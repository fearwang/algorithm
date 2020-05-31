package com.pdsrazor.algo.utils;

public class ListNode<T> {
    // 这里应该是private，但为了demo方便设置为public
    public ListNode<T> next;
    public ListNode<T> prev;
    public T value;

    public ListNode() {
        this(null, null, null);
    }

    public ListNode(T value) {
        this(null, null, value);
    }

    public ListNode(ListNode<T> prev, ListNode<T> next, T value) {
        this.next = next;
        this.value = value;
        this.prev = prev;
    }

    public void setNext(ListNode<T> next) {
        this.next = next;
    }

    public ListNode<T> getNext() {
        return next;
    }

    public void setPrev(ListNode<T> prev) {
        this.prev = prev;
    }

    public ListNode<T> getPrev() {
        return prev;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}