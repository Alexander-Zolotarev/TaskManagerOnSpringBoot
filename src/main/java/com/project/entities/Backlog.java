package com.project.entities;

import javax.persistence.*;

@Entity
@Table(name = "backlogs")
public class Backlog{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "project_id")
    private Integer projectId;

    public Backlog() {

    }

    public Backlog(String title, Integer projectId) {
        this.title = title;
        this.projectId = projectId;
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

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}
