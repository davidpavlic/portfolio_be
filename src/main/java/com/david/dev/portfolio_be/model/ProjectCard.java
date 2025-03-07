package com.david.dev.portfolio_be.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="project_card")
public class ProjectCard {

    //TODO: Setup UUID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectcard_id;

    private String projectcard_title;

    private String projectcard_description;

    @Lob
    private byte[] projectcard_image;

    @OneToMany(mappedBy = "projectCard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectCardTech> techStacks = new ArrayList<>();

    public ProjectCard(String projectcard_title, String projectcard_description, byte[] projectcard_image) {
        this.projectcard_title = projectcard_title;
        this.projectcard_description = projectcard_description;
        this.projectcard_image = projectcard_image;
    }
}
