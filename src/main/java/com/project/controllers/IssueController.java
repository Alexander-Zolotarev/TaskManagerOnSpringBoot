package com.project.controllers;

import com.project.entities.Backlog;
import com.project.entities.Project;
import com.project.entities.issue.Issue;
import com.project.entities.issue.IssueType;
import com.project.repository.BacklogRepository;
import com.project.repository.IssueRepository;
import com.project.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IssueController {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BacklogRepository backlogRepository;

//    @GetMapping("/issueListInProject")
//    public String listOfIssue(Model model) {
//        return "issueListInProject";
//    }
//    @PostMapping("titleFilter")
//    public String titleFilter() {
//         return "projectInfo";
//    }
}
