package com.david.dev.portfolio_be.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

/**
 * BaseEntity is a base class for all entities in the application.
 * It contains a unique identifier for each entity.
 */
@MappedSuperclass
@Getter
public abstract class BaseEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false, columnDefinition = "UUID")
    private UUID id;
}
