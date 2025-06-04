package com.david.dev.portfolio_be.model;

import jakarta.validation.constraints.NotBlank;

public class EmailRequest {

    @NotBlank
    private String caption;

    @NotBlank
    private String description;

    // Getters and setters
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
