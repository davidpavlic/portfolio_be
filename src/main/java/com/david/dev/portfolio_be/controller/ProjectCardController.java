package com.david.dev.portfolio_be.controller;

import com.david.dev.portfolio_be.model.ProjectCard;
import com.david.dev.portfolio_be.model.ProjectCardTech;
import com.david.dev.portfolio_be.model.ProjectTech;
import com.david.dev.portfolio_be.repository.ProjectCardTechRepository;
import com.david.dev.portfolio_be.repository.ProjectTechRepository;
import com.david.dev.portfolio_be.model.dto.ProjectCardDTO;
import com.david.dev.portfolio_be.service.ProjectCardService;
import com.david.dev.portfolio_be.service.ProjectTechService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//TODO: Dont create duplicate techs when adding a project card
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/projectcard")
public class ProjectCardController {

    private final ProjectCardService projectCardService;
    private final ProjectTechService projectTechService;
    private final ProjectCardTechRepository projectCardTechRepository;

    public ProjectCardController(ProjectCardService projectCardService, ProjectTechService projectTechService, ProjectCardTechRepository projectCardTechRepository) {
        this.projectCardService = projectCardService;
        this.projectTechService = projectTechService;
        this.projectCardTechRepository = projectCardTechRepository;
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

        List<ProjectTech> projectTechList = new ArrayList<>();
        if(!techStacks.isEmpty())
            projectTechList = projectTechService.createProjectTechsFromStrings(techStacks);

        ProjectCard projectCard = new ProjectCard();
        projectCard.setProjectcard_title(title);
        projectCard.setProjectcard_description(description);
        if (image != null) {
            projectCard.setProjectcard_image(image.getBytes());
        }
        projectCard = projectCardService.createProjectCard(projectCard);

        if(!projectTechList.isEmpty()){
            for(ProjectTech projectTech : projectTechList){
                projectCardTechRepository.save(new ProjectCardTech(projectCard, projectTech));
            }
        }

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