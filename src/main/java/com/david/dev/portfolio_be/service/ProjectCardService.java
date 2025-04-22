package com.david.dev.portfolio_be.service;

import com.david.dev.portfolio_be.model.ProjectCard;
import com.david.dev.portfolio_be.model.ProjectCardTech;
import com.david.dev.portfolio_be.model.ProjectTech;
import com.david.dev.portfolio_be.model.mapper.ProjectCardMapper;
import com.david.dev.portfolio_be.repository.ProjectCardRepository;
import com.david.dev.portfolio_be.model.dto.ProjectCardDTO;
import com.david.dev.portfolio_be.repository.ProjectCardTechRepository;
import com.david.dev.portfolio_be.repository.ProjectTechRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProjectCardService {

    private final ProjectCardTechRepository projectCardTechRepository;
    private final ProjectTechRepository projectTechRepository;
    private final ProjectCardRepository projectCardRepository;
    private final ProjectCardMapper projectCardMapper;

    public ProjectCardService(ProjectCardTechRepository projectCardTechRepository, ProjectTechRepository projectTechRepository, ProjectCardRepository projectCardRepository, ProjectCardMapper projectCardMapper) {
        this.projectCardTechRepository = projectCardTechRepository;
        this.projectTechRepository = projectTechRepository;
        this.projectCardRepository = projectCardRepository;
        this.projectCardMapper = projectCardMapper;
    }

    public Collection<ProjectCardDTO> getAllProjectCards() {
        return projectCardRepository.findAll().stream()
                .map(projectCardMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProjectCard createProjectCard(ProjectCard projectCard, List<String> techStacks) {
        // Process each tech stack first
        for (String techName : techStacks) {
            // Find or create the ProjectTech
            ProjectTech projectTech = projectTechRepository.findByName(techName)
                    .orElseGet(() -> projectTechRepository.save(
                            ProjectTech.builder().name(techName).build()
                    ));

            // Use the entity's addTech() to build the relationship
            projectCard.addTech(projectTech);
        }

        // Single save cascades to ProjectCardTechs (no need for manual ProjectCardTechRepository.save)
        ProjectCard savedCard = projectCardRepository.save(projectCard);

        return savedCard;
    }

    public ProjectCard updateProjectCard(UUID id, ProjectCardDTO projectCardDTO) throws IOException {
        ProjectCard existingProjectCard = projectCardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProjectCard not found with id: " + id));

        if (projectCardDTO.getTitle() != null) {
            existingProjectCard.setTitle(projectCardDTO.getTitle());
        }
        if (projectCardDTO.getDescription() != null) {
            existingProjectCard.setDescription(projectCardDTO.getDescription());
        }
        if (projectCardDTO.getBase64Image() != null && !projectCardDTO.getBase64Image().isEmpty()) {
            existingProjectCard.setImage(projectCardDTO.getBase64Image().getBytes());
        }

        return projectCardRepository.save(existingProjectCard);
    }

    @Transactional
    public void deleteProjectCard(UUID id) {
        ProjectCard projectCard = projectCardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProjectCard not found with id: " + id));

        List<ProjectCardTech> relatedTechs = projectCardTechRepository.findAllByProjectCard(projectCard);

        //check if it only appears once
        projectCardRepository.deleteById(id);
        for(ProjectCardTech cardTech : relatedTechs) {
            if (projectCardTechRepository.findAllByProjectTech(cardTech.getProjectTech()).size() == 0) {
                projectTechRepository.deleteById(cardTech.getProjectTech().getId());
            }
        }
    }

}