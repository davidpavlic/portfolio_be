package com.david.dev.portfolio_be.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTechDTO {
    private UUID projecttech_id;
    private String name;
}
