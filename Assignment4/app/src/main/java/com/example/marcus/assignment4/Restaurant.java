package com.example.marcus.assignment4;

public class Restaurant {

    private String name, phone, website, catagory;
    private int rating;

    public Restaurant(String name, String phone, String website, int rating, String catagory){

        this.name = name;
        this.phone = phone;
        this.website = website;
        this.rating = rating;
        this.catagory = catagory;
    }

    public String getName(){

        return name;
    }

    public String getPhone(){

        return phone;
    }

    public String getWebsite(){

        return website;
    }

    public int getRating(){

        return rating;
    }

    public String getCatagory(){

        return catagory;
    }

    public void setName(String name){

        this.name = name;
    }

    public void setPhone(String phone){

        this.phone = phone;
    }

    public void setWebsite(String website){

        this.website = website;
    }

    public void setRating(int rating){

        this.rating = rating;
    }

    public void setCatagory(String catagory){

        this.catagory = catagory;
    }

    public String toString(){

        String temp = "";

        if (ListRestaurant.preferences[0]){

            temp += ", " + this.getPhone();
        }

        if (ListRestaurant.preferences[1]){

            temp += ", " + this.getWebsite();
        }

        if (ListRestaurant.preferences[2]){

            temp += ", " + this.getCatagory();
        }


        return this.getName() + " " + temp;
    }
}
