package com.david.dev.portfolio_be.repository;

import com.david.dev.portfolio_be.model.ProjectCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectCardRepository extends JpaRepository<ProjectCard, Long> {
}
