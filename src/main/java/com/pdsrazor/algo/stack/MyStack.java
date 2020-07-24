package com.pdsrazor.algo.stack;

// 基于数组的stack
public class MyStack<T> {
    private Object[] val;
    private int sp;
    int cap;

    public MyStack(int cap) {
        val = new Object[cap];
        sp = 0;
        this.cap = cap;
    }

    public MyStack() {
        this(1);
    }   

    public boolean push(T t) {
        if(sp >= val.length) {
            Object[] tmp = new Object[val.length*2];
            for (int i = 0; i < val.length; i++) {
                tmp[i] = val[i];
            }
            val = tmp;
        }
        val[sp++] = t;
        return true;
    }

    public T pop() {
        if(sp > 0) {
            return (T)val[--sp];
           // return t;
        }
        return null;
    }
}