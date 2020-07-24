package com.pdsrazor.algo.queue;

// 非线程安全 没有防止并发的措施
public class MyCircleQueue<T> {
    private Object[] val;
    int cap;
    // 环形队列 浪费一个空间 用来区分队列空/满的状态
    // head = tail 代表空
    // (tail + 1) % cap = head 代表满
    private int head, tail;
    //private int moveThreshold;

    public MyCircleQueue(int cap) {
        val = new Object[cap];
        head = tail = 0;
        this.cap = cap;
        //moveThreshold = 10;
    }

    public MyCircleQueue() {
        this(2);
    }

    // 浪费一个空间 用来区分full和empty
    public boolean enqueue(T t) {
        if ((tail+1)%cap != head) {
            val[tail] = t;
            tail = (tail+1)%cap;
            return true;
        } else {
            return false;
        }
    }

    public T dequeue() {
        if (head != tail) {
            T ret = (T)val[head];
            head = (head+1)%cap;
            return ret;
        } else {
            return null;
        }
    }
 }