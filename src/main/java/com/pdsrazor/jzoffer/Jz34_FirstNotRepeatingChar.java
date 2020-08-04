package com.pdsrazor.jzoffer;

public class Jz34_FirstNotRepeatingChar extends Solution {
    @Override
    void solute(String[] args) {
        System.out.println("first not repeat char index : " + FirstNotRepeatingChar("google"));
    }

    int[] arr = new int[256];
    public int FirstNotRepeatingChar(String str) {
        for (int i = 0; i < str.length(); i++) {
            arr[str.charAt(i)]++;
        }
        for (int i = 0; i < str.length(); i++) {
            if (arr[str.charAt(i)] == 1) return i;
        }
        return -1;
    }
}