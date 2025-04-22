package com.david.dev.portfolio_be.model.mapper;

import com.david.dev.portfolio_be.model.ProjectCard;
import com.david.dev.portfolio_be.model.dto.ProjectCardDTO;
import com.david.dev.portfolio_be.util.ImageUtil;

import java.util.stream.Collectors;

public class ProjectCardMapper {

    public static ProjectCardDTO toDTO(ProjectCard projectCard) {
        return new ProjectCardDTO(
                projectCard.getId(),
                projectCard.getTitle(),
                projectCard.getDescription(),
                ImageUtil.encodeBase64Image(projectCard.getImage()),
                projectCard.getTechStacks().stream()
                        .map(tech -> tech.getProjectTech().getName())
                        .collect(Collectors.toList())
        );
    }

    public static ProjectCard toEntity(ProjectCardDTO projectCardDTO) {
        return ProjectCard.builder()
                .title(projectCardDTO.getTitle())
                .description(projectCardDTO.getDescription())
                .image(ImageUtil.decodeBase64Image(projectCardDTO.getBase64Image()))
                .build();
    }
}
