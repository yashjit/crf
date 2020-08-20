package com.nttdata.crf.service.impl;

import com.nttdata.crf.service.ZoneService;
import com.nttdata.crf.domain.Zone;
import com.nttdata.crf.repository.ZoneRepository;
import com.nttdata.crf.service.dto.ZoneDTO;
import com.nttdata.crf.service.mapper.ZoneMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Zone}.
 */
@Service
public class ZoneServiceImpl implements ZoneService {

    private final Logger log = LoggerFactory.getLogger(ZoneServiceImpl.class);

    private final ZoneRepository zoneRepository;

    private final ZoneMapper zoneMapper;

    public ZoneServiceImpl(ZoneRepository zoneRepository, ZoneMapper zoneMapper) {
        this.zoneRepository = zoneRepository;
        this.zoneMapper = zoneMapper;
    }

    @Override
    public ZoneDTO save(ZoneDTO zoneDTO) {
        log.debug("Request to save Zone : {}", zoneDTO);
        Zone zone = zoneMapper.toEntity(zoneDTO);
        zone = zoneRepository.save(zone);
        return zoneMapper.toDto(zone);
    }

    @Override
    public List<ZoneDTO> findAll() {
        log.debug("Request to get all Zones");
        return zoneRepository.findAll().stream()
            .map(zoneMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    public Optional<ZoneDTO> findOne(String id) {
        log.debug("Request to get Zone : {}", id);
        return zoneRepository.findById(id)
            .map(zoneMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Zone : {}", id);
        zoneRepository.deleteById(id);
    }
}
