package com.david.dev.portfolio_be.service;

import com.david.dev.portfolio_be.model.ProjectCard;
import com.david.dev.portfolio_be.model.ProjectTech;
import com.david.dev.portfolio_be.model.dto.ProjectCardDTO;
import com.david.dev.portfolio_be.model.dto.ProjectTechDTO;
import com.david.dev.portfolio_be.repository.ProjectTechRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectTechService {

    private final ProjectTechRepository projectTechRepository;

    public ProjectTechService(ProjectTechRepository projectTechRepository) {
        this.projectTechRepository = projectTechRepository;
    }

    public Collection<ProjectTechDTO> getAllProjectTechs() {
        return projectTechRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public ProjectTech createProjectTech(ProjectTechDTO projectTechDTO) throws IOException {
        ProjectTech projectTech = new ProjectTech(projectTechDTO.getName());
        return projectTechRepository.save(projectTech);
    }

    public List<ProjectTech> createProjectTechs(List<ProjectTechDTO> projectTechDTOs) throws IOException {
        List<ProjectTech> projectTechList = new ArrayList<>();
        for(ProjectTechDTO projectTechDTO : projectTechDTOs){
            projectTechList.add(new ProjectTech(projectTechDTO.getName()));
        }
        return projectTechRepository.saveAll(projectTechList);
    }

    public List<ProjectTech> createProjectTechsFromStrings(List<String> projectTechDTOs) throws IOException {
        List<ProjectTech> projectTechList = new ArrayList<>();
        for(String projectTechDTO : projectTechDTOs){
            projectTechList.add(new ProjectTech(projectTechDTO));
        }
        return projectTechRepository.saveAll(projectTechList);
    }

    private ProjectTechDTO convertToDTO(ProjectTech projectTech) {
        return new ProjectTechDTO(
                projectTech.getProjecttech_id(),
                projectTech.getProjecttech_name()
        );
    }
}
