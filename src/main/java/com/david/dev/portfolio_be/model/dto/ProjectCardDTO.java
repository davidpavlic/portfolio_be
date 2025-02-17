package com.david.dev.portfolio_be.model.dto;

import java.util.List;

public class ProjectCardDTO {
    private Long projectcard_id;
    private String title;
    private String description;
    private String base64Image;
    private List<String> techStacks;

    //Constructor
    public ProjectCardDTO(Long projectcard_id, String title, String description, String base64Image, List<String> techStacks) {
        this.projectcard_id = projectcard_id;
        this.title = title;
        this.description = description;
        this.base64Image = base64Image;
        this.techStacks = techStacks;
    }

    // Getters and setters
    public Long getProjectcard_id() { return projectcard_id; }
    public void setProjectcard_id(Long projectcard_id) { this.projectcard_id = projectcard_id; }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getBase64Image() {
        return base64Image;
    }
    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public List<String> getTechStacks() {
        return techStacks;
    }

    public void setTechStacks(List<String> techStacks) {
        this.techStacks = techStacks;
    }
}