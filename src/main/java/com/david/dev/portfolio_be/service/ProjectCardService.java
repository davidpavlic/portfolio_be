package com.david.dev.portfolio_be.service;

import com.david.dev.portfolio_be.model.ProjectCard;
import com.david.dev.portfolio_be.repository.ProjectCardRepository;
import com.david.dev.portfolio_be.model.ProjectCardTech;
import com.david.dev.portfolio_be.model.dto.ProjectCardDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectCardService {

    private final ProjectCardRepository projectCardRepository;

    public ProjectCardService(ProjectCardRepository projectCardRepository) {
        this.projectCardRepository = projectCardRepository;
    }

    public Collection<ProjectCardDTO> getAllProjectCards() {
        return projectCardRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public ProjectCard createProjectCard(ProjectCardDTO projectCardDTO) throws IOException {
        byte[] imageBytes = null;
        if (projectCardDTO.getBase64Image() != null && !projectCardDTO.getBase64Image().isEmpty()) {
            imageBytes = projectCardDTO.getBase64Image().getBytes();
        }
        ProjectCard projectCard = new ProjectCard(projectCardDTO.getTitle(), projectCardDTO.getDescription(), imageBytes);
        return projectCardRepository.save(projectCard);
    }

    public ProjectCard createProjectCard(ProjectCard projectCard) throws IOException {
        return projectCardRepository.save(projectCard);
    }

    public ProjectCard updateProjectCard(Long id, ProjectCardDTO projectCardDTO) throws IOException {
        ProjectCard existingProjectCard = projectCardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProjectCard not found with id: " + id));

        if (projectCardDTO.getTitle() != null) {
            existingProjectCard.setProjectcard_title(projectCardDTO.getTitle());
        }
        if (projectCardDTO.getDescription() != null) {
            existingProjectCard.setProjectcard_description(projectCardDTO.getDescription());
        }
        if (projectCardDTO.getBase64Image() != null && !projectCardDTO.getBase64Image().isEmpty()) {
            existingProjectCard.setProjectcard_image(projectCardDTO.getBase64Image().getBytes());
        }

        return projectCardRepository.save(existingProjectCard);
    }

    public void deleteProjectCard(Long id) {
        projectCardRepository.deleteById(id);
    }

    private ProjectCardDTO convertToDTO(ProjectCard projectCard) {
        String imageBase64 = projectCard.getProjectcard_image() != null ?
                Base64.getEncoder().encodeToString(projectCard.getProjectcard_image()) : null;

        List<String> techStacks = new ArrayList<>();
        for(ProjectCardTech projectCardTech : projectCard.getTechStacks()){
            techStacks.add(projectCardTech.getProjectTech().getProjecttech_name());
        }

        return new ProjectCardDTO(
                projectCard.getProjectcard_id(),
                projectCard.getProjectcard_title(),
                projectCard.getProjectcard_description(),
                imageBase64,
                techStacks
        );
    }
}