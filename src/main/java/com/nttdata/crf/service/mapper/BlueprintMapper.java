package com.nttdata.crf.service.mapper;


import com.nttdata.crf.domain.*;
import com.nttdata.crf.service.dto.BlueprintDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Blueprint} and its DTO {@link BlueprintDTO}.
 */
@Mapper(componentModel = "spring", uses = {WorkloadMapper.class})
public interface BlueprintMapper extends EntityMapper<BlueprintDTO, Blueprint> {

    @Mapping(source = "workload.id", target = "workloadId")
    @Mapping(source = "workload.title", target = "workloadTitle")
    BlueprintDTO toDto(Blueprint blueprint);

    @Mapping(source = "workloadId", target = "workload")
    Blueprint toEntity(BlueprintDTO blueprintDTO);

    default Blueprint fromId(String id) {
        if (id == null) {
            return null;
        }
        Blueprint blueprint = new Blueprint();
        blueprint.setId(id);
        return blueprint;
    }
}
