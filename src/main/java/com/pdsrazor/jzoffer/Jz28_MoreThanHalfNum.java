package com.pdsrazor.jzoffer;

import com.pdsrazor.algo.utils.RandomArrayGenerator;

public class Jz28_MoreThanHalfNum extends Solution {
    @Override
    void solute(String args[]) {
        int arr[] = {1,2,3,2,2,2,5,4,2};
        System.out.print("arr: ");
        RandomArrayGenerator.printIntArray(arr);
        //System.out.println("ret: " + MoreThanHalfNum_Solution(arr));
        //System.out.println("ret: " + MoreThanHalfNum_Solution2(arr));
        System.out.println("ret: " + MoreThanHalfNum_Solution3(arr));
    }

    // 基于快排 nlogn
    public int MoreThanHalfNum_Solution(int [] array) {
        if (array.length == 1) return array[0];
        quickSort(array, 0, array.length-1);
        System.out.print("sorted arr: ");
        RandomArrayGenerator.printIntArray(array);
        int maxCnt = 0;
        int cnt = 1;
        int hafv = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] == array[i-1]) {
                System.out.println("arr[" + i + "] = " + array[i]);
                cnt++;
                hafv = array[i];
            } else {
                if (maxCnt < cnt) {
                    maxCnt = cnt;
                    hafv = array[i-1];
                }
                cnt = 1;
            }
        }
        
        if (maxCnt > array.length/2) return hafv;
        else return 0;
    }

    // partion O(n)
    public int MoreThanHalfNum_Solution2(int [] array) {
        int ret = partition(array, 0, array.length-1, array.length/2);
        int cnt = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == array[ret]) {
                cnt++;
            }
        }
        if (cnt > array.length/2) return array[ret];
        else return 0;
    }
    
    public int MoreThanHalfNum_Solution3(int [] array) {
        if (array == null || array.length == 0) return 0;
        if (array.length == 1) return array[0];
        
        int data = array[0];
        int cnt = 1;
        
        for (int i = 1; i < array.length; i++) {
            if (array[i] != data) {
                cnt--;
                if (cnt == 0) {
                    cnt = 1;
                    data = array[i];
                }
            }
        }
        int dataCnt = 0;
        for (int i = 0; i < array.length; i++) {
            if (data == array[i]) dataCnt++;
        }
        if (dataCnt > array.length/2) return data;
        else return 0;
    }

    int partition(int[] arr, int start, int end, int target) {
        if (start < end) {
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
            if (p == target) return arr[p];
            else if (p > target) return partition(arr, start, p-1, target);
            else return partition(arr, p+1, end, target);
        }
        return start;
    }

    void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            int pidx = start + (end-start)/2;
            int pivot = arr[pidx];
            int p = start;
            for (int i = start; i <= end; i++) {
                if (arr[i] < pivot) {
                    //if (i != p) {
                        int tmp = arr[i];
                        arr[i] = arr[p];
                        arr[p] = tmp;
                        if (p == pidx) pidx = i;
                   // }
                    p++;
                }
            }
            arr[pidx] = arr[p];
            arr[p] = pivot;
            quickSort(arr, start, p-1);
            quickSort(arr, p+1, end);
        }
    }

}