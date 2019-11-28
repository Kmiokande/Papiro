package com.kmiokande.papiro.models;

import android.content.ContentValues;

import java.util.Calendar;

public class Note {
    private Integer id;
    private String title;
    private String content;
    private Calendar dateCreated;
    private Calendar dateModified;

    public Note() {
    }

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
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

    public ContentValues getContentValues() {
        ContentValues cv = new ContentValues();

        cv.put("title", getTitle());
        cv.put("content", getContent());

        return cv;
    }
}
