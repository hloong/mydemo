package com.hloong.mydemo.bean;
public class Order {
    private String msg;
    private int code;
    private int type;
    private OrderLeft orderLeft;
    private OrderRight orderRight;
    private OrderMiddle orderMiddle;
    public Order(String msg,int code,int type,OrderLeft orderLeft,OrderMiddle orderMiddle,OrderRight orderRight) {
        super();
        this.msg = msg;
        this.code = code;
        this.type = type;
        if (type == 1) {
            this.orderLeft = orderLeft;
        }else if(type == 2) {
            this.orderRight = orderRight;
        }else {
            this.orderMiddle = orderMiddle;
        }
    }
    public OrderLeft getOrderLeft() {
        return orderLeft;
    }
    public void setOrderLeft(OrderLeft orderLeft) {
        this.orderLeft = orderLeft;
    }
    public OrderRight getOrderRight() {
        return orderRight;
    }
    public void setOrderRight(OrderRight orderRight) {
        this.orderRight = orderRight;
    }
    public OrderMiddle getOrderMiddle() {
        return orderMiddle;
    }
    public void setOrderMiddle(OrderMiddle orderMiddle) {
        this.orderMiddle = orderMiddle;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    
}
