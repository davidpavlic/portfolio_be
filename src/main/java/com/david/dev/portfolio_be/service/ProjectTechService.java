package com.david.dev.portfolio_be.service;

import com.david.dev.portfolio_be.model.ProjectTech;
import com.david.dev.portfolio_be.model.dto.ProjectTechDTO;
import com.david.dev.portfolio_be.model.mapper.ProjectTechMapper;
import com.david.dev.portfolio_be.repository.ProjectTechRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTechService {

    private final ProjectTechRepository projectTechRepository;

    public ProjectTechService(ProjectTechRepository projectTechRepository) {
        this.projectTechRepository = projectTechRepository;
    }

    public List<ProjectTechDTO> getAllProjectTechs() {
        return projectTechRepository.findAll().stream()
                .map(ProjectTechMapper::toDTO)
                .toList();
    }

    public ProjectTech createProjectTech(ProjectTechDTO projectTechDTO) {
        return projectTechRepository.save(ProjectTechMapper.toEntity(projectTechDTO));
    }

    public List<ProjectTech> createProjectTechs(List<ProjectTechDTO> projectTechDTOs) {
        List<ProjectTech> entities = projectTechDTOs.stream()
                .map(ProjectTechMapper::toEntity)
                .toList();

        return projectTechRepository.saveAll(entities);
    }

    public List<ProjectTech> createProjectTechsFromStrings(List<String> techNames) {
        if (techNames == null || techNames.isEmpty()) return List.of();

        List<ProjectTech> entities = techNames.stream()
                .map(name -> ProjectTech.builder().name(name).build())
                .toList();

        return projectTechRepository.saveAll(entities);
    }
}
