package com.david.dev.portfolio_be.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="project_card_tech")
public class ProjectCardTech {

    //TODO: Setup UUID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
