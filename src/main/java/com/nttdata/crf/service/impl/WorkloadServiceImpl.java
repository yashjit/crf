package com.nttdata.crf.service.impl;

import com.nttdata.crf.service.WorkloadService;
import com.nttdata.crf.domain.Workload;
import com.nttdata.crf.repository.WorkloadRepository;
import com.nttdata.crf.service.dto.WorkloadDTO;
import com.nttdata.crf.service.mapper.WorkloadMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Workload}.
 */
@Service
public class WorkloadServiceImpl implements WorkloadService {

    private final Logger log = LoggerFactory.getLogger(WorkloadServiceImpl.class);

    private final WorkloadRepository workloadRepository;

    private final WorkloadMapper workloadMapper;

    public WorkloadServiceImpl(WorkloadRepository workloadRepository, WorkloadMapper workloadMapper) {
        this.workloadRepository = workloadRepository;
        this.workloadMapper = workloadMapper;
    }

    @Override
    public WorkloadDTO save(WorkloadDTO workloadDTO) {
        log.debug("Request to save Workload : {}", workloadDTO);
        Workload workload = workloadMapper.toEntity(workloadDTO);
        workload = workloadRepository.save(workload);
        return workloadMapper.toDto(workload);
    }

    @Override
    public Page<WorkloadDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Workloads");
        return workloadRepository.findAll(pageable)
            .map(workloadMapper::toDto);
    }


    @Override
    public Optional<WorkloadDTO> findOne(String id) {
        log.debug("Request to get Workload : {}", id);
        return workloadRepository.findById(id)
            .map(workloadMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Workload : {}", id);
        workloadRepository.deleteById(id);
    }
}
