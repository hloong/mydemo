package com.hloong.mydemo.bean;

public class SizeBean {
private String name;//商品属性名称
private boolean nameIsSelect;//商品属性是否选中



public SizeBean(String name,boolean isSelect){
    super();
    this.name = name;
    this.nameIsSelect = isSelect;
}

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public boolean getNameIsSelect() {
	return nameIsSelect;
}
public void setNameIsSelect(boolean nameIsSelect) {
	this.nameIsSelect = nameIsSelect;
}

}
