package com.project.repository;

import com.project.entities.issue.Issue;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IssueRepository extends CrudRepository<Issue, Integer> {
    List<Issue> findByBacklog(int backlogId);
    List<Issue> findByTitle(String title);
}
