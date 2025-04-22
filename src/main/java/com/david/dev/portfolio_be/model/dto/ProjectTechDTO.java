package com.david.dev.portfolio_be.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTechDTO {
    private UUID id;

    @NotBlank
    @Size(max = 32)
    private String name;
}
