package com.pdsrazor.algo.list;

import java.util.Random;

import com.pdsrazor.algo.MyDemo;
import com.pdsrazor.algo.utils.*;
import com.pdsrazor.algo.utils.RandomLinkedListGenerator.NodeValueComparator;
import com.pdsrazor.algo.utils.RandomLinkedListGenerator.NodeValueGenerator;

public class ListDemo extends MyDemo {
    // helper
    @Override
    public void usage() {
        System.out.println("ListDemo:");
        System.out.println("  revertLinkedList <length>");
        System.out.println("  midNodeInLinkedList <length>");
        System.out.println("  lastNthNodeInLinkedList <length> <lastN>");
        System.out.println("  detectCircleInLinkedList <length> <circle:true|false>");
        System.out.println("  mergeSortedLinkedList <length>");
        //System.out.println("  Josephus");
        System.out.println("  palindromeLinkedList <length> <palindrome:true|false>");
    }

    class IntegerGenerator extends NodeValueGenerator<Integer> {
        private int bound;
        public IntegerGenerator(int bound) {
            this.bound = bound;
        }
        @Override
        public Integer getNext() {
            return new Random().nextInt(bound);
        }
    }

    class IntegerComparator extends NodeValueComparator<Integer> {
        @Override
        public int compare(ListNode<Integer> n1, ListNode<Integer> n2) {
            return n1.getValue() - n2.getValue();
        }
    }

    // cmd
    @Override
    public int doCmd(String args[]) {
        if (args[0].equals("revertLinkedList")) {
            int len = Integer.parseInt(args[1]);
            ListNode<Integer> list = RandomLinkedListGenerator.genLinkedList(len, new IntegerGenerator(len));
            ListDemo.printLinkedList(list);
            ListNode<Integer> ret = revertLinkedList(list);
            printLinkedList(ret);
            return 1;
        }

        if (args[0].equals("midNodeInLinkedList")) {
            int len = Integer.parseInt(args[1]);
            ListNode<Integer> list = RandomLinkedListGenerator.genLinkedList(len, new IntegerGenerator(len));
            printLinkedList(list);
            System.out.println("mid node index: " + midNodeInLinkedList(list));
            return 1;
        }

        if (args[0].equals("lastNthNodeInLinkedList")) {
            int len = Integer.parseInt(args[1]);
            int lastN = Integer.parseInt(args[2]);
            ListNode<Integer> list = RandomLinkedListGenerator.genLinkedList(len, new IntegerGenerator(len));
            printLinkedList(list);
            ListNode<Integer> node = lastNthNodeInLinkedList(list, lastN);
            System.out.println("last " + lastN + " node val = "
                                + (node != null ? node.value : "null"));
            return 1;
        }

        if (args[0].equals("detectCircleInLinkedList")) {
            int len = Integer.parseInt(args[1]);
            boolean circle = Boolean.parseBoolean(args[2]);
            ListNode<Integer> list;
            if (circle)
                list = RandomLinkedListGenerator.genCircleLinkedList(len, new IntegerGenerator(len), len/2);
            else
                list = RandomLinkedListGenerator.genLinkedList(len, new IntegerGenerator(len));
                // printLinkedList(list);
            boolean ret = detectCircleInLinkedList(list);
            // ListDemo.printIntLinkedList(ret);
            if (ret == true)
                System.out.println("list have a circle");
            else
                System.out.println("list don't have a circle");
            return 1;
        }

        if (args[0].equals("mergeSortedLinkedList")) {
            int len = Integer.parseInt(args[1]);
            ListNode<Integer> list1 = RandomLinkedListGenerator.genOrderedIntegerLinkedList(len, 0);
            ListNode<Integer> list2 = RandomLinkedListGenerator.genOrderedIntegerLinkedList(len, len/2);
            printLinkedList(list1);
            printLinkedList(list2);
            ListNode<Integer> ret = mergeSortedLinkedList(list1, list2, new IntegerComparator());
            printLinkedList(ret);
            return 1;
        }

        if (args[0].equals("palindromeLinkedList")) {
            int len = Integer.parseInt(args[1]);
            boolean palin = Boolean.parseBoolean(args[2]);
            ListNode<Integer> list;
            if (palin)
                list = RandomLinkedListGenerator.genPalindromeLinkedList(len);
            else
                list = RandomLinkedListGenerator.genLinkedList(len, new IntegerGenerator(len));
            printLinkedList(list);
            boolean ret = detectPalindromeLinkedList(list);
            if (ret == true)
                System.out.println("list is a palin");
            else
                System.out.println("list is not a palin");
            return 1;
        }
        return 0;
    }

    // Josephus problem
    public static boolean Josephus() {
        return true;
    }
    
    public static <T> boolean detectPalindromeLinkedList(ListNode<T> head) {
        if (head == null) return false; // 长度为0
        if (head.next == null) return true; // 长度为1
        // 1. 寻找中间节点
        ListNode<T> p, pp;
        p = pp = head;   
        while (true) {
            if (pp.next == null || pp.next.next == null) {
                break;
            }
            p = p.next;
            pp = pp.next.next;
        }
        // 偶数返回的是 前半部的尾节点
        // so反转后半部分，会比较方便

        // 2. 反转前半部分 或 后半部分
        ListNode<T> prev, now, next;
        prev = null;
        now = p.next;
        next = now.next;
        while (next != null) {
            now.next = prev;
            prev = now;
            now = next;
            next = next.next;
        }
        // 跳出循环后 now 指向的是最后一个节点
        now.next = prev;
        System.out.print("reversed list:");
        printLinkedList(now);
        // 3. 比较
        boolean ret = true;
        ListNode<T> h1 = head;
        while (now != null) {
            if (now.value != h1.value) {
                ret = false;
                break;
            }
            now = now.next;
            h1 = h1.next;
        }
        return ret;
    }

    // 链表中环的检测
    public static boolean detectCircleInLinkedList2(ListNode head) {
        // 快慢指针，当快指针追上慢指针的时候，说明有circle
        if(head == null)
            return false;
        ListNode p = head;
        ListNode pp = head;
        while(pp != null && pp.next != null && p.next.next != null) {
            p = p.next;
            pp = pp.next.next;
            if (pp == p)
                return true;
        } 
        return false;
    }

    public static <T> boolean detectCircleInLinkedList(ListNode<T> head) {
        // 快慢指针的应用：分环的长度为奇偶数来考虑下即可
        ListNode<T> p = head;
        ListNode<T> pp = head;
        // 长度1肯定没有环
        if (head == null || head.next == null) return false;
        while(true) {
            if ((p = p.next) == null || (pp = pp.next) == null) return false;
            if ((pp = pp.next) == null) return false;
            if (p == pp) return true; 
        }
    }

    public static <T> ListNode<T> mergeSortedLinkedList(ListNode<T> head1, ListNode<T> head2, NodeValueComparator<T> comparator) {
        // 有序连表合并， 从旧链表中remove，在add到新链表即可
        ListNode<T> ret = null;
        ListNode<T> head = null;

        while (head1 != null && head2 != null) {
            if (comparator.compare(head1, head2) < 0) {
                if(head == null) {
                    ret = head1;
                    head = ret;
                } else {
                    ret.next = head1;
                    ret = ret.next;
                }
                head1 = head1.next;
            } else {
                if(head == null) {
                    ret = head2;
                    head = ret;
                } else {
                    ret.next = head2;
                    ret = ret.next;
                }
                head2 = head2.next;
            }
            // 这里要注意， 不能在这里赋值
            // ret = ret.next;
        }

        // 处理剩下的链表
        if (head1 != null) {
            if(head == null) {
                ret = head1;
                head = ret;
            } else {
                ret.next = head1;
            }
        }
        if (head2 != null) {
            if(head == null) {
                ret = head2;
                head = ret;
            } else {
                ret.next = head2;
            }
        }
        return head;
    }

    // 两个有序链表合并
    /*
    public static ListNode mergeSortedLinkedList2(ListNode head1, ListNode head2) {
        IntListNode p1 = (IntListNode)head1;
        IntListNode p2 = (IntListNode)head2;
        IntListNode head = null;
        IntListNode prev = null;
        IntListNode node = null;
        while(p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                node = p1;
                p1 = (IntListNode)p1.next;
            } else { 
                node = p2;
                p2 = (IntListNode)p2.next;
            }
            if (head == null) {
                head = prev = node;
            } else {
                prev.next = node;
                prev = node;
            }
        }
        if (p1 != null) {
            if (head == null) {
                head = p1;
            } else {
                prev.next = p1;
            }
            //p1 = (IntListNode)p1.next;
        }
        if (p2 != null) {
            if (head == null) {
                head = p2;
            } else {
                prev.next = p2;
            }
           // p2 = (IntListNode)p2.next;
        }
        return head;
    }*/

    // 删除链表倒数第n个节点
    public static ListNode lastNthNodeInLinkedList2(ListNode head, int lastN) {
        // 快指针先走lastN步. 当快指针到尾部的时候，慢指针就是要找的
        if (head == null)
            return head;
        ListNode p = head;
        int i = 0;
        for(i = 0; i < lastN; i++) {
            p = p.next;
            if (p == null)
                break;
        }
        // n 大于长度的情况要注意
        if (i < lastN)
            return null;
        
        ListNode node = head;
        // 当快指针指向最后一个node时 循环结束
        while(p.next != null) {
            node = node.next;
            p = p.next;
        }
        return node;
    }

    // 定义倒数第一个为最后一个node
    public static <T> ListNode<T> lastNthNodeInLinkedList(ListNode<T> head, int lastN) {
        // 核心思想是快指针先走N-1步，需要注意的是边界的处理
        // 如果 lastN < 0 则返回 null
        if (head == null || lastN < 0) return null;
        // 如果lastN大于连表长度，则返回null
        ListNode<T> node = head;
        int i = 0;
        // lastN ： 先走N-1步
        while (node != null && i < (lastN-1)) {
            node = node.next;
            i++;
        }
        i--; // 边界处理， 长度n，倒数第n+1个
        // 说明lastN > 链表长度
        if (i < (lastN-1)) return null;
        // 处理正常情况 , 这里结束条件注意下
        ListNode<T> ret = head;
        while (node.next != null) {
            node = node.next;
            ret = ret.next;
        }
        return ret;
    }

    // 打印链表
    public static <T> void printLinkedList(ListNode<T> head) {
        ListNode<T> node = head;
        int i = 0;
        if (node == null) {
            System.out.println("empty linked list.");
            return;
        }
        while(node.next != null) {
           System.out.print(node.value + "->");
           node = node.next;
        }
        System.out.println(node.value);
    }

    // 单链表翻转
    public static <T> ListNode<T> revertLinkedList(ListNode<T> head) {
        // 特殊情况处理
        if (head == null || head.next == null) {
            return head;
        }
        // 三个指针完成翻转
        ListNode<T> prv = null;
        ListNode<T> now = null;
        ListNode<T> nex = null;
        prv = null;
        now = head;
        nex = now.next;
        while(nex != null) {
            now.next = prv;
            prv = now;
            now = nex;
            nex = nex.next;    
        }
        now.next = prv;
        return now;
    }

    public static <T> int midNodeInLinkedList(ListNode<T> head) {
        // 快慢指针
        ListNode<T> p1 = head;
        ListNode<T> p2 = head;
        int ret = 0;

        // 长度为0 1 2 特殊处理
        if (head == null) {
            return -1;
        }
        if (head.next == null || head.next.next == null) {
            return 0;
        }
        while(true) {
            if (p2.next == null) break;
            p2 = p2.next;
            p1 = p1.next;
            if (p2.next == null) break;
            p2 = p2.next;
            // 对于奇数长度，选择小的作为返回值
            ret++;
        }
        return ret;
    }

    // 求链表中间节点
    public static int midNodeInLinkedList2(ListNode head) {
        // 空、1个节点、2个节点直接返回head为中间节点
        if (head == null || head.next == null || head.next.next == null) {
            return 0;
        }
        // 所谓快慢指针方法
        ListNode p = head;
        ListNode pp = head;
        int i = 0;
        // walk list until pp = null
        while((p = p.next) != null &&
                (pp = pp.next) != null && (pp = pp.next) != null) {
            // nothing
            i++;
        }
        return i;
    }
}