package com.project.controllers;

import com.project.entities.Backlog;
import com.project.entities.Project;
import com.project.entities.Sprint;
import com.project.entities.User;
import com.project.entities.Issue;
import com.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class IssueController {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SprintRepository sprintRepository;

    @PostMapping("project/{id}/issueListInProject/titleFilter")
    public String titleFilter(@PathVariable("id") Integer projectId, @RequestParam String title, Model model) {

        Project project = projectRepository.findById(projectId).get();
        Backlog backlog = backlogRepository.findByProject(project);

        if(title.isEmpty()) {
            model.addAttribute("issues", issueRepository.findByBacklog(backlog));
        } else {
            model.addAttribute("issues", issueRepository.findByTitleAndBacklog(title, backlog));
        }
        model
                .addAttribute("projectID",projectRepository.findById(projectId).get().getId())
                .addAttribute("title",projectRepository.findById(projectId).get().getTitle())
                .addAttribute("description", projectRepository.findById(projectId).get().getDescription())
                .addAttribute("supervisorID", projectRepository.findById(projectId).get().getSupervisor().getFirstName()
                        .concat(" ")
                        .concat(projectRepository.findById(projectId).get().getSupervisor().getLastName()))
                .addAttribute("subdivision", projectRepository.findById(projectId).get().getSubdivision())
                .addAttribute("adminID", projectRepository.findById(projectId).get().getAdmin().getFirstName()
                        .concat(" ")
                        .concat(projectRepository.findById(projectId).get().getAdmin().getLastName()));

        model.addAttribute("sprints", sprintRepository.findByProject(project));
        model.addAttribute("backlogTitle", backlogRepository.findByProject(project).getTitle());
        model.addAttribute("users", userRepository.findAll());

        return "issueListInProject";
    }

    @PostMapping("project/{id}/issueListInProject/priorityFilter")
    public String priorityFilter(@PathVariable("id") Integer projectId,
                                 @RequestParam Issue.IssuePriority issuePriority,
                                 Model model) {
        Project project = projectRepository.findById(projectId).get();
        Backlog backlog = backlogRepository.findByProject(project);

        if(issuePriority == null) {
            model.addAttribute("issues", issueRepository.findByBacklog(backlog));
        }

        model.addAttribute("issues", issueRepository.findByIssuePriorityAndBacklog(issuePriority, backlog));

        model
                .addAttribute("projectID",projectRepository.findById(projectId).get().getId())
                .addAttribute("title",projectRepository.findById(projectId).get().getTitle())
                .addAttribute("description", projectRepository.findById(projectId).get().getDescription())
                .addAttribute("supervisorID", projectRepository.findById(projectId).get().getSupervisor().getFirstName()
                        .concat(" ")
                        .concat(projectRepository.findById(projectId).get().getSupervisor().getLastName()))
                .addAttribute("subdivision", projectRepository.findById(projectId).get().getSubdivision())
                .addAttribute("adminID", projectRepository.findById(projectId).get().getAdmin().getFirstName()
                        .concat(" ")
                        .concat(projectRepository.findById(projectId).get().getAdmin().getLastName()));

        model.addAttribute("sprints", sprintRepository.findByProject(project));
        model.addAttribute("backlogTitle", backlogRepository.findByProject(project).getTitle());
        model.addAttribute("users", userRepository.findAll());

        return "issueListInProject";
    }

    @PostMapping("project/{id}/issueListInProject/dateFilter")
    public String dateFilter(@PathVariable("id") Integer projectId,
                             @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                             Model model) {

        Project project = projectRepository.findById(projectId).get();
        Backlog backlog = backlogRepository.findByProject(project);

        if(date==null) {
            model.addAttribute("issues", issueRepository.findByBacklog(backlog));
        } else {
            model.addAttribute("issues", issueRepository.findByDataCreateAndBacklog(date, backlog));
        }

        model
                .addAttribute("projectID",projectRepository.findById(projectId).get().getId())
                .addAttribute("title",projectRepository.findById(projectId).get().getTitle())
                .addAttribute("description", projectRepository.findById(projectId).get().getDescription())
                .addAttribute("supervisorID", projectRepository.findById(projectId).get().getSupervisor().getFirstName()
                        .concat(" ")
                        .concat(projectRepository.findById(projectId).get().getSupervisor().getLastName()))
                .addAttribute("subdivision", projectRepository.findById(projectId).get().getSubdivision())
                .addAttribute("adminID", projectRepository.findById(projectId).get().getAdmin().getFirstName()
                        .concat(" ")
                        .concat(projectRepository.findById(projectId).get().getAdmin().getLastName()));

        model.addAttribute("sprints", sprintRepository.findByProject(project));
        model.addAttribute("backlogTitle", backlogRepository.findByProject(project).getTitle());
        model.addAttribute("users", userRepository.findAll());

        return "issueListInProject";
    }

    @PostMapping("project/{id}/issueListInProject/executorFilter")
    public String executorFilter(@PathVariable("id") Integer projectId,
                                 @RequestParam String executor, Model model) {

        String[] firstNameAndLastName = executor.split(" ");
        User executorIssues = userRepository.findByFirstNameAndLastName(firstNameAndLastName[0], firstNameAndLastName[1]);

        Project project = projectRepository.findById(projectId).get();
        Backlog backlog = backlogRepository.findByProject(project);

        if(executor.isEmpty()) {
            model.addAttribute("issues", issueRepository.findByBacklog(backlog));
        } else {
            model.addAttribute("issues", issueRepository.findByExecutorAndBacklog(executorIssues, backlog));
        }

        model
                .addAttribute("projectID",projectRepository.findById(projectId).get().getId())
                .addAttribute("title",projectRepository.findById(projectId).get().getTitle())
                .addAttribute("description", projectRepository.findById(projectId).get().getDescription())
                .addAttribute("supervisorID", projectRepository.findById(projectId).get().getSupervisor().getFirstName()
                        .concat(" ")
                        .concat(projectRepository.findById(projectId).get().getSupervisor().getLastName()))
                .addAttribute("subdivision", projectRepository.findById(projectId).get().getSubdivision())
                .addAttribute("adminID", projectRepository.findById(projectId).get().getAdmin().getFirstName()
                        .concat(" ")
                        .concat(projectRepository.findById(projectId).get().getAdmin().getLastName()));

        model.addAttribute("sprints", sprintRepository.findByProject(project));
        model.addAttribute("backlogTitle", backlogRepository.findByProject(project).getTitle());
        model.addAttribute("users", userRepository.findAll());

        return "issueListInProject";
    }

    @PostMapping("project/{id}/issueListInProject/reporterFilter")
    public String reporterFilter(@PathVariable("id") Integer projectId,
                                 @RequestParam String reporter, Model model) {

        String[] firstNameAndLastName = reporter.split(" ");
        User reporterIssues = userRepository.findByFirstNameAndLastName(firstNameAndLastName[0], firstNameAndLastName[1]);

        Project project = projectRepository.findById(projectId).get();
        Backlog backlog = backlogRepository.findByProject(project);

        if(reporter.isEmpty()) {
            model.addAttribute("issues", issueRepository.findByBacklog(backlog));
        } else {
            model.addAttribute("issues", issueRepository.findByReporterAndBacklog(reporterIssues, backlog));
        }

        model
                .addAttribute("projectID",projectRepository.findById(projectId).get().getId())
                .addAttribute("title",projectRepository.findById(projectId).get().getTitle())
                .addAttribute("description", projectRepository.findById(projectId).get().getDescription())
                .addAttribute("supervisorID", projectRepository.findById(projectId).get().getSupervisor().getFirstName()
                        .concat(" ")
                        .concat(projectRepository.findById(projectId).get().getSupervisor().getLastName()))
                .addAttribute("subdivision", projectRepository.findById(projectId).get().getSubdivision())
                .addAttribute("adminID", projectRepository.findById(projectId).get().getAdmin().getFirstName()
                        .concat(" ")
                        .concat(projectRepository.findById(projectId).get().getAdmin().getLastName()));

        model.addAttribute("sprints", sprintRepository.findByProject(project));
        model.addAttribute("backlogTitle", backlogRepository.findByProject(project).getTitle());
        model.addAttribute("users", userRepository.findAll());

        return "issueListInProject";
    }
    @PostMapping("/project/{id}/issueListInProject/moveToSprint/issue/{issue.id}")
    public String moveIssueToSprint(@PathVariable("id") Integer projectId,
                                    @PathVariable("issue.id") Integer issueId,
                                    @RequestParam String parentIssueName,
                                    @RequestParam(name = "sprintInProject") String sprintName,
                                    Model model) {

        Project project = projectRepository.findById(projectId).get();
        Backlog backlog = backlogRepository.findByProject(project);

        Sprint sprint = sprintRepository.findByTitle(sprintName);
        Issue parentIssue = issueRepository.findByTitleAndBacklog(parentIssueName, backlog).get(0);

        Issue issue = issueRepository.findById(issueId).get();

        issue.setSprint(sprint);
        issue.setParentIssue(parentIssue);

        issue.setBacklog(null);
        issueRepository.save(issue);

        model.addAttribute("issues", issueRepository.findByBacklog(backlog));
        model.addAttribute("issuesInSprint", issueRepository.findBySprint(sprint));

        model
                .addAttribute("projectID",projectRepository.findById(projectId).get().getId())
                .addAttribute("title",projectRepository.findById(projectId).get().getTitle())
                .addAttribute("description", projectRepository.findById(projectId).get().getDescription())
                .addAttribute("supervisorID", projectRepository.findById(projectId).get().getSupervisor().getFirstName()
                        .concat(" ")
                        .concat(projectRepository.findById(projectId).get().getSupervisor().getLastName()))
                .addAttribute("subdivision", projectRepository.findById(projectId).get().getSubdivision())
                .addAttribute("adminID", projectRepository.findById(projectId).get().getAdmin().getFirstName()
                        .concat(" ")
                        .concat(projectRepository.findById(projectId).get().getAdmin().getLastName()));

        model.addAttribute("sprints", sprintRepository.findByProject(project));
        model.addAttribute("backlogTitle", backlogRepository.findByProject(project).getTitle());
        model.addAttribute("users", userRepository.findAll());

        return "redirect:/project/{id}/issueListInProject";
    }
}
