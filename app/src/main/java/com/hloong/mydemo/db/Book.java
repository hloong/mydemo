package com.hloong.mydemo.db;

import io.realm.RealmObject;

/**
 * Created by hloong on 2017/5/2.
 */

public class Book extends RealmObject {
    private String name;
    private String author;
    private String  publishing;

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public java.lang.String getAuthor() {
        return author;
    }

    public void setAuthor(java.lang.String author) {
        this.author = author;
    }

    public String getPublishing() {
        return publishing;
    }

    public void setPublishing(String publishing) {
        this.publishing = publishing;
    }
}
