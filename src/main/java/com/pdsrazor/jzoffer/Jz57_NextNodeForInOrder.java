package com.pdsrazor.jzoffer;

import com.pdsrazor.algo.tree.BinaryTreeDemo;
import com.pdsrazor.algo.utils.BinaryTreeFactory;
import com.pdsrazor.algo.utils.BinaryTreeNode;

public class Jz57_NextNodeForInOrder extends Solution {
    @Override
    void solute(String[] args) {
        int size = Integer.parseInt(args[0]);
        BinaryTreeNode root = new BinaryTreeFactory(size).genLinkedBinaryTree(size, BinaryTreeFactory.GEN_RANDOM);
        BinaryTreeDemo.graphPrintBinaryTree(root);
        System.out.println(root.value + "'s next node: " + GetNext(root).value);
    }

    // 中序遍历的 next节点
    public BinaryTreeNode GetNext(BinaryTreeNode pNode) {
        BinaryTreeNode p = pNode;
        if (p == null) return null;

        // 如果有右子树，那么就找到 右子树中序遍历的第一个节点
        if (p.right != null) {
            BinaryTreeNode t = p.right;
            while (t.left != null) {
                t = t.left;
            }
            return t;
        }

        // 如果没有右子树，那么pNode是自己这颗子树中序遍历的最后一个节点
        // 顺着父节点向上，找到向上遍历路径上 第一个为左孩子的节点的父节点
        p = pNode;
        while (p.parent != null && p.parent.right == p) {
            p = p.parent;
        }
        return p.parent;
    }
}