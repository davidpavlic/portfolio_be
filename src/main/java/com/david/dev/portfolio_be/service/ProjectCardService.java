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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectCardService {

    private final ProjectCardTechRepository projectCardTechRepository;
    private final ProjectTechRepository projectTechRepository;
    private final ProjectCardRepository projectCardRepository;

    public ProjectCardService(ProjectCardTechRepository projectCardTechRepository, ProjectTechRepository projectTechRepository, ProjectCardRepository projectCardRepository) {
        this.projectCardTechRepository = projectCardTechRepository;
        this.projectTechRepository = projectTechRepository;
        this.projectCardRepository = projectCardRepository;
    }

    public List<ProjectCardDTO> getAllProjectCards() {
        return projectCardRepository.findAll().stream()
                .map(ProjectCardMapper::toDTO)
                .toList();
    }

    public ProjectCardDTO createProjectCard(String title, String description, MultipartFile image, List<String> techStacks) throws IOException {
        ProjectCard projectCard = ProjectCard.builder()
                        .title(title)
                        .description(description)
                        .image(image != null ? image.getBytes() : null)
                        .build();

        techStacks.stream()
                .map(name -> projectTechRepository.findByName(name)
                        .orElseGet(() -> projectTechRepository.save(
                                ProjectTech.builder().name(name).build()
                        )))
                .forEach(projectCard::addTech);

        return ProjectCardMapper.toDTO(projectCardRepository.save(projectCard));
    }

    public ProjectCardDTO updateProjectCard(UUID id, ProjectCardDTO projectCardDTO) throws RuntimeException {
        ProjectCard existingProjectCard = projectCardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProjectCard not found with id: " + id));

        if (projectCardDTO.getTitle() != null)
            existingProjectCard.setTitle(projectCardDTO.getTitle());
        if (projectCardDTO.getDescription() != null)
            existingProjectCard.setDescription(projectCardDTO.getDescription());
        if (projectCardDTO.getBase64Image() != null && !projectCardDTO.getBase64Image().isEmpty())
            existingProjectCard.setImage(projectCardDTO.getBase64Image().getBytes());

        return ProjectCardMapper.toDTO(projectCardRepository.save(existingProjectCard));
    }

    @Transactional
    public boolean deleteProjectCard(UUID id) {
        Optional<ProjectCard> optional = projectCardRepository.findById(id);
        if (optional.isEmpty()) return false;

        ProjectCard projectCard = optional.get();
        List<ProjectCardTech> relatedTechs = projectCardTechRepository.findAllByProjectCard(projectCard);

        projectCardRepository.deleteById(id);

        relatedTechs.stream()
                .map(ProjectCardTech::getProjectTech)
                //.filter(tech -> !projectCardTechRepository.exists(tech))
                .filter(tech -> projectCardTechRepository.findAllByProjectTech(tech).isEmpty())
                .forEach(tech -> projectTechRepository.deleteById(tech.getId()));

        return true;
    }
}