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
@Table(name="project_tech")
public class ProjectTech {

    @Id
    @GeneratedValue
    private UUID projecttech_id;

    private String projecttech_name;

    public ProjectTech(String projecttech_name) {
        this.projecttech_name = projecttech_name;
    }
}
