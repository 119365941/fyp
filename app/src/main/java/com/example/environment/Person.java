package com.example.environment;

//reference: https://www.youtube.com/watch?v=CsoDddllUl0&list=PLYx38U7gxBf3pmsHVTUwRT_lGON6ZIBHi&index=12
/* In the following piece of code, I constructed a class called 'Person' in order to store data
   for each new user on the system. The following piece of code constructs the class Person, initialises a string and integer
   as well as applying getters and setters so as the variables can be used in other classes.*/

public class Person {

    private int id;
    private String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Person() {

    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
