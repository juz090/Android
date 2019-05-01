package edu.chapman.dev.a4activities.switchactivities;


import android.app.Application;

import java.util.ArrayList;

public class MyApplication extends Application {
    // Initializing variables
    private String inputName = "";
    private String inputEmail = "";
    public ArrayList<String> myList = new ArrayList<String>();

    public String getInputName() {
        return inputName;
    }
    public void setInputName(String inputName) {
        this.inputName = inputName;
    }
    public String getInputEmail() {
        return inputEmail;
    }
    public void setInputEmail(String inputEmail) {
        this.inputEmail = inputEmail;
    }




}