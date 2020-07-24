package com.pdsrazor.algo.heap;

// 堆是一种特殊的树：是完全二叉树，
// 堆中每一个节点的值都必须大于等于(或者小于等于)子树每个节点的值(所谓大顶堆，小顶堆)
// 因为是完全二叉树，所以可以使用数组存储，且不会浪费空间。
public class Heap {
    private int[] a;
    private int n;
    private int count;

    public Heap(int capacity) {
        a = new int[capacity + 1]; // 数据下标从1开始，i*2和i*2+1分别是左右子节点。
        n = capacity;
        count = 0;
    }

    // 默认为大顶堆吧
    public void insert(int data) {
        if (count >= n) return;
        ++count;
        a[count] = data;
        int i = count;

        while(i/2 > 0 && a[i] < a[i/2]) {
            int tmp = a[i];
            a[i] = a[i/2];
            a[i/2] = tmp;
            i = i/2;
        }
    }

    // 改变堆顶元素后 需要保证heap的特性
    private static void heapifyTop2Bottom(int a[], int endIndex, int i) {
        int maxPos = i;  
        while(true) {
            if (i*2 <= endIndex && a[i] < a[i*2]) {
                maxPos = i*2;
            }
            if ( (i*2+1) <= endIndex && a[maxPos] < a[i*2+1]) {
                maxPos = i*2+1;
            }
            // 因为改变的是堆顶，因此这里一旦发现左右子树都比自己小，则完成heapify，直接退出
            if (maxPos == i) break;
            
            int tmp = a[i];
            a[i] = a[maxPos];
            a[maxPos] = tmp;
            i = maxPos;
            continue;
        }
    }

    public int deleteMax() {
        if (count == 0) return -1;
        int ret = a[1];
        a[1] = a[count];
        --count;
        heapifyTop2Bottom(a, count, 1);
        return ret;
    }
    
    // 原地建堆:
    // 1、利用上面的插入的思想，将一个存在的数组heapify,从前往后，从下往上heap化
    // 2、数组从后往前处理数据，从上往下heap化
    // 给定一个数组存储的heap，将其heapify
    public static void buildHeap(int a[], int len) {// 1 ~ len
        // 任何一个数组都可以理解是一个完全二叉树，即i*2和i*2+1是左右子节点
        int i = len/2; // 最后一个存在子节点的非叶子节点
        while(i >= 1) {
            heapifyTop2Bottom(a, len, i);
            i--;
        }
    }

    // 第二种思路:从下往上build，类似insert
    public void buildHeap2(int a[], int len) {
        for(int j = 2; j <= len; j++) {
            int i = j;
            while(i/2 > 0 && a[i] < a[i/2]) {
                int tmp = a[i];
                a[i] = a[i/2];
                a[i/2] = tmp;
                i = i/2;
            }
        }
    }

    // 堆排序的思路：经过建堆后，堆顶元素与数组最后的一个元素交换，然后将n-1个元素继续heaopify
    public static void sort(int a[], int len) {
        buildHeap(a, len);
        int i = len;
        while(i > 1) {
            int tmp = a[i];
            a[i] = a[1];
            a[1] = tmp;
            i--;
            heapifyTop2Bottom(a, i, 1);
        }
    }

    // 应用1: 优先级队列: 合并有序小文件，相对数组来说效率提升：n提升为logN
    // 高性能定时器

    // 应用2:利用堆求top K
    // 维护k个元素的小顶堆，遍历数组，如果比堆顶大，则替换堆顶，并heapify

    // 应用3：利用堆求中位数
    // 对于静态数据集合，可以先排序 再找到中位数
    // 对于动态数据几何，中位数不停在变动：维护两个堆，大顶堆存储前半部分数据，小顶堆存放后半部部分数据
}

