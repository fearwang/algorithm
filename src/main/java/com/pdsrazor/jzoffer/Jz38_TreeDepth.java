package com.pdsrazor.jzoffer;

import java.util.LinkedList;

import com.pdsrazor.algo.tree.BinaryTreeDemo;
import com.pdsrazor.algo.utils.BinaryTreeFactory;
import com.pdsrazor.algo.utils.BinaryTreeNode;

public class Jz38_TreeDepth extends Solution {

    @Override
    void solute(String[] args) {
        int size = Integer.parseInt(args[0]);
        BinaryTreeNode root = new BinaryTreeFactory(size) .genLinkedBinaryTree(size,BinaryTreeFactory.GEN_RANDOM);
        BinaryTreeDemo.graphPrintBinaryTree(root);
        System.out.println("depth = " + TreeDepth(root));
        System.out.println("depth = " + TreeDepth2(root));
    }
    
    // 队列解法
    public int TreeDepth(BinaryTreeNode root) {
        if (root == null) return 0;
        LinkedList<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        int curTotal = 1; // 第一层只有root一个节点
        int nextTotal = 0;
        int cur = 0;
        int depth = 0;
        while (!queue.isEmpty()) {
            if (cur == 0) {
                depth++;
            }
            BinaryTreeNode node = queue.poll();
            cur++;
            if (node.left != null) {
                queue.add(node.left);
                nextTotal++;
            }
            if (node.right != null) {
                queue.add(node.right);
                nextTotal++;
            }
            if (cur == curTotal) {
                cur = 0;
                curTotal = nextTotal;
                nextTotal = 0;
            }
        }
        return depth;
    }

    public int TreeDepth2(BinaryTreeNode root) {
        if (root == null) return 0;
        int left = 0;
        int right = 0;
        if (root.left != null) left = TreeDepth2(root.left);
        if (root.right != null) right = TreeDepth2(root.right);
        return (left > right ? left : right) + 1;
    }
}