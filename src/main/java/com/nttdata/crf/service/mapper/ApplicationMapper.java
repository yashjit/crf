package com.nttdata.crf.service.mapper;


import com.nttdata.crf.domain.*;
import com.nttdata.crf.service.dto.ApplicationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Application} and its DTO {@link ApplicationDTO}.
 */
@Mapper(componentModel = "spring", uses = {WorkloadMapper.class, WaveMapper.class})
public interface ApplicationMapper extends EntityMapper<ApplicationDTO, Application> {

    @Mapping(source = "wave.id", target = "waveId")
    @Mapping(source = "wave.waveName", target = "waveWaveName")
    ApplicationDTO toDto(Application application);

    @Mapping(target = "removeWorkload", ignore = true)
    @Mapping(source = "waveId", target = "wave")
    Application toEntity(ApplicationDTO applicationDTO);

    default Application fromId(String id) {
        if (id == null) {
            return null;
        }
        Application application = new Application();
        application.setId(id);
        return application;
    }
}
