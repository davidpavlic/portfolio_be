package com.david.dev.portfolio_be.repository;

import com.david.dev.portfolio_be.model.ProjectCard;
import com.david.dev.portfolio_be.model.ProjectCardTech;
import com.david.dev.portfolio_be.model.ProjectTech;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectTechRepository extends JpaRepository<ProjectTech, Long> {
    @Query("SELECT pt FROM ProjectTech pt WHERE pt.projecttech_name = :name")
    Optional<ProjectTech> findByProjecttechName(@Param("name") String name);
}
