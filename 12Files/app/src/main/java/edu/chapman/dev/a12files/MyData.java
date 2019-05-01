package edu.chapman.dev.a12files;


import java.io.Serializable;


public class MyData implements Serializable {


    private static final long serialVersionUID = -1838868333122192701L;
    private int num;
    private String name;
    private double val;

    public MyData(int num, String name, double val) {
        this.num = num;
        this.name = name;
        this.val = val;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getVal() {
        return val;
    }

    public void setVal(double val) {
        this.val = val;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }





}
