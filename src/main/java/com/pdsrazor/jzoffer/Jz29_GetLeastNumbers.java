package com.pdsrazor.jzoffer;

import java.util.ArrayList;

import com.pdsrazor.algo.utils.RandomArrayGenerator;

public class Jz29_GetLeastNumbers extends Solution {

    @Override
    void solute(String[] args) {
        // int[] arr = {4,5,1,6,2,7,3,8};
        int[] arr = {4,5,1,6,2,7,3,8};
        int k = Integer.parseInt(args[0]);
        System.out.println("arr： ");
        RandomArrayGenerator.printIntArray(arr);
        //System.out.println("result: " + GetLeastNumbers_Solution(arr, k));
        System.out.println("result: " + GetLeastNumbers_Solution2(arr, k));
    }
    
    // 维护大小为k的大顶堆
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if (k > input.length || k <= 0) {
            return list;
        }
         
        int[] bigHeap = new int[k+1];
        for (int i = 0; i < k; i++) {
            bigHeap[i+1] = input[i];
            heapifyB2T(bigHeap, i+1);
        }
        for (int i = k; i < input.length; i++) {
            if (input[i] < bigHeap[1]) {
                bigHeap[1] = input[i];
                heapifyT2B(bigHeap, k);
            }
        }
        for (int i = 0; i < k; i++) {
            list.add(bigHeap[i+1]);
        }
        return list;
    }

    // 快派partition思想 缺点是会改变输入数据
    public ArrayList<Integer> GetLeastNumbers_Solution2(int [] input, int k) {
        if (k > input.length || k <= 0) return new ArrayList<>();
        partitionK(input, 0, input.length-1, k);
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            list.add(input[i]);
        }
        return list;
    }
    
    void partitionK(int[] arr, int start, int end, int k) {
        if (start >= end) return;
        int pivotIdx = start + (end-start)/2;
        int pivot = arr[pivotIdx];
        int p = start;
        for (int i = start; i <= end; i++) {
            if (arr[i] < pivot) {
                if (i != p) {
                    int tmp = arr[p];
                    arr[p] = arr[i];
                    arr[i] = tmp;
                    if (p == pivotIdx) {
                        pivotIdx = i;
                    }
                }
                p++;
            }
        }
        arr[pivotIdx] = arr[p];
        arr[p] = pivot;
        if (p == k) return;
        else if (p > k) partitionK(arr, start, p-1, k);
        else partitionK(arr, p+1, end, k);
    }
     
    void heapifyB2T(int[] bigHeap, int end) {
        while (end/2 > 0 && bigHeap[end] > bigHeap[end/2]) {
                int tmp = bigHeap[end];
                bigHeap[end] = bigHeap[end/2];
                bigHeap[end/2] = tmp;
                end = end/2;
        }
    }
     
    void heapifyT2B(int[] bigHeap, int end) {
        int p = 1;
        int maxPos;
        while (true) {
            maxPos = p;
            if (p*2 <= end && bigHeap[p] < bigHeap[p*2]) maxPos = p*2;
            if (p*2+1 <= end && bigHeap[maxPos] < bigHeap[p*2+1]) maxPos = p*2+1;
            if (p == maxPos) break;
            int tmp = bigHeap[p];
            bigHeap[p] = bigHeap[maxPos];
            bigHeap[maxPos] = tmp;
            p = maxPos;
        }
    }
}