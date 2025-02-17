package com.david.dev.portfolio_be.controller;

import com.david.dev.portfolio_be.model.dto.ProjectCardDTO;
import com.david.dev.portfolio_be.model.dto.ProjectTechDTO;
import com.david.dev.portfolio_be.service.ProjectTechService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/projecttech")
public class ProjectTechController {

    private final ProjectTechService projectTechService;

    ProjectTechController(ProjectTechService projectTechService){
        this.projectTechService = projectTechService;
    }

    @GetMapping({"", "/"})
    public Collection<ProjectTechDTO> getAllProjectCards() {
        return projectTechService.getAllProjectTechs();
    }

}
