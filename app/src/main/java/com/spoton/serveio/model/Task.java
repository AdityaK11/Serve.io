package com.spoton.serveio.model;

public class Task {
    Double id;
    String title;
    String description;
    String ngoId;
    String ngoLocation;
    String taskLocation;

    public Task() {
    }

    public Task(Double id, String title, String description, String ngoId, String ngoLocation, String taskLocation) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.ngoId = ngoId;
        this.ngoLocation = ngoLocation;
        this.taskLocation = taskLocation;
    }

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
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

    public String getNgoId() {
        return ngoId;
    }

    public void setNgoId(String ngoId) {
        this.ngoId = ngoId;
    }

    public String getNgoLocation() {
        return ngoLocation;
    }

    public void setNgoLocation(String ngoLocation) {
        this.ngoLocation = ngoLocation;
    }

    public String getTaskLocation() {
        return taskLocation;
    }

    public void setTaskLocation(String taskLocation) {
        this.taskLocation = taskLocation;
    }
}
