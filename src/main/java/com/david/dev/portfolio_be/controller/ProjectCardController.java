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

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/projectcard")
public class ProjectCardController {

    private ProjectCardRepository projectCardRepository;

    public ProjectCardController(ProjectCardRepository projectCardRepository) {
        this.projectCardRepository = projectCardRepository;
    }

    @GetMapping({"", "/"})
    public Collection<ProjectCardDTO> getAllProjectCards() {
        return projectCardRepository.findAll().stream().map(projectCard -> {
            ProjectCardDTO dto = new ProjectCardDTO();
            dto.setProjectcard_id(projectCard.getProjectcard_id());
            dto.setTitle(projectCard.getProjectcard_title());
            dto.setDescription(projectCard.getProjectcard_description());
            // Convert image bytes to a Base64 encoded string if present
            if (projectCard.getProjectcard_image() != null) {
                String base64Image = Base64.getEncoder().encodeToString(projectCard.getProjectcard_image());
                dto.setBase64Image(base64Image);
            }
            return dto;
        }).collect(Collectors.toList());
    }

    @PostMapping(value = {"", "/"}, consumes = "multipart/form-data")
    public ResponseEntity<ProjectCard> createProjectCard(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("image") MultipartFile image) throws IOException {

        ProjectCard projectCard = new ProjectCard();
        projectCard.setProjectcard_title(title);
        projectCard.setProjectcard_description(description);
        projectCard.setProjectcard_image(image.getBytes());
        projectCardRepository.save(projectCard);

        return ResponseEntity.ok(projectCard);
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
