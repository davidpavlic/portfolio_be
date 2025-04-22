package com.david.dev.portfolio_be.controller;

import com.david.dev.portfolio_be.model.dto.ProjectCardDTO;
import com.david.dev.portfolio_be.service.ProjectCardService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/projectcard")
public class ProjectCardController {

    private final ProjectCardService projectCardService;

    public ProjectCardController(ProjectCardService projectCardService) {
        this.projectCardService = projectCardService;
    }

    @GetMapping({"", "/"})
    public List<ProjectCardDTO> getAllProjectCards() {
        return projectCardService.getAllProjectCards();
    }

    @PostMapping(value = {"", "/"}, consumes = "multipart/form-data")
    public ResponseEntity<ProjectCardDTO> createProjectCard(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("techstacks") List<String> techStacks,
            @RequestParam("image") MultipartFile image) throws IOException {

        return ResponseEntity.status(201).body(
                projectCardService.createProjectCard(title, description, image, techStacks)
        );
    }

    @PutMapping(value = {"/{id}", "/{id}/"}, consumes = "application/json")
    public ResponseEntity<ProjectCardDTO> updateProjectCard(
            @PathVariable("id") UUID id,
            @RequestBody @Valid ProjectCardDTO projectCardDTO) throws IOException {

        try {
            ProjectCardDTO updatedCard = projectCardService.updateProjectCard(id, projectCardDTO);
            return ResponseEntity.ok(updatedCard);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping({"/{id}", "/{id}/"})
    public ResponseEntity<Void> deleteProjectCard(@PathVariable("id") UUID id) {
        return projectCardService.deleteProjectCard(id) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }
}