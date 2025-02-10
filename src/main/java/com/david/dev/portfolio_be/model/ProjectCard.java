package com.david.dev.portfolio_be.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
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

    //Constructor
    public ProjectCard(String projectcard_title, String projectcard_description, byte[] projectcard_image) {
        this.projectcard_title = projectcard_title;
        this.projectcard_description = projectcard_description;
        this.projectcard_image = projectcard_image;
    }

    //Getters and Setters
    public Long getProjectcard_id() {
        return projectcard_id;
    }
    public void setProjectcard_id(Long projectcard_id) {
        this.projectcard_id = projectcard_id;
    }
    public String getProjectcard_title() {
        return projectcard_title;
    }
    public void setProjectcard_title(String projectcard_title) {
        this.projectcard_title = projectcard_title;
    }
    public String getProjectcard_description() {
        return projectcard_description;
    }
    public void setProjectcard_description(String projectcard_description) { this.projectcard_description = projectcard_description; }
    public byte[] getProjectcard_image() { return projectcard_image; }
    public void setProjectcard_image(byte[] projectcard_image) { this.projectcard_image = projectcard_image; }
}
