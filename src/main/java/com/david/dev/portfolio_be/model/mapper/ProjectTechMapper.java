package com.david.dev.portfolio_be.model.mapper;

import com.david.dev.portfolio_be.model.ProjectTech;
import com.david.dev.portfolio_be.model.dto.ProjectTechDTO;
import org.springframework.stereotype.Component;

//TODO: Handle wrong parameters
@Component
public class ProjectTechMapper {

    public ProjectTechDTO toDTO(ProjectTech projectTech) {
        return new ProjectTechDTO(
                projectTech.getProjecttech_id(),
                projectTech.getProjecttech_name()
        );
    }

    public ProjectTech toEntity(ProjectTechDTO projectTechDTO) {
        return new ProjectTech(
                projectTechDTO.getProjecttech_id(),
                projectTechDTO.getName()
        );
    }
}
