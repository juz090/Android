package com.example.marcus.asssignment_3;
import java.util.ArrayList;

public class Class {

    private String name;
    private int num;
    private ArrayList<Student> students = new ArrayList<>();

    public Class(String name, int num)
    {
        this.name = name;
        this.num = num;

    }

    public String getName(){

        return name;
    }

    public int getNum()
    {
        return num;
    }

    public Student getStudent(int i)
    {
        return students.get(i);
    }

    public void setName(String name){

        this.name = name;
    }

    public void setNum(int num){
        this.num = num;
    }

    public void addStudent(String studentFirst, String studentLast, int studentID)
    {
        Student temp = new Student(studentFirst, studentLast, studentID);
        students.add(temp);
    }

    public String printStudents(){

        if (this.students.size() == 0){
            return "None";
        }

        String temp = "";
        for (int i = 0; i < this.students.size(); ++i){

            temp = temp + this.students.get(i).getLastName() + ", " + this.students.get(i).getFirstName() + "\n";
        }

        return temp;
    }

    public String toString()
    {
        return name;
    }

}
