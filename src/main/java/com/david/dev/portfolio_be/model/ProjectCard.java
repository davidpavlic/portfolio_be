package com.david.dev.portfolio_be.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a project card entity containing details about a project,
 * including its title, description, image, and associated technologies.
 */
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE) //Private constructor to enforce builder pattern.
@Builder
@Table(name="project_card")
public class ProjectCard extends BaseEntity{

    @Setter
    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String title;

    @Setter
    @NotBlank
    @Size(max = 2000)
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Setter
    @NotNull
    @Lob
    private byte[] image;

    @OneToMany(mappedBy = "projectCard", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ProjectCardTech> techStacks = new ArrayList<>(); // Optional references to ProjectTech, initialized to an empty ArrayList by default by the builder.
    //TODO: Change to UnmodifiableList if needed in the future.

    //ProjectTech side must be added, if further development includes it.
    public void addTech(ProjectTech tech) {
        boolean alreadyExists = techStacks.stream()
                .anyMatch(link -> link.getProjectTech().equals(tech));
        if (!alreadyExists) {
            techStacks.add(ProjectCardTech.builder()
                    .projectCard(this)  // Ensures the link points to this ProjectCard
                    .projectTech(tech)  // Ensures the link points to the given ProjectTech
                    .build());
        }
    }
}