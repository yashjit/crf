package com.nttdata.crf.service.impl;

import com.nttdata.crf.service.ApplicationService;
import com.nttdata.crf.domain.Application;
import com.nttdata.crf.repository.ApplicationRepository;
import com.nttdata.crf.service.dto.ApplicationDTO;
import com.nttdata.crf.service.mapper.ApplicationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Application}.
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final Logger log = LoggerFactory.getLogger(ApplicationServiceImpl.class);

    private final ApplicationRepository applicationRepository;

    private final ApplicationMapper applicationMapper;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, ApplicationMapper applicationMapper) {
        this.applicationRepository = applicationRepository;
        this.applicationMapper = applicationMapper;
    }

    @Override
    public ApplicationDTO save(ApplicationDTO applicationDTO) {
        log.debug("Request to save Application : {}", applicationDTO);
        Application application = applicationMapper.toEntity(applicationDTO);
        application = applicationRepository.save(application);
        return applicationMapper.toDto(application);
    }

    @Override
    public List<ApplicationDTO> findAll() {
        log.debug("Request to get all Applications");
        return applicationRepository.findAllWithEagerRelationships().stream()
            .map(applicationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    public Page<ApplicationDTO> findAllWithEagerRelationships(Pageable pageable) {
        return applicationRepository.findAllWithEagerRelationships(pageable).map(applicationMapper::toDto);
    }

    @Override
    public Optional<ApplicationDTO> findOne(String id) {
        log.debug("Request to get Application : {}", id);
        return applicationRepository.findOneWithEagerRelationships(id)
            .map(applicationMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Application : {}", id);
        applicationRepository.deleteById(id);
    }
}
