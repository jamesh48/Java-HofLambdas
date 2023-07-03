package com.hoflambdas;

public class Cat implements Printable {

    public String name;
    public int age;

    public Cat() {
        name = "James";
        age = 32;
    }

    public String print(String p, String s) {
        return "Meow";
    }
}
