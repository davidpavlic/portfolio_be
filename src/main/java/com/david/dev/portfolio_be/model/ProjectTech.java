package com.david.dev.portfolio_be.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="project_tech")
public class ProjectTech {

    //TODO: Setup UUID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projecttech_id;

    private String projecttech_name;

    public ProjectTech(String projecttech_name) {
        this.projecttech_name = projecttech_name;
    }
}
