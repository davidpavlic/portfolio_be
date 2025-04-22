package com.david.dev.portfolio_be.model.mapper;

import com.david.dev.portfolio_be.model.ProjectTech;
import com.david.dev.portfolio_be.model.dto.ProjectTechDTO;
import org.springframework.stereotype.Component;

@Component
public class ProjectTechMapper {

    public ProjectTechDTO toDTO(ProjectTech projectTech) {
        return new ProjectTechDTO(
                projectTech.getId(),
                projectTech.getName()
        );
    }

    public ProjectTech toEntity(ProjectTechDTO projectTechDTO) {
        return ProjectTech.builder()
                .name(projectTechDTO.getName())
                .build();
    }
}
