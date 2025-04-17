package com.david.dev.portfolio_be.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="project_card_tech")
public class ProjectCardTech {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "projectcard_id")
    private ProjectCard projectCard;

    @ManyToOne
    @JoinColumn(name = "projecttech_id")
    private ProjectTech projectTech;

    public ProjectCardTech(ProjectCard projectCard, ProjectTech projectTech) {
        this.projectCard = projectCard;
        this.projectTech = projectTech;
    }

}
