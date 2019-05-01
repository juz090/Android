package com.example.marcus.grocerylist;

public class Item {

    private String name, catagory;
    private int quantity;

    public Item(String name, int quantity, String catagory){

        this.name = name;
        this.quantity = quantity;
        this.catagory = catagory;
    }

    public String getName(){

        return name;
    }

    public int getQuantity(){

        return quantity;
    }

    public String getCatagory(){

        return catagory;
    }

    public void setName(String name){

        this.name = name;
    }

    public void setQuantity(int quantity){

        this.quantity = quantity;
    }

    public void setCatagory(String catagory){

        this.catagory = catagory;
    }

    public String toString(){

        return this.getName();
    }
}

