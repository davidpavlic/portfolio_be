package com.david.dev.portfolio_be.controller;

import com.david.dev.portfolio_be.model.ProjectCard;
import com.david.dev.portfolio_be.model.dto.ProjectCardDTO;
import com.david.dev.portfolio_be.service.ProjectCardService;
import com.david.dev.portfolio_be.service.ProjectCardTechService;
import com.david.dev.portfolio_be.service.ProjectTechService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

//TODO: Dont create duplicate techs when adding a project card
//TODO: Delete techs when deleting a card
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/projectcard")
public class ProjectCardController {

    private final ProjectCardService projectCardService;
    private final ProjectTechService projectTechService;
    private final ProjectCardTechService projectCardTechService;

    public ProjectCardController(ProjectCardService projectCardService, ProjectTechService projectTechService, ProjectCardTechService projectCardTechService) {
        this.projectCardService = projectCardService;
        this.projectTechService = projectTechService;
        this.projectCardTechService = projectCardTechService;
    }

    @GetMapping({"", "/"})
    public Collection<ProjectCardDTO> getAllProjectCards() {
        return projectCardService.getAllProjectCards();
    }

    @PostMapping(value = {"", "/"}, consumes = "multipart/form-data")
    public ResponseEntity<ProjectCard> createProjectCard(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("techstacks") List<String> techStacks,
            @RequestParam("image") MultipartFile image) throws IOException {

        //TODO: Incosistent
        ProjectCard projectCard = projectCardService.createProjectCard(
            new ProjectCard(title, description, image != null ? image.getBytes() : null)
        );

        projectCardTechService.createProjectCardTechs(
                projectTechService.createProjectTechsFromStrings(techStacks),
                projectCard
        );

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