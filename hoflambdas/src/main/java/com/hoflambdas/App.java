package com.hoflambdas;

import java.util.Arrays;

import com.hoflambdas.Interfaces.*;

public final class App {

    public static void main(String[] args) {

        /* Print */
        Printable lambdaPrintable = (p, r, s) -> {
            String resultMsg = p + " " + r + " " + s;
            System.out.println(resultMsg);
        };
        printThing(lambdaPrintable, "Print:", "Test", "~");

        /* Slice */
        Sliceable sliceArray = (inputArr, startIndex, endIndex) -> {
            int[] slicedArray = new int[endIndex - startIndex];
            for (int i = 0; i < slicedArray.length; i++) {
                slicedArray[i] = inputArr[startIndex + i];
            }
            return slicedArray;
        };

        int[] sliceResult = runSlice(sliceArray, new int[]{ 1, 2, 3, 4, 5 }, 1, 3);
        printThing(lambdaPrintable, "Slice:", Arrays.toString(sliceResult), "~");

        /* First */
        Firstable first = (inputArr, n) -> {
            /* undefined n case still needed */
            int[] resultArr = runSlice(sliceArray, inputArr, 0, n);
            return resultArr;
        };

        int[] firstResult = runFirst(first, new int[]{ 1, 2, 3, 4, 5 }, 1);
        printThing(lambdaPrintable, "First:", Arrays.toString(firstResult), "~");

        /* Last */
        Lastable last = (inputArr, n) -> {
            if (n == 0) {
                return new int[]{};
            }
            if (n > inputArr.length) {
                return inputArr;
            }

            // undefined n case still needed...
            int[] resultArr = runSlice(sliceArray, inputArr, n - 1, inputArr.length);
            return resultArr;
        };

        int[] lastResult = runLast(last, new int[]{ 1, 2, 3, 4, 5}, 3);
        printThing(lambdaPrintable, "Last:", Arrays.toString(lastResult), "~");

        /* Map */
        Mapable lambdaMap = (inputArr, callback) -> {
            for (int i = 0; i < inputArr.length; i++) {
                inputArr[i] = callback.intable(inputArr[i]);
            }
            return inputArr;
        };

        int[] mapResult = runMap(lambdaMap,new int[] { 1, 2, 3 }, (i) -> i * 10);
        printThing(lambdaPrintable, "Map:", Arrays.toString(mapResult), "~");


        /* Filter */
        Filterable lambdaFilter = (inputArr, callback) -> {
            int count = 0;
            for (int i = 0; i < inputArr.length; i++) {
                if (callback.boolable(inputArr[i])) {
                    count++;
                }
            }

            int[] outputArr = new int[count];

            for (int i = 0, k = 0; i < inputArr.length; i++) {
                if (callback.boolable(inputArr[i])) {
                    outputArr[k] = inputArr[i];
                    k++;
                }
            };

            return outputArr;
        };

        int[] filterResult = runFilter(lambdaFilter, new int[] { 1, 2, 3, 4, 5 }, (i) -> i == 3);
        printThing(lambdaPrintable, "Filter:", Arrays.toString(filterResult), "~");

        /* Some */
        Someable some = (inputArr, callback) -> {
            for (int i = 0; i < inputArr.length; i++) {
                if (callback.boolable(inputArr[i])) {
                    return true;
                }
            }
            return false;
        };
        boolean someResult = runSome(some, new int[] { 1, 2, 3, 4, 5}, (i) -> i == 6);
        printThing(lambdaPrintable, "Some:", Boolean.toString(someResult), "~");

        Everyable every = (inputArr, callback) -> {
            for (int i = 0; i < inputArr.length; i++) {
                if (!callback.boolable(inputArr[i])) {
                    return false;
                }
            }
            return true;
        };
        boolean everyResult = runEvery(every, new int[]{ 1, 1, 1, 1, 1 }, (x) -> x == 1);
        printThing(lambdaPrintable, "Every:", Boolean.toString(everyResult), "~");

    }


    static void printThing(Printable thing, String prefix, String result, String suffix) {
        thing.print(prefix, result, suffix);
    }

    static int[] runSlice(Sliceable thing, int[] inputArr, int startIndex, int endIndex) {
        int[] resultArr = thing.slice(inputArr, startIndex, endIndex);
        return resultArr;
    }

    static int[] runFirst(Firstable thing, int[] inputArr, int n) {
        int[] resultArr = thing.first(inputArr, n);
        return resultArr;
    }

    static int[] runLast(Lastable thing, int[] inputArr, int n) {
        int[] resultArr = thing.last(inputArr, n);
        return resultArr;
    }

    static int[] runMap(Mapable thing, int[] inputArr, Intable callback) {
        int[] resultArr = thing.mapArray(inputArr, callback);
        return resultArr;
    }

    static int[] runFilter(Filterable thing, int[] inputArr, Boolable callback) {
        int[] resultArr = thing.filterArray(inputArr, callback);
        return resultArr;
    }

    static boolean runSome(Someable thing, int[] inputArr, Boolable callback) {
        boolean result = thing.some(inputArr, callback);
        return result;
    }

    static boolean runEvery(Everyable thing, int[] inputArr, Boolable callback) {
        boolean result = thing.every(inputArr, callback);
        return result;
    }
}

