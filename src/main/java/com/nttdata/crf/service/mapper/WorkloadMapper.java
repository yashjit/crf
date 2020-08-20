package com.nttdata.crf.service.mapper;


import com.nttdata.crf.domain.*;
import com.nttdata.crf.service.dto.WorkloadDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Workload} and its DTO {@link WorkloadDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WorkloadMapper extends EntityMapper<WorkloadDTO, Workload> {


    @Mapping(target = "blueprints", ignore = true)
    @Mapping(target = "removeBlueprint", ignore = true)
    @Mapping(target = "waves", ignore = true)
    @Mapping(target = "removeWave", ignore = true)
    Workload toEntity(WorkloadDTO workloadDTO);

    default Workload fromId(String id) {
        if (id == null) {
            return null;
        }
        Workload workload = new Workload();
        workload.setId(id);
        return workload;
    }
}
