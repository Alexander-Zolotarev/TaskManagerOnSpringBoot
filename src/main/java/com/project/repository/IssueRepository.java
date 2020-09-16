package com.project.repository;

import com.project.entities.Backlog;
import com.project.entities.Sprint;
import com.project.entities.User;
import com.project.entities.issue.Issue;
import com.project.entities.issue.IssuePriority;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IssueRepository extends CrudRepository<Issue, Long> {

    List<Issue> findByBacklog(Backlog backlog);
    List<Issue> findByBacklogAndSprint(Backlog backlog, Sprint sprint);
    List<Issue> findBySprint(Sprint sprint);
    List<Issue> findByTitle(String title);
    List<Issue> findByDataCreate(LocalDate dateCreate);
    List<Issue> findByReporter(User reporterId);
    List<Issue> findByIssuePriority(IssuePriority issuePriority);
    List<Issue> findByExecutor(User executorId);
    Optional<Issue> findById(Integer issueId);
    List<Issue> deleteByBacklog(Integer issueId);
    Issue findByTitleAndSprint(String issueTitle, Sprint sprint);
    Issue findByTitleAndBacklog(String issueTitle, Backlog backlog);
}
