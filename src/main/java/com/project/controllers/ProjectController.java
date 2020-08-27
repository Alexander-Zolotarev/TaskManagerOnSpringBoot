package com.project.controllers;

import com.project.entities.Backlog;
import com.project.entities.Project;
import com.project.entities.User;
import com.project.entities.issue.Issue;
import com.project.repository.BacklogRepository;
import com.project.repository.IssueRepository;
import com.project.repository.ProjectRepository;
import com.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProjectController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private IssueRepository issueRepository;

    @GetMapping("createProject")
    public String getUsers(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);

        return "createProject";
    }

    @GetMapping("addUser")
    public String addUser() {
        return "/addUser";
    }

    @GetMapping("/projectsList")
    public String backToProjectsList(Model model) {
        Iterable<Project> projects = projectRepository.findAll();
        model.addAttribute("projects", projects);

        return "projectsList";
    }

    @GetMapping("/project/{id}/projectInfo")
    public String getProjectById(@PathVariable("id") Integer projectId,
                                 Model model) {

        model
                .addAttribute("projectID",projectRepository.findById(projectId).get().getId())
                .addAttribute("title",projectRepository.findById(projectId).get().getTitle())
                .addAttribute("description", projectRepository.findById(projectId).get().getDescription())
                .addAttribute("supervisorID", projectRepository.findById(projectId).get().getSupervisor())
                .addAttribute("subdivision", projectRepository.findById(projectId).get().getSubdivision())
                .addAttribute("adminID", projectRepository.findById(projectId).get().getAdmin());

        model.addAttribute("backlogTitle", backlogRepository.findByProjectId(projectId).getTitle());

        Integer backlogId = backlogRepository.findByProjectId(projectId).getId();

        List<Issue> issues = issueRepository.findByBacklog(backlogId);
        model.addAttribute("issues", issues);

        return "projectInfo";
    }

    @PostMapping("createProject")
    public String createProject(@RequestParam String title,
                                @RequestParam String description,
                                @RequestParam String subdivision,
                                @RequestParam String supervisor,
                                @RequestParam String admin,
                                Model model) {

        Integer supervisorId = Integer.parseInt(supervisor);
        Integer adminId = Integer.parseInt(admin);
        Project project = new Project(title, description, subdivision,supervisorId,adminId);
        projectRepository.save(project);

        Integer projectId = project.getId();

        Backlog backlog = new Backlog("Backlog", projectId);
        backlogRepository.save(backlog);

        model
                .addAttribute("projectID",projectId)
                .addAttribute("title",title)
                .addAttribute("description", description)
                .addAttribute("supervisorID", supervisorId)
                .addAttribute("subdivision", subdivision)
                .addAttribute("adminID", adminId);

        model.addAttribute("backlogTitle", backlog.getTitle());

        return "redirect:/projectsList";
    }

    @PostMapping("project/{id}/projectInfo")
    public String createIssue (@PathVariable("id") Integer projectId,
                               @RequestParam String title,
                               @RequestParam String description,
                               @RequestParam String executor,
                               @RequestParam String reporter,
                               @RequestParam String issueType,
                               Model model
                               ) {

        Integer executorId = Integer.parseInt(executor);
        Integer reporterId = Integer.parseInt(reporter);

        Integer backlogId = backlogRepository.findByProjectId(projectId).getId();
        Issue issue = new Issue(title, description, issueType, backlogId, executorId, reporterId);
        issueRepository.save(issue);

        List<Issue> issues = issueRepository.findByBacklog(backlogId);
        model.addAttribute("issues", issues);

        model
                .addAttribute("projectID",projectRepository.findById(projectId).get().getId())
                .addAttribute("title",projectRepository.findById(projectId).get().getTitle())
                .addAttribute("description", projectRepository.findById(projectId).get().getDescription())
                .addAttribute("supervisorID", projectRepository.findById(projectId).get().getSupervisor())
                .addAttribute("subdivision", projectRepository.findById(projectId).get().getSubdivision())
                .addAttribute("adminID", projectRepository.findById(projectId).get().getAdmin());

        model.addAttribute("backlogTitle", backlogRepository.findByProjectId(projectId).getTitle());

        return "projectInfo";
    }

    @GetMapping(value = "project/{id}/issues")
    public String getIssues(@PathVariable("id") Integer id, Model model) {

        Integer backlogId = backlogRepository.findByProjectId(id).getId();

        List<Issue> issues = issueRepository.findByBacklog(backlogId);

        model.addAttribute("issues", issues);
        return "projectInfo";
    }

    @PostMapping("project/{id}/projectInfo/titleFilter")
    public String titleFilter(@PathVariable("id") Integer projectId, @RequestParam String title, Model model) {
        Iterable<Issue> issues;
        Integer backlogId = backlogRepository.findByProjectId(projectId).getId();

        if(title!=null & !title.isEmpty()) {
            issues = issueRepository.findByTitle(title);
        } else {
            issues = issueRepository.findByBacklog(backlogId);
        }

        model
                .addAttribute("projectID",projectRepository.findById(projectId).get().getId())
                .addAttribute("title",projectRepository.findById(projectId).get().getTitle())
                .addAttribute("description", projectRepository.findById(projectId).get().getDescription())
                .addAttribute("supervisorID", projectRepository.findById(projectId).get().getSupervisor())
                .addAttribute("subdivision", projectRepository.findById(projectId).get().getSubdivision())
                .addAttribute("adminID", projectRepository.findById(projectId).get().getAdmin());

        model.addAttribute("backlogTitle", backlogRepository.findByProjectId(projectId).getTitle());

        model.addAttribute("issues", issues);

        return "projectInfo";
    }

    @GetMapping("/project/{id}/issueListInProject")
    public String goToIssueList(@PathVariable("id") Integer projectId,
                                Model model) {
        Integer backlogId = backlogRepository.findByProjectId(projectId).getId();

        model
                .addAttribute("projectID",projectRepository.findById(projectId).get().getId())
                .addAttribute("title",projectRepository.findById(projectId).get().getTitle())
                .addAttribute("description", projectRepository.findById(projectId).get().getDescription())
                .addAttribute("supervisorID", projectRepository.findById(projectId).get().getSupervisor())
                .addAttribute("subdivision", projectRepository.findById(projectId).get().getSubdivision())
                .addAttribute("adminID", projectRepository.findById(projectId).get().getAdmin());

        model.addAttribute("backlogTitle", backlogRepository.findByProjectId(projectId).getTitle());

        model.addAttribute("issues", issueRepository.findByBacklog(backlogId));
        return "issueListInProject";
    }


}
