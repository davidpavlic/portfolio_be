package com.david.dev.portfolio_be.model.mapper;

import com.david.dev.portfolio_be.model.ProjectCard;
import com.david.dev.portfolio_be.model.dto.ProjectCardDTO;
import com.david.dev.portfolio_be.util.ImageUtil;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

//TODO: Handle wrong parameters
@Component
public class ProjectCardMapper {

    public ProjectCardDTO toDTO(ProjectCard projectCard) {
        return new ProjectCardDTO(
                projectCard.getProjectcard_id(),
                projectCard.getProjectcard_title(),
                projectCard.getProjectcard_description(),
                ImageUtil.encodeBase64Image(projectCard.getProjectcard_image()),
                projectCard.getTechStacks().stream()
                        .map(tech -> tech.getProjectTech().getProjecttech_name())
                        .collect(Collectors.toList())
        );
    }

    public ProjectCard toEntity(ProjectCardDTO projectCardDTO) {
        return new ProjectCard(
                projectCardDTO.getTitle(),
                projectCardDTO.getDescription(),
                ImageUtil.decodeBase64Image(projectCardDTO.getBase64Image())
        );
    }
}
