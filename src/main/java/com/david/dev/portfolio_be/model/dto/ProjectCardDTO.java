package com.david.dev.portfolio_be.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectCardDTO {
    private UUID projectcard_id;
    private String title;
    private String description;
    private String base64Image;
    private List<String> techStacks;
}
