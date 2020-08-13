package com.pdsrazor.jzoffer;

public class Jz33_UglyNumber extends Solution {
    @Override
    void solute(String[] args) {
        int index = Integer.parseInt(args[0]);
        System.out.println( index + "th ugly number is: " + GetUglyNumber_Solution(index));
    }
    public int GetUglyNumber_Solution(int index) {
        if (index <= 0) return 0;
        int[] nums = new int[index];
        nums[0] = 1;
        int p2, p3, p5;
        p2 = p3 = p5 = 0;
        for (int i = 1; i < index; i++) {
            int min = Math.min(nums[p2]*2, Math.min(nums[p3]*3, nums[p5]*5));
            if (min == nums[p2]*2) p2++;
            if (min == nums[p3]*3) p3++;
            if (min == nums[p5]*5) p5++;
            nums[i] = min;
        }
        return nums[index-1];
    }
}