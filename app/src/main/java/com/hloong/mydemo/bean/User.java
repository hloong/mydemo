package com.hloong.mydemo.bean;

/**
 * Created by hloong on 2016/11/11.
 */

public class User {
    private int img;
    private String userName;
    private String pinyin;
    private String firstLetter;

    public User(){

    }

    public User(String firstLetter,int img,String pinyin,String userName){
        this.firstLetter = firstLetter;
        this.img = img;
        this.pinyin = pinyin;
        this.userName = userName;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }
}
