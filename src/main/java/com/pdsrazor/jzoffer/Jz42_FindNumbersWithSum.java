package com.pdsrazor.jzoffer;

import java.util.ArrayList;

public class Jz42_FindNumbersWithSum extends Solution {
    @Override
    void solute(String[] args) {
        int arr[] = {1, 2, 4, 6 ,10};
        int sum = 10;
        System.out.println(FindNumbersWithSum(arr, sum));
    }

    public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        ArrayList<Integer> list = new ArrayList<>();
        if (array == null || array.length < 2) return list;
        int i, j;
        for (i = 0; i < array.length; i++) {
            for (j = i+1; j < array.length; j++) {
                if (array[i]+array[j] == sum) {
                    list.add(array[i]);
                    list.add(array[j]);
                    return list;
                }
            }
        }
        return list;
    }
}