package com.hloong.mydemo.bean;

import java.util.List;

public class ListBean {
    private String title;
    private List<SizeBean> list;
    
    public ListBean(String title,List<SizeBean> list) {
        super();
        this.title = title;
        this.list = list;
    }
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public List<SizeBean> getList() {
        return list;
    }
    public void setList(List<SizeBean> list) {
        this.list = list;
    }
    
    
}
