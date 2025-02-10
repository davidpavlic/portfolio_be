package com.david.dev.portfolio_be.controller;

import com.david.dev.portfolio_be.model.ProjectCard;
import com.david.dev.portfolio_be.model.ProjectCardRepository;
import com.david.dev.portfolio_be.model.dto.ProjectCardDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Collection;
import java.util.stream.Collectors;

//TODO: Learn CORS
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/projectcard")
public class ProjectCardController {

    private ProjectCardRepository projectCardRepository;

    public ProjectCardController(ProjectCardRepository projectCardRepository) {
        this.projectCardRepository = projectCardRepository;
    }

    //TODO: Separate dto conversion externally (See old code)
    @GetMapping({"", "/"})
    public Collection<ProjectCardDTO> getAllProjectCards() {
        return projectCardRepository.findAll().stream().map(projectCard -> {
            ProjectCardDTO dto = new ProjectCardDTO(
                    projectCard.getProjectcard_id(),
                    projectCard.getProjectcard_title(),
                    projectCard.getProjectcard_description(),
                    Base64.getEncoder().encodeToString(projectCard.getProjectcard_image()));
            return dto;
        }).collect(Collectors.toList());
    }

    //TODO: Look how it is handled in the old code.
    @PostMapping(value = {"", "/"}, consumes = "multipart/form-data")
    public ResponseEntity<ProjectCard> createProjectCard(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("image") MultipartFile image) throws IOException {

        ProjectCard projectCard = new ProjectCard(title, description, image.getBytes());
        return ResponseEntity.ok(projectCardRepository.save(projectCard));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjectCard(@PathVariable("id") Long id) {
        if (!projectCardRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        projectCardRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
