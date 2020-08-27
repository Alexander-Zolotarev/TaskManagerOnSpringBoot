package com.project.entities.issue;

public enum WorkFlowIssue {
    OPEN_ISSUE("Open Issue"),
    INPROGRESS_ISSUE("InProgress Issue"),
    REVIEW_ISSUE("Review Issue"),
    TEST_ISSUE("Test Issue"),
    RESOLVED_ISSUE("Resolved Issue"),
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

