package com.pdsrazor.jzoffer;

public class Jz31_NumberOf1 extends Solution {
    @Override
    void solute(String[] args) {
        int n = Integer.parseInt(args[0]);
        System.out.println("num of 1 from 1 to " + n + ": " + NumberOf1Between1AndN_Solution(n));
    }

    public int NumberOf1Between1AndN_Solution(int n) {
        int numOf1 = 0;
        for (int i = 1; i <= n; i++) {
            int val = i;
            while (val > 0) {
                int yu = val % 10;
                if (yu == 1) numOf1++;
                val /= 10;
            }
        }
        return numOf1;
    }
}