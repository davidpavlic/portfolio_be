package com.david.dev.portfolio_be.repository;

import com.david.dev.portfolio_be.model.ProjectTech;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectTechRepository extends JpaRepository<ProjectTech, Long> {
}
