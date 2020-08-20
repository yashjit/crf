package com.nttdata.crf.service.mapper;


import com.nttdata.crf.domain.*;
import com.nttdata.crf.service.dto.ConfigMasterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ConfigMaster} and its DTO {@link ConfigMasterDTO}.
 */
@Mapper(componentModel = "spring", uses = {OrganizationMapper.class})
public interface ConfigMasterMapper extends EntityMapper<ConfigMasterDTO, ConfigMaster> {

    @Mapping(source = "organization.id", target = "organizationId")
    @Mapping(source = "organization.orgName", target = "organizationOrgName")
    ConfigMasterDTO toDto(ConfigMaster configMaster);

    @Mapping(source = "organizationId", target = "organization")
    ConfigMaster toEntity(ConfigMasterDTO configMasterDTO);

    default ConfigMaster fromId(String id) {
        if (id == null) {
            return null;
        }
        ConfigMaster configMaster = new ConfigMaster();
        configMaster.setId(id);
        return configMaster;
    }
}
