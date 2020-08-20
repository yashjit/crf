package com.nttdata.crf.service.mapper;


import com.nttdata.crf.domain.*;
import com.nttdata.crf.service.dto.WaveDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Wave} and its DTO {@link WaveDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProjectMapper.class})
public interface WaveMapper extends EntityMapper<WaveDTO, Wave> {

    @Mapping(source = "project.id", target = "projectId")
    @Mapping(source = "project.projectName", target = "projectProjectName")
    WaveDTO toDto(Wave wave);

    @Mapping(target = "applications", ignore = true)
    @Mapping(target = "removeApplication", ignore = true)
    @Mapping(source = "projectId", target = "project")
    Wave toEntity(WaveDTO waveDTO);

    default Wave fromId(String id) {
        if (id == null) {
            return null;
        }
        Wave wave = new Wave();
        wave.setId(id);
        return wave;
    }
}
