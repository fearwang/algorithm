package com.pdsrazor.jzoffer;

import com.pdsrazor.algo.utils.RandomArrayGenerator;

public class Jz37_GetNumberOfK extends Solution {
    @Override
    void solute(String[] args) {
        int[] arr = {1, 2, 2, 3, 4, 5};
        int k = 2;
        RandomArrayGenerator.printIntArray(arr);
        System.out.println("num of " + k + ": " + GetNumberOfK(arr, k));
        System.out.println("num of " + k + ": " + GetNumberOfK2(arr, k));
    }

    public int GetNumberOfK(int [] array , int k) {
        if (array == null || array.length == 0) return 0;
        int cnt = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == k) cnt++;
        }
        return cnt;
    }

    public int GetNumberOfK2(int [] array , int k) {
        if (array == null || array.length == 0) return 0;
        int start = 0;
        int end = array.length-1;
        int si = -1;
        int ei = -1;
        // found si
        while (start <= end) {
            int mid = start + (end-start)/2;
            if (array[mid] > k) {
                end = mid-1;
            } else if (array[mid] < k) {
                start = mid + 1;
            } else {
                if (mid > 0 && array[mid-1] == array[mid]) {
                    end = mid-1;
                } else {
                    si = mid;
                    break;
                }
            }
        }
        if (si >= 0) {
            start = 0;
            end = array.length-1;
            while (start <= end) {
                int mid = start + (end-start)/2;
                if (array[mid] > k) {
                    end = mid-1;
                } else if (array[mid] < k) {
                    start = mid + 1;
                } else {
                    if (mid < (array.length-1) && array[mid+1] == array[mid]) {
                        start = mid+1;
                    } else {
                        ei = mid;
                        break;
                    }
                }
            }
            // System.out.println("si: " + si + ", ei: " + ei);
            return ei-si+1;
        }
        
        return 0;
    }
}