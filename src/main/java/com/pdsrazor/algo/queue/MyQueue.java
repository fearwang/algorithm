package com.pdsrazor.algo.queue;

// 非线程安全 没有防止并发的措施
public class MyQueue<T> {
    private Object[] val;
    int cap;
    private int head, tail;
    private int moveThreshold;

    public MyQueue(int cap) {
        val = new Object[cap];
        head = tail = 0;
        this.cap = cap;
        moveThreshold = 10;
    }

    public MyQueue() {
        this(1);
    }

    public boolean enqueue(T t) {
        if (tail >= cap) {
            Object[] tmp = new Object[cap*2];
            for (int i = 0; i < cap; i++) {
                tmp[i] = val[i];    
            }
            val = tmp;
        }
        val[tail++] = t;
        return true;
    }

    // todo：移动元素，防止数组一直增长
    public T dequeue() {
        if (head >= tail)
            return null;
        else {
            T ret = (T)val[head++];
            return ret;
            //if (head > moveThreshold) {
                
            //}
        }
    }
 }