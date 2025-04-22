package com.david.dev.portfolio_be.service;

import com.david.dev.portfolio_be.model.ProjectTech;
import com.david.dev.portfolio_be.model.dto.ProjectTechDTO;
import com.david.dev.portfolio_be.model.mapper.ProjectTechMapper;
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
    private final ProjectTechMapper projectTechMapper;

    public ProjectTechService(ProjectTechRepository projectTechRepository, ProjectTechMapper projectTechMapper) {
        this.projectTechRepository = projectTechRepository;
        this.projectTechMapper = projectTechMapper;
    }

    public Collection<ProjectTechDTO> getAllProjectTechs() {
        return projectTechRepository.findAll().stream()
                .map(projectTechMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProjectTech createProjectTech(ProjectTechDTO projectTechDTO) throws IOException {
        ProjectTech projectTech = projectTechMapper.toEntity(projectTechDTO);
        return projectTechRepository.save(projectTech);
    }

    public List<ProjectTech> createProjectTechs(List<ProjectTechDTO> projectTechDTOs) throws IOException {
        List<ProjectTech> projectTechList = new ArrayList<>();
        for(ProjectTechDTO projectTechDTO : projectTechDTOs){
            projectTechList.add(projectTechMapper.toEntity(projectTechDTO));
        }
        return projectTechRepository.saveAll(projectTechList);
    }

    public List<ProjectTech> createProjectTechsFromStrings(List<String> projectTechDTOs) throws IOException {
        if(projectTechDTOs.isEmpty())
            return new ArrayList<>();
        List<ProjectTech> projectTechList = new ArrayList<>();
        for(String projectTechDTO : projectTechDTOs){
            projectTechList.add(ProjectTech.builder()
                    .name(projectTechDTO)
                    .build());
        }
        return projectTechRepository.saveAll(projectTechList);
    }
}
