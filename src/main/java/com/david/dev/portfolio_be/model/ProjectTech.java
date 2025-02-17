package com.david.dev.portfolio_be.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="project_tech")
public class ProjectTech {

    //TODO: Setup UUID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projecttech_id;

    private String projecttech_name;

    public ProjectTech(){}

    public ProjectTech(String projecttech_name) {
        this.projecttech_name = projecttech_name;
    }
}
