package com.david.dev.portfolio_be.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * Represents a project tech entity containing details about a tech associated with a project,
 * it contains the name of the technology.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE) //Private constructor to enforce builder pattern.
@Builder
@Table(name="project_tech")
public class ProjectTech extends BaseEntity{

    @NotBlank
    @Size(max = 32)
    @Column(nullable = false, length = 32, unique = true)
    private String name;

}