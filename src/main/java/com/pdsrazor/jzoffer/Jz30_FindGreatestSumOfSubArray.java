package com.pdsrazor.jzoffer;

import com.pdsrazor.algo.utils.RandomArrayGenerator;

public class Jz30_FindGreatestSumOfSubArray extends Solution {
    @Override
    void solute(String[] args) {
        int[] arr = {6,-3,-2,7,-15,1,2,2};
        RandomArrayGenerator.printIntArray(arr);
        System.out.println("greatest sum of sub arr: " + FindGreatestSumOfSubArray(arr));
        System.out.println("greatest sum of sub arr: " + FindGreatestSumOfSubArray2(arr));
    }

    public int FindGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length == 0) return -1;
        int max = Integer.MIN_VALUE;
        int i, j;
        int sum = 0;
        for (i = 0; i < array.length; i++) {
            sum = 0;
            for (j = i; j < array.length; j++) {
                sum += array[j];
                if (sum > max) max = sum;
            }
        }
        return max;
    }

    public int FindGreatestSumOfSubArray2(int[] array) {
        if (array == null || array.length == 0) return -1;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            if (sum <= 0) {
                sum = array[i];
            } else {
                sum += array[i];
            }

            if (max < sum) max = sum;
        }
        return max;
    }
}