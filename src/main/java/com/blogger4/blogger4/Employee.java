package com.blogger4.blogger4;

public class Employee {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.salary= salary;
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }



}
