package com.pdsrazor.jzoffer;

public class Jz50_duplicate extends Solution {
    @Override
    void solute(String[] args) {
        int[] arr = {2,3,1,0,2,5,3};
        int[] dup = new int[1];
        duplicate2(arr, 7, dup);
        System.out.println("dup num: " + dup[0]);
    }

    // O(n)的空间复杂度，还可以先排序，会改变数据，O(nlogn)
    public boolean duplicate(int numbers[],int length,int [] duplication) {
        if (numbers == null) return false;
        int[] cntArr = new int[length];
        for (int i = 0; i < length; i++) {
            cntArr[i] = 0;
        }
        
        for (int i = 0; i < length; i++) {
            cntArr[numbers[i]]++;
            if (cntArr[numbers[i]] > 1) {
                duplication[0] = numbers[i];
                return true;
            }
        }
        
        return false;
    }

    public boolean duplicate2(int numbers[],int length,int [] duplication) {
        if (numbers == null) return false;
        for (int i = 0; i < length; i++) {
            while (numbers[i] != i) {
                if (numbers[i] == numbers[numbers[i]]) {
                    duplication[0] = numbers[i];
                    return true;
                }
                int tmp = numbers[i];
                numbers[i] = numbers[tmp];
                numbers[tmp] = tmp;
            }
        }
        return false;
    }
}