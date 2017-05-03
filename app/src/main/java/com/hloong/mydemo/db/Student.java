package com.hloong.mydemo.db;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by hloong on 2017/5/2.
 */

public class Student extends RealmObject {
    @PrimaryKey
    private String name;
    private long password;
    private String nickname;
    private RealmList<Book> books;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPassword() {
        return password;
    }

    public void setPassword(long password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public RealmList<Book> getBooks() {
        return books;
    }

    public void setBooks(RealmList<Book> books) {
        this.books = books;
    }
}
