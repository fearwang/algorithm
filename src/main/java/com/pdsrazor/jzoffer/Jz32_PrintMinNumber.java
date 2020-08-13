package com.pdsrazor.jzoffer;

public class Jz32_PrintMinNumber extends Solution {
    @Override
    void solute(String args[]) {
        int[] numbers = {5, 4, 3, 2, 1};
        System.out.println("min number: " + PrintMinNumber(numbers));
    }

    public String PrintMinNumber(int [] numbers) {
        String numstr[] = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            numstr[i] = new Integer(numbers[i]).toString();
        }
        qsort(numstr, 0, numbers.length-1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numbers.length; i++) {
            sb.append(numstr[i]);
        }
        return sb.toString();
    }
    
    void qsort(String[] nums, int start, int end) {
        if (start < end) {
            int pIdx = start + (end-start)/2;
            String pivot = nums[pIdx];
            int i = start;
            for (int j = i; j <= end; j++) {
                if (cmp(nums[j], pivot) < 0) {
                    String tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                    if (i == pIdx) pIdx = j;
                    i++;
                }
            }
            nums[pIdx] = nums[i];
            nums[i] = pivot;
            
            qsort(nums, start, i-1);
            qsort(nums, i+1, end);
        }
    }
    
    int cmp(String s1, String s2) {
        String c1 = s1 + s2;
        String c2 = s2 + s1;
        return c1.compareTo(c2);
    }
}