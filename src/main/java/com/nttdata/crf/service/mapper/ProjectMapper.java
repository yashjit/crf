package com.nttdata.crf.service.mapper;


import com.nttdata.crf.domain.*;
import com.nttdata.crf.service.dto.ProjectDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Project} and its DTO {@link ProjectDTO}.
 */
@Mapper(componentModel = "spring", uses = {OrganizationMapper.class})
public interface ProjectMapper extends EntityMapper<ProjectDTO, Project> {

    @Mapping(source = "organization.id", target = "organizationId")
    @Mapping(source = "organization.orgName", target = "organizationOrgName")
    ProjectDTO toDto(Project project);

    @Mapping(target = "waves", ignore = true)
    @Mapping(target = "removeWave", ignore = true)
    @Mapping(source = "organizationId", target = "organization")
    Project toEntity(ProjectDTO projectDTO);

    default Project fromId(String id) {
        if (id == null) {
            return null;
        }
        Project project = new Project();
        project.setId(id);
        return project;
    }
}
