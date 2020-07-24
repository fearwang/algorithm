package com.pdsrazor.algo.search;

import com.pdsrazor.algo.utils.*;
import com.pdsrazor.algo.MyDemo;
import com.pdsrazor.algo.sort.*;
import java.util.Random;

public class SearchDemo extends MyDemo {
    @Override
    public int doCmd(String args[]) {
        if (args[0].equals("binarySearch")) {
            int len = Integer.parseInt(args[1]);
            int[] arr = RandomArrayGenerator.genIntArray(len, len);
            System.out.println("before sort: ");
            RandomArrayGenerator.printIntArray(arr);
            SortDemo.quickSort(arr);
            System.out.println("after sort: ");
            RandomArrayGenerator.printIntArray(arr);
            int target = new Random().nextInt(len);
            int index = binarySearch(arr, target);
            System.out.println("target: " + target + " ,search result: " + index);
            return 1;
        }
        if (args[0].equals("squareRoot")) {
            double square = Double.parseDouble(args[1]);
            System.out.println("square: " + square + ", root: " + getSquareRoot(square));
            return 1;
        }
        if (args[0].equals("binarySearchFirst") || args[0].equals("binarySearchLast")) {
            int len = Integer.parseInt(args[1]);
            int[] arr = RandomArrayGenerator.genIntArray(len, len);
            System.out.println("before sort: ");
            RandomArrayGenerator.printIntArray(arr);
            SortDemo.quickSort(arr);
            System.out.println("after sort: ");
            RandomArrayGenerator.printIntArray(arr);
            int target = new Random().nextInt(len);
            //int[] arr = {1, 3, 4, 5, 6, 8, 8, 8, 11, 18};
            //RandomArrayGenerator.printIntArray(arr);
            // int target = 8;
            System.out.println("target: " + target + ", search first result: " + binarySearchFirst(arr, target)
                 + ", search normal result: " + binarySearch(arr, target)
                 + ", search last result: " + binarySearchLast(arr, target));
            return 1;
        }
        if (args[0].equals("binarySearchFirstGE")) {
            int len = Integer.parseInt(args[1]);
            int[] arr = RandomArrayGenerator.genIntArray(len, len);
            System.out.println("before sort: ");
            RandomArrayGenerator.printIntArray(arr);
            SortDemo.quickSort(arr);
            System.out.println("after sort: ");
            RandomArrayGenerator.printIntArray(arr);
            int target = new Random().nextInt(len);
            //int[] arr = {1, 3, 4, 5, 6, 8, 8, 8, 11, 18};
            //RandomArrayGenerator.printIntArray(arr);
            // int target = 8;
            int index = binarySearchFirstGE(arr, target);
            System.out.println("target: " + target + ", search first ge result: index = "
                 + index + ", value = " + (index >= 0 ? arr[index] : "not found"));
            return 1;
        }
        if (args[0].equals("binarySearchLastLE")) {
            int len = Integer.parseInt(args[1]);
            int[] arr = RandomArrayGenerator.genIntArray(len, len);
            System.out.println("before sort: ");
            RandomArrayGenerator.printIntArray(arr);
            SortDemo.quickSort(arr);
            System.out.println("after sort: ");
            RandomArrayGenerator.printIntArray(arr);
            int target = new Random().nextInt(len);
            //int[] arr = {1, 3, 4, 5, 6, 8, 8, 8, 11, 18};
            //RandomArrayGenerator.printIntArray(arr);
            // int target = 8;
            int index = binarySearchLastLE(arr, target);
            System.out.println("target: " + target + ", search last le result: index = "
                 + index + ", value = " + (index >= 0 ? arr[index] : "not found"));
            return 1;
        }
        if (args[0].equals("binarySearchCircle")) {
            int len = Integer.parseInt(args[1]);
            int[] arr = RandomArrayGenerator.genIntArray(len, len);
            System.out.println("before sort: ");
            RandomArrayGenerator.printIntArray(arr);
            SortDemo.quickSort(arr);
            System.out.println("after sort: ");
            RandomArrayGenerator.printIntArray(arr);
            int[] rA = circlizeSortedArray(arr);
            // int[] rA = {4, 5, 6, 1, 2, 3, 7, 8, 9, 1, 2, 3};
            System.out.println("after circlize: ");
            RandomArrayGenerator.printIntArray(rA);
            int target = new Random().nextInt(len);
            int index = binarySearchCircle2(rA, 0, rA.length-1, target);
            System.out.println("target: " + target + ",  result: index = "
                 + index + ", value = " + (index >= 0 ? rA[index] : "not found"));
            return 1;
        }
        return 0;
    }

    // 排序好的数组， 改成循环数组，即起始地址不为0
    public static int[] circlizeSortedArray(int[] arr) {
        int[] ret = new int[arr.length];
        int start = new Random().nextInt(arr.length);
        for (int i = 0; i < arr.length; i++) {
            ret[(start + i) % arr.length] = arr[i];
        }
        return ret;
    }

    @Override
    public void usage() {
        System.out.println("SearchDemo:");
        System.out.println("  binarySearch <length>");
        System.out.println("  binarySearchFirst <length>");
        System.out.println("  binarySearchLast <length>");
        System.out.println("  binarySearchFirstGE <length>");
        System.out.println("  binarySearchLastLE <length>");
        System.out.println("  binarySearchCircle <length>");
        System.out.println("  squareRoot <square>");
    }

    public static int binarySearch(int[] arr, int target) {
        int start = 0;
        int end = arr.length-1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target) return mid;
            if (arr[mid] > target) end = mid - 1;
            if (arr[mid] < target) start = mid + 1;
        }
        return -1;
    }

    // 有序数组，二分查找
    public static int binarySearch2(int arr[], int target) {
        if (arr == null || arr.length <= 0) {
            return -1;
        }
        int start = 0;
        int end = arr.length-1;
        int mid = (start + end) / 2;

        while (start < end) {
            if (arr[mid] < target) {
                start = mid+1;
                mid = (start + end) /2;
                continue;
            }

            if (arr[mid] > target) {
                end = mid-1;
                mid = (start+end)/2;
                continue;
            }

            return mid;
        }

        if (arr[mid] == target) {
            return mid;
        } else {
            return -1;
        }
    }

    public int accept(double val, double target) {
        if (val * val > target) {
            if (val * val - target < 0.000001) return 0;
            else return 1;
        } else if (val * val < target) {
            if (target - val * val < 0.000001) return 0;
            else return -1;
        } else {
            return 0;
        }
    }

    // 举例： val = 2, accuracy = 2, 返回0.02
    public double getPoint(double val, int accuracy) {
        while(accuracy-- > 0) {
            val = val / 10;
        }
        return val;
    }

    // 求一个数的平方根，精确到小数点后6位, 先仅仅考虑target 大于 0
    public double getSquareRoot(double target) {
        long start = 0;
        long end = (long)target;
        // 先找到整数部分， 小数部分每位范围0-9固定，单独处理
        long v = 0;
        if ((long)target != 0) {
            while (start <= end) {
                v = start + (end - start) / 2;
                if (v * v == (long)target) break;
                if (v * v > (long)target) end = v - 1;
                if (v * v < (long)target) start = v + 1;
            }
        }
        // 最后因start > end退出循环之前， v = start + (start - end) / 2 = start + 0 = start
        double dv = v;
        // 2 * 2 = 4 这种情况
        if (accept(dv, target) == 0) return dv;
        else if (accept(dv, target) > 0) dv -= 1; // 选择从小往大找
        int accuracy = 1;
        while (accuracy <= 6) {
            int s = 0;
            int e = 9;
            int m = 0;
            double tail = 0;
            while (s <= e) {
                m = s + (e - s) / 2;
                tail = getPoint(m, accuracy);
                if (accept(dv + tail, target) == 0) {
                    System.out.println("break: " + tail);
                    break;
                }
                if (accept(dv + tail, target) > 0) {
                    System.out.println(" > tail " + tail + " dv " + dv + " m " + m + " accuracy " + accuracy);
                    e = m - 1;
                }
                if (accept(dv + tail, target) < 0) {
                    System.out.println(" < tail " + tail + " dv " + dv + " m " + m + " accuracy " + accuracy);
                    s = m + 1;
                }
            }
            tail = getPoint(m, accuracy);
            System.out.println("this final tail " + tail + " accuracy " + accuracy);
            if (accept(dv + tail, target) == 0) {
                System.out.println("break: " + tail);
                break;
            }
            if (accept(dv + tail, target) > 0) {
                tail = getPoint(m-1, accuracy);
                System.out.println("new dv " + (dv + tail));
            }
            dv += tail;
            accuracy++;
        }
        System.out.println("寻找小数位 ： " + accuracy + " " + getPoint(7, 3));
        return dv;
    }

    // 寻找一个等于目标值的元素(存在多个元素等于目标值的情况下)
    // 假设数据从小到大排序
    // 返回下标
    public int binarySearchFirst(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] > target) end = mid - 1;
            if (arr[mid] < target) start = mid + 1;
            if (arr[mid] == target) {
                if (mid == 0) return mid;
                if (arr[mid - 1] < target) return mid;
                else end = mid - 1;
            }  
        }
        return -1;
    }

    // 查找最后一个等于给定值的元素
    public int binarySearchLast(int arr[], int target) {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] > target) end = mid - 1;
            else if (arr[mid] < target) start = mid + 1;
            else {
                if (mid == arr.length - 1 || arr[mid + 1] != target) return mid;
                else start = mid + 1;
            }
        }
        return -1;
    }

    // 查找第一个大于等于指定值的元素
    public int binarySearchFirstGE(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = start + ((end-start) >> 1);
            if (arr[mid] >= target) {
                if (mid == 0 || arr[mid - 1] < target) return mid;
                else end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    // 查找最后一个小于等于给定值的元素
    public int binarySearchLastLE(int[] arr, int target) {
        int start = 0;
        int end = arr.length;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] <= target) {
                if (mid == arr.length-1 || arr[mid+1] > target) return mid;
                else start = mid + 1;
            } else end = mid - 1;
        }
        return -1;
    }

    // 针对循环有序数组的二分查找算法, 假设无重复数据
    // 4，5，6，1，2，3
    public int binarySearchCircle(int arr[], int start, int end, int target) {
        if (start > end) return -1;
        int mid = start + (end - start) / 2;
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] > arr[start]) { // 前半部分不再是循环数组了，而是普通数组
            if (target >= arr[start] && target <= arr[mid-1])
                return binarySearchImpl(arr, start, mid-1, target);
            else
                return binarySearchCircle(arr, mid+1, end, target);
        } else { // 后半部分不再是循环数组
            if (target >= arr[mid+1] && target <= arr[end])
                return binarySearchImpl(arr, mid+1, end, target);
            else
                return binarySearchCircle(arr, start, mid-1, target);
        }
    }

    public int binarySearchCircle2(int arr[], int start, int end, int target) {
        if (start > end) return -1;
        int mid = start + (end - start) / 2;
        int sortedStart, sortedEnd, unsortedStart, unsortedEnd;
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] > arr[start]) {
            sortedStart = start;
            sortedEnd = mid - 1;
            unsortedStart = mid + 1;
            unsortedEnd = end;
        } else {
            sortedStart = mid + 1;
            sortedEnd = end;
            unsortedStart = start;
            unsortedEnd = mid - 1;
        }
        if (target >= arr[sortedStart] && target <= arr[sortedEnd]) {
            return binarySearchImpl(arr, sortedStart, sortedEnd, target);
        } else {
            return binarySearchCircle2(arr, unsortedStart, unsortedEnd, target);
        }
    }

    public int binarySearchImpl(int arr[], int start, int end, int target) {
        while (start <= end) {
            int mid = start + (end-start)/2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] > target) end = mid -1;
            else start = mid + 1;
        }
        return -1; // 代表没找到
    }
}