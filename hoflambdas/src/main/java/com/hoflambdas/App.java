package com.hoflambdas;

public final class App {

    // private App() {}

    public static void main(String[] args) {
        // Cat myCat = new Cat();
        Printable lambdaPrintable = (p, s) -> p + " Meow " + s;
        printThing(lambdaPrintable);
    }

    static void printThing(Printable thing) {
        thing.print("Cat: ", "!");
    }
}
