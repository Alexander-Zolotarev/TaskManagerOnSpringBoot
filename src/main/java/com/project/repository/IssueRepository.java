package com.project.repository;

import com.project.entities.Backlog;
import com.project.entities.Sprint;
import com.project.entities.User;
import com.project.entities.issue.Issue;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IssueRepository extends CrudRepository<Issue, Long> {

    List<Issue> findByBacklog(Backlog backlog);
    List<Issue> findByBacklogAndSprint(Backlog backlog, Sprint sprint);
    List<Issue> findBySprint(Sprint sprint);
    List<Issue> findByTitle(String title);
    List<Issue> findByDataCreateAndBacklog(LocalDate dateCreate, Backlog backlog);
    List<Issue> findByReporterAndBacklog(User reporter, Backlog backlog);
    List<Issue> findByIssuePriorityAndBacklog(Issue.IssuePriority issuePriority, Backlog backlog);
    List<Issue> findByExecutorAndBacklog(User executor, Backlog backlog);
    Optional<Issue> findById(Integer issueId);
    List<Issue> deleteByBacklog(Integer issueId);
    Issue findByTitleAndSprint(String issueTitle, Sprint sprint);
    List<Issue> findByTitleAndBacklog(String issueTitle, Backlog backlog);
}
