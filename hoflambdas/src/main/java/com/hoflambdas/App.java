package com.hoflambdas;

import java.util.Arrays;

public final class App {

    public static void main(String[] args) {
        Printable lambdaPrintable = (p, s) -> {
            String meowMsg = p + "Meow" + s;
            System.out.println(meowMsg);
        };
        printThing(lambdaPrintable);

        Mapable lambdaMap = (inputArr, callback) -> {
            for (int i = 0; i < inputArr.length; i++) {
                inputArr[i] = callback.intable(inputArr[i]);
            }
            return inputArr;
        };

        runMap(lambdaMap);

    }

    static void printThing(Printable thing) {
        thing.print("Result: ", " ~");
    }

    static void runMap(Mapable thing) {
        int[] inputArr = new int[] { 1, 2, 3 };
        Intable callback = (int i) -> i * 10;
        int[] outputArr = thing.mapArray(inputArr, callback);
        // Validation
        System.out.println(Arrays.toString(outputArr));
    }
}
