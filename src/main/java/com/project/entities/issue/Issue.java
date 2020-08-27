package com.project.entities.issue;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "issues")
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "issue_type")
    private String issueType;

    @Column(name = "parent_issue_id")
    private Integer parentIssue;

    @Column(name = "date_create")
    private Date dataCreate;

    @Column(name = "description")
    private String description;

    @Column(name = "executor_id")
    private Integer executor;

    @Column(name = "reporter_id")
    private Integer reporter;

    @Enumerated(value = EnumType.ORDINAL)
    private WorkFlowIssue workFlowIssue;

    @Column(name = "issue_priority")
    @Enumerated(value = EnumType.STRING)
    private IssuePriority issuePriority;

    @Column(name = "backlog_id")
    private Integer backlog;

    public Issue() {

    }

    public Issue(String title, String description, String issueType,
                 Integer backlog,
                 Integer executor, Integer reporter) {
        this.title=title;
        this.description=description;
        this.issueType = issueType;
        this.backlog = backlog;
        this.workFlowIssue = WorkFlowIssue.OPEN_ISSUE;
        this.dataCreate = new Date();
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

    public Integer getParentIssue() {
        return parentIssue;
    }

    public void setParentIssue(Integer parentIssue) {
        this.parentIssue = parentIssue;
    }

    public Date getDataCreate() {
        return dataCreate;
    }

    public void setDataCreate(Date dataCreate) {
        this.dataCreate = dataCreate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getExecutor() {
        return executor;
    }

    public void setExecutor(Integer executor) {
        this.executor = executor;
    }

    public Integer getReporter() {
        return reporter;
    }

    public void setReporter(Integer reporter) {
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

    public Integer getBacklog() {
        return backlog;
    }

    public void setBacklog(Integer backlog) {
        this.backlog = backlog;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }
}