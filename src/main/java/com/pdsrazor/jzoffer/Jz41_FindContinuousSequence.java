package com.pdsrazor.jzoffer;

import java.util.ArrayList;

public class Jz41_FindContinuousSequence extends Solution {
    @Override
    void solute(String[] args) {
        int sum = Integer.parseInt(args[0]);
        System.out.println(FindContinuousSequence(sum));
    }

    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> rets = new ArrayList<>();
        for (int i = 1; i < sum; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            int s = 0;
            if (i+i+1 > sum) break;
            for (int j = i; j < sum; j++) {
                s += j;
                list.add(j);
                if (s == sum) {
                    rets.add(list);
                    break;
                }
                if (s > sum) {
                    break;
                }
            }
        }
         return rets;
     }
}