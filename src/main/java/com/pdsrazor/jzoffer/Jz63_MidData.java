package com.pdsrazor.jzoffer;

import java.util.ArrayList;

import com.pdsrazor.algo.utils.RandomArrayGenerator;

public class Jz63_MidData extends Solution {
    @Override
    void solute(String args[]) {
        int arr[] = {5,2,3,4,1,6,7,0,8};
        System.out.print("arr: ");
        RandomArrayGenerator.printIntArray(arr);
        for (int i = 0; i < arr.length; i++) {
            Insert(arr[i]);
            System.out.println("big: " + bigtop + ", small:" + smalltop + ", ret: " + GetMedian() + " ");
        }
        System.out.println();

        // reset
        bigtop.clear();
        smalltop.clear();
        smalltop.add(0, 0);
        bigtop.add(0, 0);
        cnt = 0;
        for (int i = 0; i < arr.length; i++) {
            Insert2(arr[i]);
            System.out.println("big: " + bigtop + ", small:" + smalltop + ", ret: " + GetMedian() + " ");
        }
        System.out.println();
    }

    public ArrayList<Integer> bigtop = new ArrayList<>();
    public ArrayList<Integer> smalltop = new ArrayList<>();
    public int cnt = 0;
    
    public Jz63_MidData() {
        smalltop.add(0, 0);
        bigtop.add(0, 0);
    }
    
    public void swap(ArrayList<Integer> list, int i, int j) {
        int ti = list.get(i);
        int tj = list.get(j);
        list.set(i, tj);
        list.set(j, ti);
    }

    public int SMALL_TOP = 1;
    public int BIG_TOP = 2;

    boolean match(int parent, int child, int type) {
        if (type == SMALL_TOP) {
            return parent <= child;
        } else if (type == BIG_TOP) {
            return parent >= child;
        }
        return false;
    }

    void heapifyT2B(ArrayList<Integer> heap, int start, int type) {
        int end = heap.size()-1;
        int p = start;
        int pos = p;
        while (true) {
            pos = p;
            if (p*2 <= end && !match(heap.get(p), heap.get(p*2), type)) {
                pos = p*2;
            }
            if (p*2+1 <= end && !match(heap.get(pos), heap.get(p*2+1), type)) {
                pos = p*2+1;
            }
            if (pos == p) break;
            swap(heap, p, pos);
            p = pos;
        }
    }

    void heapifyB2T(ArrayList<Integer> heap, int start, int type) {
        while (start/2 > 0) {
            if (!match(heap.get(start/2), heap.get(start), type)) {
                swap(heap, start, start/2);
                start = start/2;
                continue;
            }
            break;
        }
    }
    
    public void Insert(Integer num) {
        cnt++;
        int expectedBigTopSize = ((cnt/2) + (cnt%2));
        int bigTopSize = bigtop.size()-1;
        if (bigTopSize < expectedBigTopSize) {
            if (smalltop.size() > 1 && num > smalltop.get(1)) {
                // 后半部分数据中存在比num小的，交换之
                bigtop.add(smalltop.get(1));
                heapifyB2T(bigtop, bigtop.size()-1, BIG_TOP);
                smalltop.set(1, num);
                heapifyT2B(smalltop, 1, SMALL_TOP);
            } else {
                // 后半部分数据为空或者全部比num大，num直接插入前半部分数据中
                bigtop.add(num);
                // bigtop heapify bottom to top
                heapifyB2T(bigtop, bigtop.size()-1, BIG_TOP);
            }

        } else if (num < bigtop.get(1)){
            int tmp = bigtop.get(1);
            bigtop.set(1, num);
            // bigtop heapify top to bottom
            heapifyT2B(bigtop, 1, BIG_TOP);
            
            smalltop.add(tmp);
            // smalltop heapify bottom to top
            heapifyB2T(smalltop, smalltop.size()-1, SMALL_TOP);
        } else {
            smalltop.add(num);
            // small top heapify bottom to top
            heapifyB2T(smalltop, smalltop.size()-1, SMALL_TOP);
        }
    }

    public void Insert2(Integer num) {
        cnt++;
        int expectedBigTopSize = ((cnt/2) + (cnt%2));
        int bigTopSize = bigtop.size()-1;
        if (bigTopSize < expectedBigTopSize) {
            if (smalltop.size() > 1 && num > smalltop.get(1)) {
                // 后半部分数据中存在比num小的，交换之
                bigtop.add(smalltop.get(1));
                int end = bigtop.size()-1;
                while (end/2 > 0) {
                    if (bigtop.get(end) > bigtop.get(end/2)) {
                        swap(bigtop, end, end/2);
                        end = end/2;
                        continue;
                    }
                    break;
                }
                smalltop.set(1, num);
                end = smalltop.size()-1;
                int p = 1;
                int minPos = 1;
                while (true) {
                    minPos = p;
                    if (p*2 <= end && smalltop.get(p) > smalltop.get(p*2)) {
                        minPos = p*2;
                    }
                    if (p*2+1 <= end && smalltop.get(minPos) > smalltop.get(p*2+1)) {
                        minPos = p*2+1;
                    }
                    if (minPos == p) break;
                    swap(smalltop, p, minPos);
                    p = minPos;
                }
            } else {
                // 后半部分数据为空或者全部比num大，num直接插入前半部分数据中
                bigtop.add(num);
                // bigtop heapify bottom to top
                int end = bigtop.size()-1;
                while (end/2 > 0) {
                    if (bigtop.get(end) > bigtop.get(end/2)) {
                        swap(bigtop, end, end/2);
                        end = end/2;
                        continue;
                    }
                    break;
                }
            }

        } else if (num < bigtop.get(1)){
            int tmp = bigtop.get(1);
            bigtop.set(1, num);
            // bigtop heapify top to bottom
            int end = bigtop.size()-1;
            int p = 1;
            int maxPos = 1;
            while (true) {
                maxPos = p;
                if (p*2 <= end && bigtop.get(p) < bigtop.get(p*2)) {
                    maxPos = p*2;
                }
                if (p*2+1 <= end && bigtop.get(maxPos) < bigtop.get(p*2+1)) {
                    maxPos = p*2+1;
                }
                if (maxPos == p) break;
                swap(bigtop, p, maxPos);
                p = maxPos;
            }
            
            smalltop.add(tmp);
            // smalltop heapify bottom to top
            end = smalltop.size()-1;
            while (end/2 > 0) {
                if (smalltop.get(end) < smalltop.get(end/2)) {
                    swap(smalltop, end, end/2);
                    end = end/2;
                    continue;
                }
                break;
            }
        } else {
            smalltop.add(num);
            // small top heapify bottom to top
            int end = smalltop.size()-1;
            while (end/2 > 0) {
                if (smalltop.get(end) < smalltop.get(end/2)) {
                    swap(smalltop, end, end/2);
                    end = end/2;
                    continue;
                }
                break;
            }
        }
    }

    public Double GetMedian() {
        if (cnt%2 == 0) {
            return ((double)bigtop.get(1) + (double)smalltop.get(1)) / 2;
        } else {
            return (double)bigtop.get(1);
        }
    }

    /*
    PriorityQueue<Integer> bigTop = new PriorityQueue(new BigTopCmp());
    PriorityQueue<Integer> smallTop = new PriorityQueue(new SmallTopCmp());
    public void Insert(Integer num) {
        bigTop.add(num);
        smallTop.add(bigTop.poll());
        if (bigTop.size() < smallTop.size()) {
            bigTop.add(smallTop.poll());
        }
    }

    public Double GetMedian() {
        if (bigTop.size() == 0) return 0.0;
        if (bigTop.size() > smallTop.size()) return (double)bigTop.peek();
        else return ((double)bigTop.peek() + (double)smallTop.peek())/2;
    }

    class BigTopCmp implements Comparator<Integer> {
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }
    class SmallTopCmp implements Comparator<Integer> {
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }*/

    /*
    ArrayList<Integer> list = new ArrayList<>();
    Comparator cmp = new SmallTopCmp();
    public void Insert(Integer num) {
        list.add(num);
        list.sort(cmp);
    }

    public Double GetMedian() {
        int mid = list.size()/2;

        if (list.size() % 2 == 0) {
            return ((double)list.get(mid) + (double)list.get(mid-1))/2;
        } else {
            return (double)list.get(mid);
        }
    }

    class BigTopCmp implements Comparator<Integer> {
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }
    class SmallTopCmp implements Comparator<Integer> {
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    } */
}
