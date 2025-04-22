package com.david.dev.portfolio_be.controller;

import com.david.dev.portfolio_be.model.ProjectCard;
import com.david.dev.portfolio_be.model.dto.ProjectCardDTO;
import com.david.dev.portfolio_be.service.ProjectCardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

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

    @PostMapping(value = {"", "/"}, consumes = "multipart/form-data")
    public ResponseEntity<ProjectCard> createProjectCard(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("techstacks") List<String> techStacks,
            @RequestParam("image") MultipartFile image) throws IOException {

        ProjectCard projectCard = projectCardService.createProjectCard(
                ProjectCard.builder()
                        .title(title)
                        .description(description)
                        .image(image != null ? image.getBytes() : null)
                        .build(),
                techStacks
        );

        return ResponseEntity.ok(projectCard);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<ProjectCard> updateProjectCard(
            @PathVariable("id") UUID id,
            @RequestBody ProjectCardDTO projectCardDTO) throws IOException {

        ProjectCard updatedProjectCard = projectCardService.updateProjectCard(id, projectCardDTO);
        return ResponseEntity.ok(updatedProjectCard);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjectCard(@PathVariable("id") UUID id) {
        projectCardService.deleteProjectCard(id);
        return ResponseEntity.noContent().build();
    }
}