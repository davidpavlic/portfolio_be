package com.david.dev.portfolio_be.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * Join entity linking a {@link ProjectCard} with a {@link ProjectTech}.
 * This enables a many-to-many relationship with the ability to expand later
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE) //Private constructor to enforce builder pattern.
@Builder
@Table(
    name="project_card_tech",
    uniqueConstraints = @UniqueConstraint( // Prevent duplicate associations
        columnNames = {"projectcard_id", "projecttech_id"}
    )
)
public class ProjectCardTech extends BaseEntity{

    @NotNull
    @ManyToOne
    @JoinColumn(name = "projectcard_id", nullable = false)
    private ProjectCard projectCard;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "projecttech_id", nullable = false)
    private ProjectTech projectTech;

}
