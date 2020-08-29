package com.pdsrazor.jzoffer;

public class Jz8_JumpFloor extends Solution {
    @Override
    void solute(String args[]) {
        int target = Integer.parseInt(args[0]);
        System.out.println("target : " + target + ", ret: " + JumpFloor(target));
    }

    /*
    public int JumpFloor(int target) {
        if (target <= 0) return 0;
        if (target == 1) return 1;
        if (target == 2) return 2;
        int p = 2;
        int pp = 1;
        int ret = 0;
        for (int i = 3; i <= target; i++) {
            ret = p + pp;
            pp = p;
            p = ret;
        }
        return ret;
    } */

    public int JumpFloor(int target) {
        if (target <= 0) return 0;
        int[] record = new int[target+1];
        record[0] = 0;
        //record[1] = 1;
        //record[2] = 2;
        return jump(target, record);
    }
    
    int jump(int target, int[] record) {
        if (target == 1) {
            record[1] = 1;
            return 1;
        }
        if (target == 2) {
            record[2] = 2;
            return 2;
        }
        if (record[target] != 0) {
            return record[target];
        }
        int ret = jump(target-1, record) + jump(target-2, record);
        record[target] = ret;
        return ret;
    }
}