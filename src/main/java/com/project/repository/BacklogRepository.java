package com.project.repository;

import com.project.entities.Backlog;
import com.project.entities.Project;
import org.springframework.data.repository.CrudRepository;

public interface BacklogRepository extends CrudRepository<Backlog, Long> {
   Backlog findByProject(Project project);
}
