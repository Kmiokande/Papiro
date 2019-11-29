package com.kmiokande.papiro.models;

import android.content.ContentValues;

import java.io.Serializable;

public class Note implements Serializable {
    private Integer id;
    private String title;
    private String content;

    public Note() {
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

    public ContentValues getContentValues() {
        ContentValues cv = new ContentValues();

        cv.put("title", getTitle());
        cv.put("content", getContent());

        return cv;
    }
}
