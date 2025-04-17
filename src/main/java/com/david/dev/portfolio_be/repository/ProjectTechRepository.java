package com.david.dev.portfolio_be.repository;

import com.david.dev.portfolio_be.model.ProjectCard;
import com.david.dev.portfolio_be.model.ProjectCardTech;
import com.david.dev.portfolio_be.model.ProjectTech;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectTechRepository extends JpaRepository<ProjectTech, UUID> {
    @Query("SELECT pt FROM ProjectTech pt WHERE pt.projecttech_name = :name")
    Optional<ProjectTech> findByProjecttechName(@Param("name") String name);
}
