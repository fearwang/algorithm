package com.pdsrazor.algo.utils;

import java.util.Random;

public class RandomArrayGenerator {
    public static int[] genIntArray(int len, int bound) {
        if (len <= 0)
            return null;
        int[] arr = new int[len];
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            arr[i] = random.nextInt(bound);
        }
        return arr;
    }

    public static int[] getCircleIntArray(int len, int bound) {
        if (len <= 0) return null;
        int[] arr = new int[len];
        Random random = new Random();
        int start = random.nextInt(len);
        for (int i = 0; i < len; i++) {
            arr[(start + i) % len] = random.nextInt(bound);
        }
        return arr;
    }

    public static void printIntArray(int[] arr) {
        if (arr != null && arr.length > 0) {
            for(int i = 0; i < arr.length-1; i++) {
                System.out.print(arr[i] + "-->");
            }
            System.out.println(arr[arr.length-1]);
        } else {
            System.out.println("null array");
        }
    }

    public static <T> void printArray(T[] arr) {
        if (arr != null && arr.length > 0) {
            for(int i = 0; i < arr.length-1; i++) {
                System.out.print(arr[i] + "-->");
            }
            System.out.println(arr[arr.length-1]);
        } else {
            System.out.println("null array");
        }
    }
}