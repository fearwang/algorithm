package com.pdsrazor.algo.sort;

import com.pdsrazor.algo.MyDemo;
import com.pdsrazor.algo.utils.*;

// int[] 类型数组排序
public class SortDemo extends MyDemo {
    @Override
    public int doCmd(String args[]) {
        if (args[0].equals("bubbleSort")) {
            int len = Integer.parseInt(args[1]);
            int[] arr = RandomArrayGenerator.genIntArray(len, len);
            System.out.println("before sort: ");
            RandomArrayGenerator.printIntArray(arr);
            bubbleSort(arr);
            System.out.println("after sort: ");
            RandomArrayGenerator.printIntArray(arr);
            return 1;
        }

        if (args[0].equals("insertSort")) {
            int len = Integer.parseInt(args[1]);
            int[] arr = RandomArrayGenerator.genIntArray(len, len);
            System.out.println("before sort: ");
            RandomArrayGenerator.printIntArray(arr);
            insertSort(arr);
            System.out.println("after sort: ");
            RandomArrayGenerator.printIntArray(arr);
            return 1;
        }

        if (args[0].equals("selectSort")) {
            int len = Integer.parseInt(args[1]);
            int[] arr = RandomArrayGenerator.genIntArray(len, len);
            System.out.println("before sort: ");
            RandomArrayGenerator.printIntArray(arr);
            selectSort(arr);
            System.out.println("after sort: ");
            RandomArrayGenerator.printIntArray(arr);
            return 1;
        }

        if (args[0].equals("mergeSort")) {
            int len = Integer.parseInt(args[1]);
            int[] arr = RandomArrayGenerator.genIntArray(len, len);
            System.out.println("before sort: ");
            RandomArrayGenerator.printIntArray(arr);
            mergeSort(arr);
            System.out.println("after sort: ");
            RandomArrayGenerator.printIntArray(arr);
            return 1;
        }

        if (args[0].equals("quickSort")) {
            int len = Integer.parseInt(args[1]);
            int[] arr = RandomArrayGenerator.genIntArray(len, len);
            System.out.println("before sort: ");
            RandomArrayGenerator.printIntArray(arr);
            quickSort(arr);
            System.out.println("after sort: ");
            RandomArrayGenerator.printIntArray(arr);
            return 1;
        }

        if (args[0].equals("findKthMax")) {
            int len = Integer.parseInt(args[1]);
            int k = Integer.parseInt(args[2]);
            int[] arr = RandomArrayGenerator.genIntArray(len, len);
            System.out.println("before find: ");
            RandomArrayGenerator.printIntArray(arr);
            System.out.println(k + "th element: " + findKthMax(arr, k));
            System.out.println("after find: ");
            RandomArrayGenerator.printIntArray(arr);         
            quickSort(arr);
            System.out.println("after sort: ");
            RandomArrayGenerator.printIntArray(arr);
            return 1;
        }

        if (args[0].equals("countingSort")) {
            int len = Integer.parseInt(args[1]);
            int[] arr = RandomArrayGenerator.genIntArray(len, len);
            System.out.println("before sort: ");
            RandomArrayGenerator.printIntArray(arr);
            countingSort(arr);
            System.out.println("after sort: ");
            RandomArrayGenerator.printIntArray(arr);
            return 1;
        }

        if (args[0].equals("radixSort")) {
            int len = Integer.parseInt(args[1]);
            int[] arr = RandomArrayGenerator.genIntArray(len, 999999);
            System.out.println("before sort: ");
            RandomArrayGenerator.printIntArray(arr);
            radixSort(arr);
            System.out.println("after sort: ");
            RandomArrayGenerator.printIntArray(arr);
            return 1;
        }

        if (args[0].equals("bucketSort")) {
            int len = Integer.parseInt(args[1]);
            int[] arr = RandomArrayGenerator.genIntArray(len, len);
            System.out.println("before sort: ");
            RandomArrayGenerator.printIntArray(arr);
            bucketSort(arr);
            System.out.println("after sort: ");
            RandomArrayGenerator.printIntArray(arr);
            return 1;
        }
        if (args[0].equals("alphabetNumSort")) {
            int len = Integer.parseInt(args[1]);
            Character[] arr = {'D', 'a', 'F', 'B', 'c', 'A', 'z', '1', 'r', '3', 'U'};
            System.out.println("before sort: ");
            RandomArrayGenerator.printArray(arr);
            alphabetNumSort(arr);
            System.out.println("after sort: ");
            RandomArrayGenerator.printArray(arr);
            return 1;
        }
        return 0;
    }

    @Override
    public void usage() {
        System.out.println("SortDemo:");
        System.out.println("  bubbleSort <length>");
        System.out.println("  insertSort <length>");
        System.out.println("  selectSort <length>");
        System.out.println("  mergeSort <length>");
        System.out.println("  quickSort <length>");
        System.out.println("  findKthMax <length> <k>");
        System.out.println("  countingSort <length>");
        System.out.println("  radixSort <length>");
        System.out.println("  alphabetNumSort <length>");
        System.out.println("  all <length>");
    }

    // 原地排序，输入参数也是输出结果 
    public static void bubbleSort(int[] arr) {
        int len = arr.length;
        if (len <= 1) return; // 特殊情况直接返回

        int i, j;
        for (i = 0; i < len; i++) {
            boolean swap = false;
            for (j = 0; j < len-i-1; j++) {
                if (arr[j] > arr[j+1]) { // 保持稳定性
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                    swap = true;
                }
            }
            if (swap == false) {
                break;  // 提前退出循环
            }
        }
    }

    public static void bubbleSort2(int[] arr) {
        int len = arr.length;
        boolean swap = false;
        if (len <= 1)
            return;
        for (int i = len-1; i >= 1; i--) {
            swap = false;
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j+1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                    swap = true;
                }
            }
            if (swap == false)
                return;
        }
        return;
    }
    
    public static void insertSort(int[] arr) {
        int len = arr.length;
        if (len <= 1) return;

        int i,j;
        for (i = 1; i < len; i++) {
            int tmp = arr[i];
            for(j = i-1; j >= 0; j--) {
                if (arr[j] > tmp) {
                    arr[j+1] = arr[j];
                } else
                    break;
            }
            arr[j+1] = tmp;
        }
    }

    // 未排序区间的元素 插入到已排序区间的合适位置 类似抓扑克牌
    public static void insertSort2(int[] arr) {
        if (arr.length <= 1) return;
        int i,j,k;
        for(i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            for (j = i-1; j >= 0; j--) {
                if (arr[j] > tmp) {
                    arr[j+1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j+1] = tmp;
        }
        return;
    }

    // 类似冒泡，因为总是要找到未排序区间的最小元素
    public static void selectSort(int[] arr) {
        if (arr.length <= 1) return;
        int i,j;
        int minIdx;
        // 假设已排序区间结尾从-1开始
        for(i = 0; i < arr.length; i++) {
            minIdx = i;
            for (j = i; j < arr.length; j++) {
                if (arr[minIdx] > arr[j]) minIdx = j;
            }
            // System.out.println("minidx " + minIdx + ", min " + arr[minIdx]);
            int tmp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = tmp;
        }
    }

    // 总是从未排序区间找到最小的元素放到已排序区间的最后
    public static void selectSort2(int[] arr) {
        int minIdx = -1;
        for(int i = 0; i < arr.length; i++) {
            minIdx = i;
            for(int j = i; j < arr.length; j++) {
                if (arr[j] < arr[minIdx])
                    minIdx = j;
            }
            if (minIdx != i) {
                int tmp = arr[minIdx];
                arr[minIdx] = arr[i];
                arr[i] = tmp;
            }
        }
        return;
    }

    public static void hillSort(int[] arr) {
        return;
    }

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int start = 0;
        int end = arr.length-1;
        if (start < end) {
            quickSortImpl2(arr, start, end);
        }
    }

    // 2020 07 05
    // 对于任意选择的pivot能否适用？
    public static void quickSortImpl(int arr[], int start, int end) {
        if (start >= end) return;

        int pivot = arr[end];
        int i = start;
        int j = end;
        while (i < j) {
            while(i < j) {
                if (arr[i] > pivot) {
                    arr[j] = arr[i];
                    j--;
                    break;
                }
                i++;
            }
            while (i < j) {
                if (arr[j] < pivot) {
                    arr[i] = arr[j];
                    i++;
                    break;
                }
                j--;
            }
        }
        arr[i] = pivot;
        quickSortImpl(arr, start, i-1);
        quickSortImpl(arr, i+1, end);
    }

    // 2020 07 05
    // 改造后能支持任意pivot选取方法，而不仅仅是end作为pivot
    public static void quickSortImpl2(int arr[], int start, int end) {
        if (start >= end) return;

        //int pivot = arr[end];
        int pIdx = start + (end-start)/2;
        int pivot = arr[pIdx];
        int i = start; // 未处理区间的起点
        // for (int j = start; j <= end-1; j++) {
        for (int j = start; j <= end; j++) {
            if (arr[j] < pivot) {
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                if (i == pIdx) { // pivot的slot发生交换，记录新的
                    pIdx = j;
                }
                i++;
            }
        }
        arr[pIdx] = arr[i];
        arr[i] = pivot; // i 为 分界点
        quickSortImpl2(arr, start, i-1);
        quickSortImpl2(arr, i+1, end);
    }

    public static void quickSort3(int[] arr, int start, int end) {
        int j = start;
        int i = start;
        int pIdx = start + (end-start)/2;
        int pivot = arr[pIdx];
        for (i = start; i <= end; i++) {
            if (arr[i] < pivot) {
                if (i != j) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
                if (j == pIdx) {
                    pIdx = i;
                }
                j++;
            }
        }
        arr[pIdx] = arr[i];
        arr[i] = pivot;
        quickSort3(arr, start, i-1);
        quickSort3(arr, i+1, end);
    }

    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length <= 1)
            return;
        quickSortSub2(arr, 0, arr.length);
        return;
    }

    // 寻找无序数组中第k大的元素
    public static int findKthMax(int[] arr, int k) {
        if (arr == null || k > arr.length) return -1;
        return findKthMaxImpl(arr, k, 0, arr.length-1);
    }

    public static int findKthMaxImpl(int[] arr, int k, int start, int end) {
        int pIdx = start + (end-start)/2;
        int pivot = arr[pIdx];
        int i = start; // 未处理区间的起点
        for (int j = i; j <= end; j++) { // 遍历 未处理区间
            if (arr[j] > pivot) {  // 从大到小排序
                int tmp = arr[j];
                arr[j] = arr[i];
                arr[i] = tmp;
                if (i == pIdx) pIdx = j;
                i++;
            }
        }
        int tmp = arr[i];
        arr[i] = arr[pIdx];
        arr[pIdx] = tmp;

        if (i+1 == k) return arr[i];
        else if (i+1 > k) return findKthMaxImpl(arr, k, start, i-1);
        else return findKthMaxImpl(arr, k, i+1, end);
    }

    // [)
    // 不好
    private static void quickSortSub2(int[] arr, int start, int end) {
        if (start >= end-1)
            return;
        int pivot = arr[start];
        int leftIdx = start;
        int rigthIdx = end - 1;
        int emptyIdx = start; // 记录交换后空闲的slot

        // 当左右指针碰见的时候说明遍历结束
        while (rigthIdx > leftIdx) {
            while (arr[rigthIdx] >= pivot && rigthIdx > leftIdx) {
                rigthIdx--;
            }
            if (rigthIdx > leftIdx) {
                arr[leftIdx] = arr[rigthIdx];
                emptyIdx = rigthIdx;
            }

            while (arr[leftIdx] < pivot && rigthIdx > leftIdx) {
                leftIdx++;
            }
            if (rigthIdx > leftIdx) {
                arr[rigthIdx] = arr[leftIdx];
                emptyIdx = leftIdx;
            }
        }
        arr[emptyIdx] = pivot;

        quickSortSub2(arr, start, emptyIdx);
        quickSortSub2(arr, emptyIdx+1, end);
    }
    // 不好
    private static void quickSortSub(int[] arr, int start, int end) {
        if (start >= end-1)
            return;
        int pivot = arr[end-1]; // 总是选择最后一个元素作为pivot
        int mid = start; //  循环结束后mid指向大于pivot的第一个元素
        // 循环过程中 mid指向确定小于pivot的区间的下一个元素
        for (int i = start; i < end-1; i++) {
            if (arr[i] < pivot && i != mid) {
                int tmp = arr[i];
                arr[i] = arr[mid];
                arr[mid] = tmp;
                mid++;
            }
        }
        // pivot放在中间位置
        int tmp = arr[mid];
        arr[mid] = arr[end-1];
        arr[end-1] = tmp;

        quickSortSub(arr, start, mid);
        quickSortSub(arr, mid+1, end);
    }

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int start = 0; 
        int end = arr.length - 1;
        mergeSortImpl(arr, start, end);
    }

    public static void mergeSortImpl(int[] arr, int start, int end) {
        if (start < end) {
            int mid = start + (end-start)/2;
            mergeSortImpl(arr, start, mid);
            mergeSortImpl(arr, mid+1, end);
            mergeImpl(arr, start, mid, end);
        }
    }

    public static void mergeImpl(int arr[], int start, int mid, int end) {
        int[] tmpArr = new int[end-start+1];
        int i = start;
        int j = mid+1;
        int k = 0;
        while (i <= mid && j <= end) {
            if (arr[i] <= arr[j]) {   // 确保稳定性？
                tmpArr[k++] = arr[i++];
            }
            if (arr[i] > arr[j]) {
                tmpArr[k++] = arr[j++];
            }
        }
        while (i <= mid) {
            tmpArr[k++] = arr[i++];
        }
        while (j <= end) {
            tmpArr[k++] = arr[j++];
        }
        // copy back
        for (i = start,j=0; i <= end; i++,j++) {
            arr[i] = tmpArr[j];
        }
    }

    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length <= 1)
            return;
        int len = arr.length;
        int mid = (0+len)/2;
        sortHalf2(arr, 0, mid);
        sortHalf2(arr, mid, len);
        merge(arr, mid, 0, len);
        return;
    }

    private static void sortHalf2(int[] arr, int start, int end) {
        // [)区间 所以是 end-1
        if (start >= end-1)
            return;
        // 这里需要注意求的是中间元素的下标，不能用长度/2
        int mid = (start+end)/2;
        sortHalf2(arr, 0, mid);
        sortHalf2(arr, mid, end);
        merge(arr, mid, start, end);
    }

    private static void merge(int[] arr, int mid, int start, int end) {
        int[] merged = new int[end-start];
        int i, j, k;
        for(i = start, j = mid, k = 0; i < mid && j < end; k++) {
            if (arr[i] < arr[j]) {
                merged[k] = arr[i++];
            } else {
                merged[k] = arr[j++];
            }
        }
        for (; i < mid; i++, k++) {
            merged[k] = arr[i];
        }
        for (; j < end; j++, k++) {
            merged[k] = arr[j];
        }
        // copy back
        for(i = start, j = 0; i < end; i++,j++) {
            arr[i] = merged[j];
        }
    }

    // 只考虑数据范围为正数的情况，其他情况需要作index的mapping
    public static void countingSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        // 计算数据范围， 确定桶的个数
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
        }

        // 分配桶，并计数每个数据的数量到对应桶中。
        int[] c = new int[max+1]; // [0-max]
        for (int i = 0; i < arr.length; i++) {
            c[arr[i]]++;
        }

        // 求和， 每个桶表示的含义改变为 小于等于当前桶对应数据的数量， 如果下表为5， 则表示所有小于等于5的数据的数量
        for (int i = 1; i <= max; i++) {
            c[i] += c[i-1];
        }

        // 临时数组，存放排序后的数据
        int[] s = new int[arr.length];
        // 倒序计数排序，其实正序应该也可以
        for (int i = arr.length-1; i>= 0; i--) {
            int index = c[arr[i]]-1; // index代表当前剩余数据中，小于等于arr[i]的个数
            s[index] = arr[i];
            c[arr[i]]--; // 成功将一个数据放入临时数组的指定位置， 因此小于等于arr[i]的数据的个数剪1
        }

        // 临时数组拷贝回arr
        for (int i = 0; i < arr.length; i++) {
            arr[i] = s[i];
        }
    }

    // 电话号码排序的例子
    public static void radixSort(int[] arr) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
        }
        // 计算数据的最大长度
        int maxLen = 0;
        while (max > 0) {
            max = max/10;
            maxLen++;
        }
        // 循环maxLen次，每次按照某一位进行排序，从最底位开始
        for (int i = 0; i < maxLen; i++) {
            radixSortSingle(arr, i);
        }
    }

    // 求整数的某个位的值， 12345， 分别位1，2，3，4，5
    public static int getPosValue(int val, int pos) {
        int v = 1;
        while (pos > 0) {
            v *= 10;
            pos--;
        }
        return (val/v)%10; 
    }

    public static void radixSortSingle(int[] arr, int pos) {
        // 分配桶，并计数每个数据的数量到对应桶中。
        int[] c = new int[10]; // 0-9， 如果是其他case则会不同，比如字母范围等
        for (int i = 0; i < arr.length; i++) {
            c[getPosValue(arr[i], pos)]++;
        }

        // 求和， 每个桶表示的含义改变为 小于等于当前桶对应数据的数量， 如果下表为5， 则表示所有小于等于5的数据的数量
        for (int i = 1; i <= 9; i++) {
            c[i] += c[i-1];
        }

        // 临时数组，存放排序后的数据
        int[] s = new int[arr.length];
        // 倒序计数排序，其实正序应该也可以
        for (int i = arr.length-1; i>= 0; i--) {
            int index = c[getPosValue(arr[i], pos)]-1; // index代表当前剩余数据中，小于等于arr[i]的个数
            s[index] = arr[i];
            c[getPosValue(arr[i], pos)]--; // 成功将一个数据放入临时数组的指定位置， 因此小于等于arr[i]的数据的个数剪1
        }

        // 临时数组拷贝回arr
        for (int i = 0; i < arr.length; i++) {
            arr[i] = s[i];
        }
    }

    // 只考虑简单的情况，数据范围大于等于0
    public static void bucketSort(int[] arr) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
        }
        //
    }

    public static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    public static boolean isUpper(char c) {
        return c >= 'A' && c <= 'Z';
    }

    public static boolean isLower(char c) {
        return c >= 'a' && c <= 'z';
    }

    // 数据中包含数字，大小写字母，要求小写字母在前面，数字在中间，大写字母在最后，内部不做顺序要求
    public static void alphabetNumSort(Character arr[]) {
        // 利用快排的分区思想
        // 找到任意数字或者大写字母作为pivot
        char pivot = (char)-1;
        int pIdx = -1;
        for (int i = 0; i < arr.length; i++) {
            if (isDigit(arr[i]) || isUpper(arr[i])) {
                pivot = arr[i];
                pIdx = i;
                break;
            }
        }

        int unhandleIndexStart = 0; // 未处理区间起始位置
        // 第一次分区， 前面位小写字母，后面位数字和大写字母
        if (pIdx != -1) {
            for (int j = unhandleIndexStart; j < arr.length; j++) { // 依次处理未处理区间的数据
                if (isLower(arr[j])) {
                    char tmp = arr[j]; // 交换，将小写字母放到未处理区间的起始位置，并更新未处理区间起始位置++
                    arr[j] = arr[unhandleIndexStart];
                    arr[unhandleIndexStart] = tmp;
                    if (unhandleIndexStart == pIdx) pIdx = j; // 更新pIdx,用于最后的处理
                    unhandleIndexStart++;
                }
            }
            arr[pIdx] = arr[unhandleIndexStart]; // 循环的最后，确保pivot位于未处理区间第一个位置，将数据分为两部分
            arr[unhandleIndexStart] = pivot;
        }
        
        // 从后半部分，继续分区，分为数字和大写字母
        pIdx = -1; // 便于判断是否需要分区
        for (int i = unhandleIndexStart; i < arr.length; i++) {
            if (isUpper(arr[i])) {
                pIdx = i;
                pivot = arr[i];
                break;
            }
        }

        // 对后半部分分区，分为数字和大写字母
        if (pIdx != -1) {
            for (int j = unhandleIndexStart; j< arr.length; j++) {
                if (isDigit(arr[j])) {
                    char tmp = arr[j];
                    arr[j] = arr[unhandleIndexStart];
                    arr[unhandleIndexStart] = tmp;
                    if (unhandleIndexStart == pIdx) pIdx = j;
                    unhandleIndexStart++;
                }
            }
            arr[pIdx] = arr[unhandleIndexStart];
            arr[unhandleIndexStart] = pivot;
        }
    }
}