package com.nttdata.crf.service.impl;

import com.nttdata.crf.service.FieldMasterService;
import com.nttdata.crf.domain.FieldMaster;
import com.nttdata.crf.repository.FieldMasterRepository;
import com.nttdata.crf.service.dto.FieldMasterDTO;
import com.nttdata.crf.service.mapper.FieldMasterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link FieldMaster}.
 */
@Service
public class FieldMasterServiceImpl implements FieldMasterService {

    private final Logger log = LoggerFactory.getLogger(FieldMasterServiceImpl.class);

    private final FieldMasterRepository fieldMasterRepository;

    private final FieldMasterMapper fieldMasterMapper;

    public FieldMasterServiceImpl(FieldMasterRepository fieldMasterRepository, FieldMasterMapper fieldMasterMapper) {
        this.fieldMasterRepository = fieldMasterRepository;
        this.fieldMasterMapper = fieldMasterMapper;
    }

    @Override
    public FieldMasterDTO save(FieldMasterDTO fieldMasterDTO) {
        log.debug("Request to save FieldMaster : {}", fieldMasterDTO);
        FieldMaster fieldMaster = fieldMasterMapper.toEntity(fieldMasterDTO);
        fieldMaster = fieldMasterRepository.save(fieldMaster);
        return fieldMasterMapper.toDto(fieldMaster);
    }

    @Override
    public List<FieldMasterDTO> findAll() {
        log.debug("Request to get all FieldMasters");
        return fieldMasterRepository.findAll().stream()
            .map(fieldMasterMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    public Optional<FieldMasterDTO> findOne(String id) {
        log.debug("Request to get FieldMaster : {}", id);
        return fieldMasterRepository.findById(id)
            .map(fieldMasterMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete FieldMaster : {}", id);
        fieldMasterRepository.deleteById(id);
    }
}
