package com.david.dev.portfolio_be.model.dto;

public class ProjectTechDTO {
    private Long projecttech_id;
    private String name;

    public ProjectTechDTO(Long projecttech_id, String name) {
        this.projecttech_id = projecttech_id;
        this.name = name;
    }

    public Long getProjecttech_id() {
        return projecttech_id;
    }

    public void setProjecttech_id(Long projecttech_id) {
        this.projecttech_id = projecttech_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
