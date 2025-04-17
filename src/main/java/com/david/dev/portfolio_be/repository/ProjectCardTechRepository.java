package com.david.dev.portfolio_be.repository;

import com.david.dev.portfolio_be.model.ProjectCard;
import com.david.dev.portfolio_be.model.ProjectCardTech;
import com.david.dev.portfolio_be.model.ProjectTech;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectCardTechRepository extends JpaRepository<ProjectCardTech, Long> {

    List<ProjectCardTech> findAllByProjectCard(ProjectCard projectCard);
    List<ProjectCardTech> findAllByProjectTech(ProjectTech projectTech);
}
