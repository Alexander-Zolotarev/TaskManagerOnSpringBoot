package com.project.repository;

import com.project.entities.Project;
import com.project.entities.Sprint;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SprintRepository extends CrudRepository<Sprint, Long> {

    List<Sprint> findByProject(Project project);
    Sprint findByProject(String sprintTitle);
    Sprint findByTitle(String sprintTitle);
}
