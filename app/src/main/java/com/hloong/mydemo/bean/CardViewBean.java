package com.hloong.mydemo.bean;

/**
 * Created by hl on 16/8/10.
 */
public class CardViewBean {
    private int color;
    private String title;

    public CardViewBean(){

    }
    public CardViewBean(int color,String title){
        this.color = color;
        this.title = title;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
