package com.david.dev.portfolio_be.repository;

import com.david.dev.portfolio_be.model.ProjectCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjectCardRepository extends JpaRepository<ProjectCard, UUID> {
}
