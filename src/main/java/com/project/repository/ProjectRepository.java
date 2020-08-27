package com.project.repository;

import com.project.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Integer>, JpaRepository<Project, Integer> {
    Project findById(int id);
}
