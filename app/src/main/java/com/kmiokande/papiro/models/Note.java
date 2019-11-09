package com.kmiokande.papiro.models;

import java.util.Calendar;

public class Note {
    private Integer id;
    private String title;
    private String content;
    private Calendar dateCreated;
    private Calendar dateModified;

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Note(Integer id, String title, String content, Calendar dateCreated, Calendar dateModified) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Calendar getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Calendar dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Calendar getDateModified() {
        return dateModified;
    }

    public void setDateModified(Calendar dateModified) {
        this.dateModified = dateModified;
    }
}
