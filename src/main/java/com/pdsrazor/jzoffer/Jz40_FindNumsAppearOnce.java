package com.pdsrazor.jzoffer;

import com.pdsrazor.algo.utils.RandomArrayGenerator;

public class Jz40_FindNumsAppearOnce extends Solution {
    @Override
    void solute(String[] args) {
        int[] arr = {3, 2, 2, 3, 4, 5};
        RandomArrayGenerator.printIntArray(arr);
        int[] num1 = new int[1];
        int[] num2 = new int[1];
        FindNumsAppearOnce(arr, num1, num2);
        System.out.println("appear once: " + num1[0] + ", " + num2[0]);
    }

    public void FindNumsAppearOnce(int [] array, int num1[] , int num2[]) {
        int tmp = 0;
        for (int i = 0; i < array.length; i++) {
            tmp = tmp ^ array[i];
        }
        int firstSet = 0;
        while (firstSet < 8 * Integer.BYTES) {
            if ((tmp & 0x1) != 0) {
                break;
            }
            firstSet++;
            tmp = (tmp >> 1);
        }
        /*
        int mux = (0x1 << firstSet);
        int[] a1 = new int[array.length];
        int[] a2 = new int[array.length];
        int a1L, a2L;
        a1L = a2L = 0;
        for (int i = 0; i < array.length; i++) {
            if ((array[i] & mux) != 0) a1[a1L++] = array[i];
            else a2[a2L++] = array[i];
        }

        num1[0] = num2[0] = 0;
        for (int i = 0; i < a1L; i++) {
            num1[0] = num1[0]^a1[i];
        }
        for (int i = 0; i < a2L; i++) {
            num2[0] = num2[0]^a2[i];
        }*/
        for (int i = 0; i < array.length; i++) {
            if ((array[i] & (0x1 << firstSet)) != 0) {
                num1[0] = num1[0] ^ array[i];
            } else {
                num2[0] = num2[0] ^ array[i];
            }
        }
    }
}