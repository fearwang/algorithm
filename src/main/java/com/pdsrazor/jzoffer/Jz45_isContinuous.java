package com.pdsrazor.jzoffer;

public class Jz45_isContinuous extends Solution {
    @Override
    void solute(String[] args) {
        int arr[] = {1, 3, 0, 0, 5};
        System.out.println(isContinuous(arr));
    }

    public boolean isContinuous(int [] numbers) {
        if (numbers == null || numbers.length != 5) return false;
        for (int i = 0; i < 5; i++) {
            if (numbers[i] > 13 || numbers[i] < 0) return false;
        }
        int[] arr = new int[14]; // 0-13
        for (int i = 0; i < 5; i++) {
            arr[numbers[i]]++;
            if (numbers[i] != 0 && arr[numbers[i]] > 1) return false;
        }
        int start, end;
        start = end = -1;
        for (int i = 1; i < 14; i++) {
            if (start == -1 && arr[i] != 0) {
                start = i;
            }
            if (i > end && arr[i] != 0) {
                end = i;
            }
        }
        if (end-start+1 > 5) return false;
        return true;
    }
}