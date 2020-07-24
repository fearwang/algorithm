package com.pdsrazor.algo.utils;

public class BinaryTreeNode {
    public BinaryTreeNode left = null;
    public BinaryTreeNode right = null;
    public int value = 0;
    public int level = 0;
    public int col = 0;
    public int lenOfValueInStr = 0;

    public BinaryTreeNode() {
        left = null;
        right = null;
        value = 0;
    }

    public BinaryTreeNode(int val) {
        left = null;
        right = null;
        value = val;
    }

    public void calcAndSetLengthOfValueInstr() {
        String str = String.valueOf(value);
        lenOfValueInStr = str.length();
    }

    public String toString() {
        return value + " ";
    }
}