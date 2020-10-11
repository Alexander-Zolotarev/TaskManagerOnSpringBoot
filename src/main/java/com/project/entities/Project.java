package com.project.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="title")
    @Size(min = 2, max = 30, message = "Title should be from 2 to 30 symbols")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="subdivision")
    @Size(min = 10, max = 50, message = "Subdivision should be from 10 to 50 symbols")
    private String subdivision;

    @OneToOne
    @JoinColumn(name = "supervisor_id")
    //@NotBlank(message = "Choose supervisor")
    private User supervisor;

    @OneToOne
    @JoinColumn(name="admin_id")
    //@NotBlank(message = "Choose admin")
    private User admin;

    public Project(String title, String description, String subdivision, User supervisor, User admin) {
        this.title=title;
        this.description=description;
        this.subdivision=subdivision;
        this.supervisor=supervisor;
        this.admin=admin;
    }

    public Project() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision;
    }

    public User getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(User supervisor) {
        this.supervisor = supervisor;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }
}
