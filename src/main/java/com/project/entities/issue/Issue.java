package com.project.entities.issue;

import com.project.entities.Backlog;
import com.project.entities.Sprint;
import com.project.entities.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "issues")
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "issue_type")
    private IssueType issueType;

    @ManyToOne
    @JoinColumn(name = "sprint_id")
    private Sprint sprint;

    @OneToOne
    @JoinColumn(name = "parent_issue_id")
    private Issue parentIssue;

    @Column(name = "date_create")
    private LocalDate dataCreate;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "executor_id")
    private User executor;

    @ManyToOne
    @JoinColumn(name = "reporter_id")
    private User reporter;

    @Column(name = "workflow")
    @Enumerated(value = EnumType.STRING)
    private WorkFlowIssue workFlowIssue;

    @Column(name = "issue_priority")
    @Enumerated(value = EnumType.STRING)
    private IssuePriority issuePriority;

    @ManyToOne
    @JoinColumn(name = "backlog_id")
    private Backlog backlog;

    public Issue() {

    }

    public Issue(String title, String description, IssueType issueType, IssuePriority issuePriority,
                 Backlog backlog,
                 User executor, User reporter) {
        this.title=title;
        this.description=description;
        this.issueType = issueType;
        this.issuePriority = issuePriority;
        this.backlog = backlog;
        this.workFlowIssue = WorkFlowIssue.OPEN_ISSUE;
        this.dataCreate = LocalDate.now();
        this.executor=executor;
        this.reporter=reporter;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public IssueType getIssueType() {
        return issueType;
    }

    public void setIssueType(IssueType issueType) {
        this.issueType = issueType;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    public Issue getParentIssue() {
        return parentIssue;
    }

    public void setParentIssue(Issue parentIssue) {
        this.parentIssue = parentIssue;
    }

    public LocalDate getDataCreate() {
        return dataCreate;
    }

    public void setDataCreate(LocalDate dataCreate) {
        this.dataCreate = dataCreate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getExecutor() {
        return executor;
    }

    public void setExecutor(User executor) {
        this.executor = executor;
    }

    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public WorkFlowIssue getWorkFlowIssue() {
        return workFlowIssue;
    }

    public void setWorkFlowIssue(WorkFlowIssue workFlowIssue) {
        this.workFlowIssue = workFlowIssue;
    }

    public IssuePriority getIssuePriority() {
        return issuePriority;
    }

    public void setIssuePriority(IssuePriority issuePriority) {
        this.issuePriority = issuePriority;
    }

    public Backlog getBacklog() {
        return backlog;
    }

    public void setBacklog(Backlog backlog) {
        this.backlog = backlog;
    }

    public enum WorkFlowIssue {
        OPEN_ISSUE("Open issue"),
        INPROGRESS_ISSUE("In progress issue"),
        REVIEW_ISSUE("Review issue"),
        TEST_ISSUE("Test issue"),
        RESOLVED_ISSUE("Resolved issue"),
        REOPENED_ISSUE("ReOpened Issue"),
        CLOSE_ISSUE("Close Issue");

        String title;

        WorkFlowIssue(String title){
            this.title = title;
        }

        WorkFlowIssue() {
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

}