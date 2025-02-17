package com.david.dev.portfolio_be.controller;

import com.david.dev.portfolio_be.model.ProjectCard;
import com.david.dev.portfolio_be.model.dto.ProjectCardDTO;
import com.david.dev.portfolio_be.service.ProjectCardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/projectcard")
public class ProjectCardController {

    private final ProjectCardService projectCardService;

    public ProjectCardController(ProjectCardService projectCardService) {
        this.projectCardService = projectCardService;
    }

    @GetMapping({"", "/"})
    public Collection<ProjectCardDTO> getAllProjectCards() {
        return projectCardService.getAllProjectCards();
    }

    @PostMapping(value = {"", "/"}, consumes = "application/json")
    public ResponseEntity<ProjectCard> createProjectCard(
            @RequestBody ProjectCardDTO projectCardDTO) throws IOException {

        ProjectCard projectCard = projectCardService.createProjectCard(projectCardDTO);
        return ResponseEntity.ok(projectCard);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<ProjectCard> updateProjectCard(
            @PathVariable("id") Long id,
            @RequestBody ProjectCardDTO projectCardDTO) throws IOException {

        ProjectCard updatedProjectCard = projectCardService.updateProjectCard(id, projectCardDTO);
        return ResponseEntity.ok(updatedProjectCard);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjectCard(@PathVariable("id") Long id) {
        projectCardService.deleteProjectCard(id);
        return ResponseEntity.noContent().build();
    }
}