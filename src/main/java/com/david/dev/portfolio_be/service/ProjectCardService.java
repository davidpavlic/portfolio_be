package com.david.dev.portfolio_be.service;

import com.david.dev.portfolio_be.model.ProjectCard;
import com.david.dev.portfolio_be.model.ProjectCardRepository;
import com.david.dev.portfolio_be.model.dto.ProjectCardDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Collection;
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

    public void deleteProjectCard(Long id) {
        projectCardRepository.deleteById(id);
    }

    private ProjectCardDTO convertToDTO(ProjectCard projectCard) {
        String imageBase64 = projectCard.getProjectcard_image() != null ?
                Base64.getEncoder().encodeToString(projectCard.getProjectcard_image()) : null;
        return new ProjectCardDTO(
                projectCard.getProjectcard_id(),
                projectCard.getProjectcard_title(),
                projectCard.getProjectcard_description(),
                imageBase64
        );
    }
}