package com.pdsrazor.jzoffer;

import com.pdsrazor.algo.utils.ListNode;

public class Jz46_LastRemaining extends Solution {

    @Override
    void solute(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        System.out.println(n + " childs, " + m + " count, remaining: " + LastRemaining_Solution(n, m));
        System.out.println(n + " childs, " + m + " count, remaining: " + LastRemaining_Solution2(n, m));
    }

    public int LastRemaining_Solution(int n, int m) {
        if (n <= 0|| m <= 0) return -1;
        if (n == 1) return 0;
        int cnt = n;
        int[] child = new int[n];
        for (int i = 0; i < n; i++) {
            child[i] = 1;
        }
        int idx = 0;
        while (cnt > 1) {
            for (int j = 0; j < m; j++) {
                while (child[idx] == 0) {
                    idx = (idx+1)%n; // 找到报数为j的孩子
                }
                if (j != m-1) {  // 如果不是第m-1个孩子，则++， 继续循环， 否则找到了要删除的孩子，本次报数结束
                    idx = (idx+1)%n;
                }
            }
            child[idx] = 0;  // 找到的孩子 结束游戏
            idx = (idx+1)%n; // 指向下一个槽，继续下一轮报数
            cnt--;
        }
        for (int i = 0; i < n; i++) {
            if (child[i] == 1) return i;
        }
        return -1;
    }

    public int LastRemaining_Solution2(int n, int m) {
        if (n <= 0|| m <= 0) return -1;
        if (n == 1) return 0;  // 过滤长度为1的情况

        ListNode<Integer> head = new ListNode<>(0);
        head.next = head;
        ListNode<Integer> p = head;
        for (int i = 1; i < n; i++) {
            ListNode<Integer> node = new ListNode<>(i);
            node.next = p.next;
            p.next = node;
            p = node;
        }

        // 初始化为尾节点
        ListNode<Integer> prev = p;
        p = head;
        int cnt = n;
        while (cnt > 1) {
            for (int i = 0; i < m-1; i++) {
                prev = p;
                p = p.next;
            }
            prev.next = p.next;
            cnt--;
            p = prev.next;
        }
        return prev.value;
    }

}
