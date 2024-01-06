package com.blogger4.blogger4.util;

public class Movie {
    private String name;
    private int rating;
    private int year;

    public Movie(String name, int rating, int year) {
        this.name = name;
        this.rating = rating;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public int getYear() {
        return year;
    }


}
