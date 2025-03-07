package com.david.dev.portfolio_be.service;

import com.david.dev.portfolio_be.model.ProjectCard;
import com.david.dev.portfolio_be.model.mapper.ProjectCardMapper;
import com.david.dev.portfolio_be.repository.ProjectCardRepository;
import com.david.dev.portfolio_be.model.dto.ProjectCardDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class ProjectCardService {

    private final ProjectCardRepository projectCardRepository;
    private final ProjectCardMapper projectCardMapper;

    public ProjectCardService(ProjectCardRepository projectCardRepository, ProjectCardMapper projectCardMapper) {
        this.projectCardRepository = projectCardRepository;
        this.projectCardMapper = projectCardMapper;
    }

    public Collection<ProjectCardDTO> getAllProjectCards() {
        return projectCardRepository.findAll().stream()
                .map(projectCardMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProjectCard createProjectCard(ProjectCardDTO projectCardDTO) throws IOException {
        ProjectCard projectCard = projectCardMapper.toEntity(projectCardDTO);
        return projectCardRepository.save(projectCard);
    }

    //TODO: Redundant
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

}