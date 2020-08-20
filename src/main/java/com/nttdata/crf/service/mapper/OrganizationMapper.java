package com.nttdata.crf.service.mapper;


import com.nttdata.crf.domain.*;
import com.nttdata.crf.service.dto.OrganizationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Organization} and its DTO {@link OrganizationDTO}.
 */
@Mapper(componentModel = "spring", uses = {LocationMapper.class})
public interface OrganizationMapper extends EntityMapper<OrganizationDTO, Organization> {

    @Mapping(source = "location.id", target = "locationId")
    @Mapping(source = "location.title", target = "locationTitle")
    OrganizationDTO toDto(Organization organization);

    @Mapping(source = "locationId", target = "location")
    @Mapping(target = "projects", ignore = true)
    @Mapping(target = "removeProject", ignore = true)
    @Mapping(target = "teams", ignore = true)
    @Mapping(target = "removeTeam", ignore = true)
    @Mapping(target = "configMasters", ignore = true)
    @Mapping(target = "removeConfigMaster", ignore = true)
    Organization toEntity(OrganizationDTO organizationDTO);

    default Organization fromId(String id) {
        if (id == null) {
            return null;
        }
        Organization organization = new Organization();
        organization.setId(id);
        return organization;
    }
}
