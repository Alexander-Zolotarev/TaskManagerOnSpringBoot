package com.project.repository;

import com.project.entities.Backlog;
import org.springframework.data.repository.CrudRepository;

public interface BacklogRepository extends CrudRepository<Backlog, Integer> {
   Backlog findByProjectId(int id);
}
