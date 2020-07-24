package com.pdsrazor.algo.heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import com.pdsrazor.algo.MyDemo;
import com.pdsrazor.algo.sort.SortDemo;
import com.pdsrazor.algo.tree.BinaryTreeDemo;
import com.pdsrazor.algo.utils.BinaryTreeNode;
import com.pdsrazor.algo.utils.RandomArrayGenerator;

public class HeapDemo extends MyDemo {

    @Override
    public void usage() {
        System.out.println("HeapDemo:");
        System.out.println("  genRandom <type{0|1}> <size>");
        System.out.println("  insertToHeap <type{0|1}> <size>");
        System.out.println("  deleteTop <type{0|1}> <size>");
        System.out.println("  delete <type{0|1}> <size>");
        System.out.println("  heapSort <size>");
        System.out.println("  priorityQueue <size>");
        System.out.println("  mergeMultiSortedData <size>");
        System.out.println("  topK <size>");
        System.out.println("  dynamicTopK <size>");
        System.out.println("  median <size>");
    }

    @Override
    public int doCmd(String[] args) {
        int type = Integer.parseInt(args[1]);
        int size = Integer.parseInt(args[2]);
        MyHeap h = new MyHeap(size, type > 0 ? MyHeap.SMALL_TOP : MyHeap.LARGE_TOP);
        if (args[0].equals("genRandom")) {
            h.print();
            return 1;
        }
        if (args[0].equals("insertToHeap")) {
            h.print();
            int data = new Random().nextInt(MyHeap.BOUND);
            h.insert(data);
            System.out.println("insert data: " + data);
            h.print();
            return 1;
        }
        if (args[0].equals("deleteTop")) {
            h.print();
            h.deleteTop();
            System.out.println("after delete top:");
            h.print();
            return 1;
        }
        if (args[0].equals("delete")) {
            h.print();
            int ret = 0;
            int data = 0;
            do {
                data = new Random().nextInt(MyHeap.BOUND);
                ret = h.find(data);
            } while (ret <= 0);
            h.delete(data);
            System.out.println("after delete: " + data);
            h.print();
            return 1;
        }
        if (args[0].equals("heapSort")) {
            int[] arr = RandomArrayGenerator.genIntArray(size, MyHeap.BOUND);
            System.out.println("before: ");
            RandomArrayGenerator.printIntArray(arr);
            MyHeap.heapSort(arr);
            System.out.println("after: ");
            RandomArrayGenerator.printIntArray(arr);
        }

        if (args[0].equals("priorityQueue")) {
            int data = -1;
            PriorityQueue q = new PriorityQueue(size);
            q.print();
            System.out.println("dequeue: ");
            while ((data = q.dequeue()) >= 0) {
                System.out.print(data + " ");
            }
            System.out.println();
        }

        if (args[0].equals("mergeMultiSortedData")) {
            int[][] data = new int[size][5];
            for (int i = 0; i < size; i++) {
                data[i] = RandomArrayGenerator.genIntArray(5, MyHeap.BOUND);
                SortDemo.quickSort(data[i]);
                RandomArrayGenerator.printIntArray(data[i]);
            }
            System.out.println("merged: " + mergeMultiSortedData(data));

        }
        if (args[0].equals("topK")) {
            int k = Integer.parseInt(args[3]);
            int data[] = RandomArrayGenerator.genIntArray(size, MyHeap.BOUND);
            RandomArrayGenerator.printIntArray(data);
            int[] top = topK(data, k);
            RandomArrayGenerator.printIntArray(top);
            SortDemo.quickSort(data);
            System.out.print("sorted data: ");
            RandomArrayGenerator.printIntArray(data);
        }
        if (args[0].equals("dynamicTopK")) {
            int k = Integer.parseInt(args[3]);
            int data[] = RandomArrayGenerator.genIntArray(size, MyHeap.BOUND);
            RandomArrayGenerator.printIntArray(data);
            TopkHeap tkp = new TopkHeap(k);
            for (int i = 0; i < data.length; i++) {
                tkp.insert(data[i]);
            }
            int[] top = topK(data, k);
            System.out.println("topk: ");
            RandomArrayGenerator.printIntArray(top);

            SortDemo.quickSort(data);
            System.out.print("sorted data: ");
            RandomArrayGenerator.printIntArray(data);

            int testd[] = RandomArrayGenerator.genIntArray(5, MyHeap.BOUND);
            for (int i = 0; i < testd.length; i++) {
                System.out.println("insert: " + testd[i]);
                tkp.insert(testd[i]);
                int[] td = tkp.getTopk();
                System.out.print("topk: ");
                RandomArrayGenerator.printIntArray(td);
                SortDemo.quickSort(td);
                System.out.print("sorted topk: ");
                RandomArrayGenerator.printIntArray(td);
                int[] allData = tkp.asArray();
                SortDemo.quickSort(allData);
                System.out.print("sorted all data: ");
                boolean pass = true;
                int pi = k-1;
                int si = allData.length-1;
                for (int j = 0; j < k; j++) {
                    if (td[pi--] != allData[si--]) pass = false;
                }
                System.out.println("test pass: " + pass);
                RandomArrayGenerator.printIntArray(data);
            }
            int deld[] = RandomArrayGenerator.genIntArray(5, MyHeap.BOUND);
            for (int i = 0; i < deld.length; i++) {
                System.out.println("del: " + deld[i]);
                tkp.delete(deld[i]);
                int[] td = tkp.getTopk();
                System.out.print("topk: ");
                RandomArrayGenerator.printIntArray(td);
                SortDemo.quickSort(td);
                System.out.print("sorted topk: ");
                RandomArrayGenerator.printIntArray(td);
                int[] allData = tkp.asArray();
                SortDemo.quickSort(allData);
                System.out.print("sorted all data: ");
                boolean pass = true;
                int pi = k-1;
                int si = allData.length-1;
                for (int j = 0; j < k; j++) {
                    if (td[pi--] != allData[si--]) pass = false;
                }
                System.out.println("test pass: " + pass);
                RandomArrayGenerator.printIntArray(data);
            }  
        }
        if (args[0].equals("median")) {
            int k = size/2; // 中位数， 即top 1/2
            int data[] = RandomArrayGenerator.genIntArray(size, MyHeap.BOUND);
            RandomArrayGenerator.printIntArray(data);
            TopkHeap tkp = new TopkHeap(k);
            for (int i = 0; i < data.length; i++) {
                tkp.insert(data[i]);
            }
            SortDemo.quickSort(data);
            System.out.print("sorted data: ");
            RandomArrayGenerator.printIntArray(data);
            int median = -1;
            if (size%2 == 0) median = tkp.mTopk.top();
            else median = tkp.mOther.top();
            System.out.print("median data: " + median);
        }
        return 0;
    }

    // 合并有序小文件
    // 每个数组都是有序的， 合并这些数组成为一个大的有序数组
    public static ArrayList<Integer> mergeMultiSortedData(int[][] data) {
        if (data == null || data.length == 0) return null;
        int arrsLen = data.length;
        int[] curs = new int[arrsLen];
        for (int i = 0; i < curs.length; i++) {
            curs[i] = 0;
        }
        ArrayList<Integer> merged = new ArrayList<>();
        MyHeap heap = MyHeap.newEmptyHeap(arrsLen, MyHeap.SMALL_TOP);

        do {
            // 这里有问题，应该是从堆中删除一个数据后，再从对应文件中挑选一个插入heap
            for (int i = 0; i < arrsLen; i++) {
                if (curs[i] < data[i].length) {
                    heap.insert(data[i][curs[i]]);
                    curs[i]++; // 子数组中未insert的下标
                }
            }
            merged.add(heap.deleteTop());
        } while (!heap.isEmpty());

        return merged;
    }

    // 找出一组数据中的topK
    public static int[] topK(int[] data, int k) {
        if (k >= data.length) {
            return data;
        }
        int[] heap = new int[k+1];
        int idx = 1;
        // 小顶堆
        for (int i = 0; i < k; i++) {
            int hi = idx++;
            heap[hi] = data[i];
            while (hi/2 > 0 && heap[hi] < heap[hi/2]) {
                int tmp = heap[hi];
                heap[hi] = heap[hi/2];
                heap[hi/2] = tmp;
                hi = hi/2;
            }
        }
        //for (int i = 1; i <= k; i++) {
         //   System.out.print(heap[i] + " ");
        //}
        // System.out.println();
        for (int i = k; i < data.length; i++) {
            if (data[i] > heap[1]) {
                // 挤掉heap中最小的
                heap[1] = data[i];
                // 重新堆化
                int p = 1;
                int min = 1;
                while (true) {
                    min = p;
                    if (p*2 <= k && heap[p] > heap[p*2]) {
                        min = p*2;
                    }
                    if (p*2+1 <= k && heap[min] > heap[p*2+1]) {
                        min = p*2 + 1;
                    }
                    if (min == p) break;
                    int tmp = heap[p];
                    heap[p] = heap[min];
                    heap[min] = tmp;
                    p = min;
                }
            }
        }
        return heap;
    }
}

class MyHeap {
    public int[] mData;
    public int mCount;
    public int mMaxCount;
    public int mType;

    public static int LARGE_TOP = 0;
    public static int SMALL_TOP = 1;

    public static int BOUND = 1000;

    public MyHeap() {

    }

    public MyHeap(int size, int type) {
        this.mMaxCount = size;
        this.mCount = 0;
        this.mType = type;
        this.mData = new int[mMaxCount+1];
        for (int i = 0; i <= mMaxCount; i++) {
            mData[i] = -1; // invalid data
        }

        int[] tmp = RandomArrayGenerator.genIntArray(size, BOUND);
        for (int i = 0; i < tmp.length; i++) {
            insert(tmp[i]);
        }
    }

    public static MyHeap newEmptyHeap(int size, int type) {
        MyHeap heap = new MyHeap();
        heap.mMaxCount = size;
        heap.mCount = 0;
        heap.mType = type;
        heap.mData = new int[heap.mMaxCount+1];
        for (int i = 0; i <= heap.mMaxCount; i++) {
            heap.mData[i] = -1; // invalid data
        }
        return heap;
    }

    public boolean isEmpty() {
        return mCount == 0;
    }

    public int top() {
        if (mCount <= 0) return -1;
        return mData[1];
    }

    public int size() {
        return mCount;
    }

    public void print() {
        BinaryTreeDemo.graphPrintBinaryTree(genBTFromHeap());
    }

    public BinaryTreeNode genBTFromHeap() {
        if (mCount == 0) {
            return null;
        }
        BinaryTreeNode[] arr = new BinaryTreeNode[mCount+1];
        for (int i = 1; i<= mCount; i++) {
            arr[i] = new BinaryTreeNode(mData[i]);
        }

        for (int j = 1; j*2 <= mCount; j++) {
            arr[j].left = arr[j*2];
            if ((j*2+1) <= mCount) {
                arr[j].right = arr[j*2+1]; 
            }
        }

        return arr[1];
    }

    public static BinaryTreeNode genBTFromHeapArray(int datas[]) {
        if (datas == null || datas.length == 0) {
            return null;
        }
        BinaryTreeNode[] arr = new BinaryTreeNode[datas.length+1];
        for (int i = 1; i <= datas.length; i++) {
            arr[i] = new BinaryTreeNode(datas[i-1]);
        }

        for (int j = 1; j*2 <= datas.length; j++) {
            arr[j].left = arr[j*2];
            if ((j*2+1) <= datas.length) {
                arr[j].right = arr[j*2+1]; 
            }
        }

        return arr[1];
    }

    public boolean insert(int data) {
        // 必要时扩容， 非并发安全
        if (mCount >= mMaxCount) {
            int[] tmp = new int[2*mMaxCount+1];
            for (int i = 1; i <= 2*mMaxCount; i++) {
                tmp[i] = -1; // invalid data
            }
            for (int i = 1; i <= mMaxCount; i++) {
                tmp[i] = mData[i];
            }
            mData = tmp;
            mMaxCount = 2*mMaxCount;
        }

        // 放到最后，然后从下到上heapify
        mData[++mCount] = data; // 下标从1开始
        heapifyBottom2Top(mCount);
        return true;
    }

    public int deleteTop() {
        if (mCount <= 0) return -1;
        int ret = mData[1];
        mData[1] = mData[mCount];
        mCount--;
        heapifyTop2Bottom(1);
        return ret;
    }

    public boolean delete(int data) {
        if (mCount <= 0) return false;
        int ret = find(data);
        if (ret <= 0) return false;
        mData[ret] = mData[mCount];
        mCount--;
        heapifyTop2Bottom(ret);
        return true;
    }

    public int find(int data) {
        for (int i = 1; i <= mCount; i++) {
            if (mData[i] == data) return i;
        }
        return -1;
    }

    public static void swap(int[] arr, int ai, int bi) {
        int tmp = arr[ai];
        arr[ai] = arr[bi];
        arr[bi] = tmp;
    }

    // 从小到大排序
    public static int[] heapSort(int[] data) {
        // 数组下标从0开始，和heap的处理方式不同
        for (int j = 0; j < data.length; j++) {
            int i = j;
            while ((i-1)/2 >= 0 && data[i] > data[(i-1)/2]) {
                swap(data, i, (i-1)/2);
                i = (i-1)/2;
            }
        }
        // 构建了一个大顶堆
        BinaryTreeDemo.graphPrintBinaryTree(genBTFromHeapArray(data));
        // // 尾部交换，最大的放到最后，然后对 剩下的部分继续大顶堆化
        for (int i = data.length-1; i >= 0; i--) {
            swap(data, i, 0);
            // BinaryTreeDemo.graphPrintBinaryTree(genBTFromHeapArray(data));
            int p = 0;
            int maxPos = 0;
            while (true) {
                maxPos = p;
                if (p*2+1 < i && data[p] < data[p*2+1]) {
                    maxPos = p*2+1;
                }
                if (p*2+2 < i && data[maxPos] < data[p*2+2]) {
                    maxPos = p*2+2;
                }
                if (maxPos == p) break;
                swap(data, p, maxPos);
                p = maxPos;
            }
        }
        return data;
    }

    private boolean match(int parent, int child) {
        if (mType == LARGE_TOP) {
            return parent >= child;
        } else if (mType == SMALL_TOP) {
            return parent <= child;
        }
        return false;
    }

    private void heapifyTop2Bottom(int start) {
        int i = start;
        int maxPos = i;
        while (true) {
            maxPos = i;
            if (i*2 <= mCount && !match(mData[i], mData[i*2])) {
                maxPos = i*2;
            }
            if ((i*2+1) <= mCount && !match(mData[maxPos], mData[i*2+1])) {
                maxPos = i*2 + 1;
            }
            if (maxPos == i) {
                break;
            }
            int tmp = mData[i];
            mData[i] = mData[maxPos];
            mData[maxPos] = tmp;
            i = maxPos;
        }
    }
    
    private void heapifyBottom2Top(int start) {
        int i = start;
        while (i/2 > 0 && !match(mData[i/2], mData[i])) {
            int tmp = mData[i/2];
            mData[i/2] = mData[i];
            mData[i] = tmp;
            i = i/2;
        }
    }
}

// 支持随时O(1)取出topk数据， 支持insert/delete
class TopkHeap {
    int mk = 0;
    // 小顶堆
    MyHeap mTopk = null;
    // 大顶堆
    MyHeap mOther = null;

    public TopkHeap(int k) {
        this.mk = k;
        mTopk = MyHeap.newEmptyHeap(10, MyHeap.SMALL_TOP);
        mOther = MyHeap.newEmptyHeap(10, MyHeap.LARGE_TOP);
    }

    public void insert(int data) {
        if (mTopk.size() < mk) {
            mTopk.insert(data);
        } else {
            if (mTopk.top() < data) {
                int del = mTopk.deleteTop();
                mTopk.insert(data);
                mOther.insert(del);
            } else {
                mOther.insert(data);
            }
        }
    }

    public boolean delete(int data) {
        // 先尝试在非topk中delete，找到了则删除直接返回
        if (mOther.delete(data)) {
            return true;
        }
        // 如果非topk没找到，则在topk中尝试删除
        if (mTopk.delete(data)) {
            // 如果topk删掉了一个，则需要在非topk中找一个最大的补充
            int top = mOther.deleteTop();
            if (top >= 0) {
                mTopk.insert(top);
            }
            return true;
        }
        return false;
    }

    public int[] getTopk() {
        int[] ret = new int[mTopk.mCount];
        for (int i = 0; i < mTopk.mCount; i++) {
            ret[i] = mTopk.mData[i+1];
        }
        return ret;
    }

    public int[] asArray() {
        int[] ret = new int[mTopk.mCount + mOther.mCount];
        int k = 0;
        for (int i = 1; i <= mTopk.mCount; i++) {
            ret[k++] = mTopk.mData[i];
        }
        for (int i = 1; i <= mOther.mCount; i++) {
            ret[k++] = mOther.mData[i];
        }
        return ret;
    }
}

// 优先级最高的先出队
class PriorityQueue {
    public MyHeap heap;

    // 大顶堆， 值越大代表优先级越高
    public PriorityQueue(int size) {
        heap = new MyHeap(size, MyHeap.LARGE_TOP);
    }

    public boolean enqueue(int data) {
        return heap.insert(data);
    }

    public int dequeue() {
        return heap.deleteTop();
    }

    public void print() {
        heap.print();
    }
}
