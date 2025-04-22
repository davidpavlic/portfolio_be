package com.david.dev.portfolio_be.service;

import com.david.dev.portfolio_be.model.ProjectCard;
import com.david.dev.portfolio_be.model.ProjectCardTech;
import com.david.dev.portfolio_be.model.ProjectTech;
import com.david.dev.portfolio_be.model.dto.ProjectTechDTO;
import com.david.dev.portfolio_be.model.mapper.ProjectTechMapper;
import com.david.dev.portfolio_be.repository.ProjectCardTechRepository;
import com.david.dev.portfolio_be.repository.ProjectTechRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectCardTechService {

    private final ProjectCardTechRepository projectCardTechRepository;

    public ProjectCardTechService(ProjectCardTechRepository projectCardTechRepository) {
        this.projectCardTechRepository = projectCardTechRepository;
    }

    public List<ProjectCardTech> createProjectCardTechs(List<ProjectTech> projectTechList, ProjectCard projectCard) throws IOException {
        List<ProjectCardTech> projectCardTechList = new ArrayList<>();
        if(!projectTechList.isEmpty()){
            for(ProjectTech projectTech : projectTechList){
                projectCardTechList.add(ProjectCardTech.builder().projectCard(projectCard).projectTech(projectTech).build());
            }
        }
        return projectCardTechRepository.saveAll(projectCardTechList);
    }



}
