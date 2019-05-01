package com.example.marcus.asssignment_3;

public class Student {

    private String firstName;
    private String lastName;
    private int id;

    public Student()
    {

    }

    public Student(String studentFirst, String studentLast, int studentID)
    {
        this.firstName = studentFirst;
        this.lastName = studentLast;
        this.id = studentID;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public int getId()
    {
        return id;
    }

    public void setFirstName(String studentFirst)
    {
        this.firstName = studentFirst;
    }

    public void setLastName(String studentLast)
    {
        this.lastName = studentLast;
    }

    public void setId(int studentID)
    {
        this.id = studentID;
    }
}
