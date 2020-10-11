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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;

@Controller
@ControllerAdvice
public class ProjectController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private SprintRepository sprintRepository;

    @GetMapping
    public String greeting() {
        return "greeting";
    }

    @GetMapping("createProject")
    public String getUsers(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);

        return "createProject";
    }

    @GetMapping(value = "createProject", params = "tpl")
    public String backToCreateProject() {
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

        Project project = projectRepository.findById(projectId).get();
        Integer supervisorId = projectRepository.findById(projectId).get().getSupervisor().getId();
        Integer adminId = projectRepository.findById(projectId).get().getAdmin().getId();

        model
                .addAttribute("projectID",projectRepository.findById(projectId).get().getId())
                .addAttribute("title",projectRepository.findById(projectId).get().getTitle())
                .addAttribute("description", projectRepository.findById(projectId).get().getDescription())
                .addAttribute("supervisor", userRepository.findById(supervisorId).get().getFirstName()
                        .concat(" ")
                        .concat( userRepository.findById(supervisorId).get().getLastName()))
                .addAttribute("subdivision", projectRepository.findById(projectId).get().getSubdivision())
                .addAttribute("admin", userRepository.findById(supervisorId).get().getFirstName()
                        .concat(" ")
                        .concat( userRepository.findById(adminId).get().getLastName()));

        model.addAttribute("backlogTitle", backlogRepository.findByProject(project).getTitle());

        Backlog backlog = backlogRepository.findByProject(project);

        List<Issue> issues = issueRepository.findByBacklog(backlog);
        List<Sprint> sprints = sprintRepository.findByProject(project);
        List<Issue.WorkFlowIssue> workFlowsIssue= new ArrayList<>();
        workFlowsIssue.add(Issue.WorkFlowIssue.OPEN_ISSUE);
        workFlowsIssue.add(Issue.WorkFlowIssue.INPROGRESS_ISSUE);
        workFlowsIssue.add(Issue.WorkFlowIssue.REVIEW_ISSUE);
        workFlowsIssue.add(Issue.WorkFlowIssue.TEST_ISSUE);
        workFlowsIssue.add(Issue.WorkFlowIssue.RESOLVED_ISSUE);
        workFlowsIssue.add(Issue.WorkFlowIssue.REOPENED_ISSUE);
        workFlowsIssue.add(Issue.WorkFlowIssue.CLOSE_ISSUE);

        model.addAttribute("sprints", sprints);
        model.addAttribute("issues", issues);
        model.addAttribute("workflows", workFlowsIssue);
        model.addAttribute("users", userRepository.findAll());

        return "projectInfo";
    }

    @PostMapping("createProject")
    public String createProject(@ModelAttribute(name = "project") Project project,
//                                @RequestParam String title,
//                                @RequestParam String description,
//                                @RequestParam String subdivision,
//                                @RequestParam String supervisor,
//                                @RequestParam String admin,
                                BindingResult bindingResult,
                                Model model) {

        User admin = project.getAdmin();
        User supervisor = project.getSupervisor();
        String title = project.getTitle();


        if(bindingResult.hasErrors()) {
            //List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            Map<String, String> errorsMap = ControllerUtil.getErrors(bindingResult);
            model.addAttribute("errors", errorsMap);
            model.addAttribute("project", project);
            return "createProject";
        } else {

//        User supervisorUser = userRepository.findById(Integer.parseInt(supervisor)).get();
//        User adminUser = userRepository.findById(Integer.parseInt(admin)).get();
//
//        String[] supervisor = supervisorName.split(" ");
//        User supervisorProject = userRepository.findByFirstNameAndLastName(supervisor[0], supervisor[1]);
//
//        String[] admin = adminName.split(" ");
//        User adminProject = userRepository.findByFirstNameAndLastName(admin[0], admin[1]);


//            Project projectCur = new Project(
//                    title,
//                    description,
//                    subdivision,
//                    supervisorProject,
//                    adminProject
//            );
            projectRepository.save(project);

            Integer projectId = project.getId();

            Backlog backlog = new Backlog("Backlog" + " " + projectId, project);
            backlogRepository.save(backlog);

            model
                    .addAttribute("projectID", projectId)
                    .addAttribute("title", project.getTitle())
                    .addAttribute("description", project.getDescription())
                    .addAttribute("supervisorID", project.getSupervisor())
                    .addAttribute("subdivision", project.getSubdivision())
                    .addAttribute("adminID", project.getAdmin());

            model.addAttribute("backlogTitle", backlog.getTitle());

            return "redirect:/projectsList";
        }
    }

    @PostMapping("project/{id}/projectInfo")
    public String createIssue (@PathVariable("id") Integer projectId,
                               @RequestParam String title,
                               @RequestParam String description,
                               @RequestParam String executor,
                               @RequestParam String reporter,
                               @RequestParam Issue.IssuePriority issuePriority,
                               @RequestParam Issue.IssueType issueType,
                               Model model
                               ) {

        Project project = projectRepository.findById(projectId).get();
        User executorUser = userRepository.findById(Integer.parseInt(executor)).get();
        User reporterUser = userRepository.findById(Integer.parseInt(reporter)).get();

        Backlog backlog = backlogRepository.findByProject(project);
        Issue issue = new Issue(title, description, issueType, issuePriority, backlog, executorUser, reporterUser);
        issueRepository.save(issue);

        List<Issue> issues = issueRepository.findByBacklog(backlogRepository.findByProject(project));
        List<Sprint> sprints = sprintRepository.findByProject(project);
        model.addAttribute("issues", issues);

        model
                .addAttribute("projectID",projectRepository.findById(projectId).get().getId())
                .addAttribute("title",projectRepository.findById(projectId).get().getTitle())
                .addAttribute("description", projectRepository.findById(projectId).get().getDescription())
                .addAttribute("supervisorID", projectRepository.findById(projectId).get().getSupervisor())
                .addAttribute("subdivision", projectRepository.findById(projectId).get().getSubdivision())
                .addAttribute("adminID", projectRepository.findById(projectId).get().getAdmin());

        model.addAttribute("backlogTitle", backlogRepository.findByProject(project).getTitle());
        model.addAttribute("sprints", sprints);

        return "redirect:/project/{id}/projectInfo";
    }

    @GetMapping("project/{id}/issues")
    public String getIssues(@PathVariable("id") Integer projectId, Model model) {

        Project project = projectRepository.findById(projectId).get();
        Backlog backlog = backlogRepository.findByProject(project);

        List<Issue> issues = issueRepository.findByBacklog(backlog);

        model.addAttribute("issues", issues);
        return "projectInfo";
    }

    @GetMapping("/project/{id}/issueListInProject")
    public String goToIssueList(@PathVariable("id") Integer projectId,
                                Model model) {
        Project project = projectRepository.findById(projectId).get();
        Backlog backlog = backlogRepository.findByProject(project);
        List<Sprint> sprints = sprintRepository.findByProject(project);

        Set<User> allExecutorsIssuesInProject = new HashSet<>();
        Set<User> allReportersIssuesInProject = new HashSet<>();

        List<Issue> allIssuesInBacklog = issueRepository.findByBacklog(backlog);
        for (Issue issue : allIssuesInBacklog) {
            allExecutorsIssuesInProject.add(issue.getExecutor());
            allReportersIssuesInProject.add(issue.getReporter());
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

        model.addAttribute("backlogTitle", backlogRepository.findByProject(project).getTitle());
        model.addAttribute("executorsInProject", allExecutorsIssuesInProject);
        model.addAttribute("reportersInProject", allReportersIssuesInProject);

        model.addAttribute("issues", issueRepository.findByBacklog(backlog));
        model.addAttribute("parentIssue", issueRepository.findByBacklog(backlog));
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("sprints", sprints);
        return "issueListInProject";
    }

    @PostMapping("project/{id}/projectInfo/createSprint")
    public String createSprint(@PathVariable("id") Integer projectId,
                                @RequestParam String title,
                                @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                Model model) {

        Project project = projectRepository.findById(projectId).get();
        Backlog backlog = backlogRepository.findByProject(project);

        Sprint sprint = new Sprint(project, title, startDate ,endDate);
        sprintRepository.save(sprint);

        List<Sprint> sprints = sprintRepository.findByProject(project);

        model
                .addAttribute("projectID",projectRepository.findById(projectId).get().getId())
                .addAttribute("title",projectRepository.findById(projectId).get().getTitle())
                .addAttribute("description", projectRepository.findById(projectId).get().getDescription())
                .addAttribute("supervisorID", projectRepository.findById(projectId).get().getSupervisor())
                .addAttribute("subdivision", projectRepository.findById(projectId).get().getSubdivision())
                .addAttribute("adminID", projectRepository.findById(projectId).get().getAdmin());

        model.addAttribute("backlogTitle", backlogRepository.findByProject(project).getTitle());

        model.addAttribute("sprints", sprints);
        model.addAttribute("issues", issueRepository.findByBacklog(backlog));

        return "redirect:/project/{id}/projectInfo";
    }

    @PostMapping("project/{id}/projectInfo/changeWorkFlow/issue/{issue.id}")
    public String changeWorkflow(Model model,
                                 @PathVariable("id") Integer projectId,
                                 @PathVariable("issue.id") Integer issueId,
                                 @RequestParam Issue.WorkFlowIssue workflow
                                 ) {

        Project project = projectRepository.findById(projectId).get();
        Backlog backlog = backlogRepository.findByProject(project);

        Issue issue = issueRepository.findById(issueId).get();
        if (workflow != null) {
            issue.setWorkFlowIssue(workflow);
            issueRepository.save(issue);
        }
        model.addAttribute("issues", issueRepository.findByBacklog(backlog));

        model
                .addAttribute("projectID",projectRepository.findById(projectId).get().getId())
                .addAttribute("title",projectRepository.findById(projectId).get().getTitle())
                .addAttribute("description", projectRepository.findById(projectId).get().getDescription())
                .addAttribute("supervisorID", projectRepository.findById(projectId).get().getSupervisor())
                .addAttribute("subdivision", projectRepository.findById(projectId).get().getSubdivision())
                .addAttribute("adminID", projectRepository.findById(projectId).get().getAdmin());

        model.addAttribute("backlogTitle", backlogRepository.findByProject(project).getTitle());
        model.addAttribute("users", userRepository.findAll());

        return "redirect:/project/{id}/projectInfo";
    }
}