package com.pdsrazor.algo.utils;

import java.util.Random;
import com.pdsrazor.algo.tree.BinaryTreeDemo;

public class BinaryTreeFactory {
    public static int GEN_RANDOM = 1;
    public static int GEN_SORTED = 2;
    public static int GEN_SORTED_DUP= 2; // 允许存在重复的数据

    public Random random = null;
    public int mSize; // 二叉树节点的数量
    public static int BOUND = 1000;
    public static int BOUND_DUP = 15; // 刻意产生重复的节点
    public int[] mVals;
    public int mCurSize = 0;

    public BinaryTreeFactory(int size) {
        this.mSize = size;
        random = new Random();
        mVals = new int[size];
    }

    boolean hasSameVal(int v) {
        for (int i = 0; i < mCurSize; i++) {
            if (mVals[i] == v) return true;
        }
        return false;
    }

    public void reset(int size) {
        this.mSize = size;
        random = new Random();
        mVals = new int[size];
    }

    int getSmallerValue(int data) {
        int val = 0;
        do {
            val = random.nextInt(BOUND);
        } while (hasSameVal(val)|| val >= data);
        mVals[mCurSize++] = val;
        return val;
    }

    int getBiggerValue(int data) {
        int val = 0;
        do {
            val = random.nextInt(BOUND);
        } while (hasSameVal(val)|| val <= data);
        mVals[mCurSize++] = val;
        return val;
    }

    int getNextValue() {
        int val = 0;
        do {
            val = random.nextInt(BOUND);
        } while (hasSameVal(val));
        mVals[mCurSize++] = val;
        return val;
    }

    // 插入节点的方式构造 随机二叉树
    private int ranDomCallBack(BinaryTreeNode root, int size) {
        if (size <= 0) {
            return 0;
        }
        int neededSize = size;
        int leftSize = random.nextInt(size+1);
        int rightSize = size - leftSize;
        //System.out.println("left: " + leftSize + ", right: " + rightSize + ", need: " + neededSize);
        if(leftSize > 0) {
            //System.out.println("gen left");
            BinaryTreeNode node = new BinaryTreeNode(getNextValue());
            root.left = node;
            node.parent = root;
            if (leftSize - 1 > 0) {
                leftSize = ranDomCallBack(node, leftSize-1) + 1;
            } else {
                leftSize = 1;
            }
        }
        if(rightSize > 0) {
            //System.out.println("gen right");
            BinaryTreeNode node = new BinaryTreeNode(getNextValue());
            root.right = node;
            node.parent = root;
            if (rightSize - 1 > 0) {
                rightSize = ranDomCallBack(node, rightSize-1) + 1;
            } else {
                rightSize = 1;
            }
        }
        if (leftSize + rightSize != neededSize) 
            System.out.println("size mismatch, result: " + (leftSize+rightSize) + ", expect: " + neededSize);
        return leftSize + rightSize;
    }

    // 生成二叉查找树
    public BinaryTreeNode sortedCallback(int size) {
        if (size == 0) return null;
        int[] arr = RandomArrayGenerator.getCircleIntArray(size, BOUND);
        return BinaryTreeDemo.genBSTFromData(arr);
    }

    public BinaryTreeNode sortedCallbackDup(int size) {
        if (size == 0) return null;
        int[] arr = RandomArrayGenerator.getCircleIntArray(size, BOUND_DUP);
        return BinaryTreeDemo.genBSTFromData(arr);
    }

    public BinaryTreeNode genLinkedBinaryTree(int size, int type) {
        this.mSize = size;
        BinaryTreeNode root = null;
        if (size == 0) return null;
        if (type == GEN_RANDOM) {
            root = new BinaryTreeNode(getNextValue()); // root在第一层
            ranDomCallBack(root, size-1);
        }
        if (type == GEN_SORTED) {
            root = sortedCallback(size);
        }
        if (type == GEN_SORTED_DUP) {
            root = sortedCallbackDup(size);
        }
        return root;
    }
}