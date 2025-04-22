package com.david.dev.portfolio_be.model.mapper;

import com.david.dev.portfolio_be.model.ProjectTech;
import com.david.dev.portfolio_be.model.dto.ProjectTechDTO;

public class ProjectTechMapper {

    public static ProjectTechDTO toDTO(ProjectTech projectTech) {
        return new ProjectTechDTO(
                projectTech.getId(),
                projectTech.getName()
        );
    }

    public static ProjectTech toEntity(ProjectTechDTO projectTechDTO) {
        return ProjectTech.builder()
                .name(projectTechDTO.getName())
                .build();
    }
}
