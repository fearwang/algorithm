package com.pdsrazor.jzoffer;

public class Jz67_cutRope extends Solution {
    @Override
    void solute(String[] args) {
        System.out.println("max len of 8: " + cutRope(8));
    }

    public int cutRope(int target) {
        if (target == 2) {
            return 1;
        }
        if (target == 3) {
            return 2;
        }
        int mark[] = new int[target+1];
        for (int i = 0; i <= target; i++) {
            mark[i] = -1;
        }
        mark[1] = 1;
        mark[2] = 2;
        mark[3] = 3;
        mark[4] = 4;
        for (int i = 5; i <= target; i++) {
            int ret = 0;
            for (int j = 1; j < i; j++) {
                ret = Math.max(ret, j*mark[i-j]);
            }
            mark[i] = ret;
        }
        return mark[target];
    }
}