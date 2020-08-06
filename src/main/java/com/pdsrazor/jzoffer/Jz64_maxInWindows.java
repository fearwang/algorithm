package com.pdsrazor.jzoffer;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Jz64_maxInWindows extends Solution {
    @Override
    void solute(String args[]) {
        int arr[] = {2,3,4,2,6,2,5,1};
        System.out.print("list: " + maxInWindows(arr, 3));
    }
    
    public ArrayList<Integer> maxInWindows(int [] num, int size) {
        ArrayList<Integer> list = new ArrayList<>();
        if (num == null || num.length < size || size <= 0) return list;
        for (int i = 0; i < (num.length - size + 1); i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < (i+size); j++) {
                if (num[j] > max) max = num[j];
            }
            list.add(max);
        }
        return list;
    }

    public ArrayList<Integer> maxInWindows2(int [] num, int size) {
        ArrayList<Integer> list = new ArrayList<>();
        if (num == null || size > num.length || size <= 0) return list;
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        for (int i = 0; i < size; i++) {
            while (ad.size() > 0 && num[i] > num[ad.peekLast()]) {
                ad.pollLast();
            }
            ad.addLast(i);
        }
        list.add(num[ad.peekFirst()]);
        
        for (int i = size; i < num.length; i++) {
            while (ad.size() > 0 && (i - ad.peekFirst() >= size)) {
                ad.pollFirst();
            }
            while (ad.size() > 0 && num[i] > num[ad.peekLast()]) {
                ad.pollLast();
            }
            ad.addLast(i);
            list.add(num[ad.peekFirst()]);
        }
        return list;
    }
}