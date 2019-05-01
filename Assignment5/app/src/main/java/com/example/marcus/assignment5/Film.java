package com.example.marcus.assignment5;

public class Film {

    private String name, date, file;

    public Film(String name, String date, String file){

        this.name = name;
        this.date = date;
        this.file = file;
    }

    public String getName(){

        return name;
    }

    public String getDate(){

        return date;
    }

    public String getFile(){

        return file;
    }

    public void setName(String name){

        this.name = name;
    }

    public void setDate(String date){

        this.date = date;
    }

    public void setFile(String file){

        this.file = file;
    }

    public String toString(){

        return name;
    }

}
