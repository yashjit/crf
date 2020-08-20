package com.nttdata.crf.service.impl;

import com.nttdata.crf.service.BlueprintService;
import com.nttdata.crf.domain.Blueprint;
import com.nttdata.crf.repository.BlueprintRepository;
import com.nttdata.crf.service.dto.BlueprintDTO;
import com.nttdata.crf.service.mapper.BlueprintMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Blueprint}.
 */
@Service
public class BlueprintServiceImpl implements BlueprintService {

    private final Logger log = LoggerFactory.getLogger(BlueprintServiceImpl.class);

    private final BlueprintRepository blueprintRepository;

    private final BlueprintMapper blueprintMapper;

    public BlueprintServiceImpl(BlueprintRepository blueprintRepository, BlueprintMapper blueprintMapper) {
        this.blueprintRepository = blueprintRepository;
        this.blueprintMapper = blueprintMapper;
    }

    @Override
    public BlueprintDTO save(BlueprintDTO blueprintDTO) {
        log.debug("Request to save Blueprint : {}", blueprintDTO);
        Blueprint blueprint = blueprintMapper.toEntity(blueprintDTO);
        blueprint = blueprintRepository.save(blueprint);
        return blueprintMapper.toDto(blueprint);
    }

    @Override
    public List<BlueprintDTO> findAll() {
        log.debug("Request to get all Blueprints");
        return blueprintRepository.findAll().stream()
            .map(blueprintMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    public Optional<BlueprintDTO> findOne(String id) {
        log.debug("Request to get Blueprint : {}", id);
        return blueprintRepository.findById(id)
            .map(blueprintMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Blueprint : {}", id);
        blueprintRepository.deleteById(id);
    }
}
