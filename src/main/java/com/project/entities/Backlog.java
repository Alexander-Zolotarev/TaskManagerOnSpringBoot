package com.project.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "backlogs")
public class Backlog{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "title")
    @Size(min = 2, max = 20, message = "Backlog should be from 2 to 20 symbols")
    private String title;

    @OneToOne
    @JoinColumn(name = "project_id")
    @NotBlank(message = "Choose the project")
    private Project project;

    public Backlog() {

    }

    public Backlog(String title, Project project) {
        this.title = title;
        this.project = project;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
