package com.pdsrazor.algo.utils;

//import java.util.Random;

public class RandomLinkedListGenerator {
    public static abstract class NodeValueGenerator<T> {
        public abstract T getNext();
    }

    public static abstract class NodeValueComparator<T> {
        // n1 > n2: positive, n1 < n2: negative, n1 == n2: zero 
        public abstract int compare(ListNode<T> n1, ListNode<T> n2);
    }

    public static <T> ListNode<T> genLinkedList(int size, NodeValueGenerator<T> generator) {
        ListNode<T> head = null;
        ListNode<T> prev = null;
        for (int i = 0; i < size; i++) {
            T val = generator.getNext();
            ListNode<T> node = new ListNode<T>();
            node.setValue(val);
            if (head == null) {
                head = prev = node;
            } else {
                prev.setNext(node);
                node.setPrev(prev);
                prev = node;
            }
        }
        return head;
    }

    // BUG： 类似带环的连表， 这个接口产生的是单向circle list
    public static <T> ListNode<T> genCircleLinkedList(int size, NodeValueGenerator<T> generator, int circleIdx) {
        ListNode<T> head = null;
        ListNode<T> prev = null;
        ListNode<T> node = null;
        ListNode<T> circleNode = null;
        for (int i = 0; i < size; i++) {
            T val =  generator.getNext();
            node = new ListNode<T>();
            node.setValue(val);
            if (head == null) {
                head = prev = node;
            } else {
                prev.setNext(node);
                node.setPrev(prev);
                prev = node;
            }
            // 保存环的入口节点
            if (i == circleIdx) {
                circleNode = node;
            }
        }
        // 尾节点 和 环的入口节点 连接起来
        if (node != null) {
            node.setNext(circleNode);
            circleNode.setPrev(node);
        }
        return head;
    }

    public static ListNode<Integer> genOrderedIntegerLinkedList(int len, int start) {
        ListNode<Integer> head = null;
        ListNode<Integer> prev = null;
        ListNode<Integer> node = null;
        for (int i = 0 + start; i < len + start; i++) {
            node = new  ListNode<Integer>();
            node.setValue(i);
            if (head == null) {
                head = prev = node;
            } else {
                prev.setNext(node);
                node.setPrev(prev);
                prev = node;
            }
        }
        return head;
    }

    public static ListNode<Integer> genPalindromeLinkedList(int len) {
        if (len == 0) return null;
        if (len == 1) return new ListNode<Integer>(0);
        boolean odd = true;
        if (len % 2 == 0) {
            odd = false;
        }
        int mid = len / 2;
        ListNode<Integer> head = null;
        ListNode<Integer> node = null;
        ListNode<Integer> prev = null;
        for (int i = 0; i < mid; i++) {
            node = new ListNode<Integer>(i);
            if (head == null) head = node;
            if (prev != null) {
                prev.next = node;
            }
            prev = node;
        }
        for (int i = (odd) ? (mid) : (mid-1); i >= 0; i--) {
            if (head == null) head = node;
            node = new ListNode<Integer>(i);
            if (prev != null) {
                prev.next = node;
            }
            prev = node;
        }
        return head;
    }
}
