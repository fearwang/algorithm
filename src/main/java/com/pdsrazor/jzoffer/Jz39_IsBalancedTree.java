package com.pdsrazor.jzoffer;

import com.pdsrazor.algo.tree.BinaryTreeDemo;
import com.pdsrazor.algo.utils.BinaryTreeFactory;
import com.pdsrazor.algo.utils.BinaryTreeNode;

public class Jz39_IsBalancedTree extends Solution {

    @Override
    void solute(String[] args) {
        int size = Integer.parseInt(args[0]);
        BinaryTreeNode root = new BinaryTreeFactory(size) .genLinkedBinaryTree(size,BinaryTreeFactory.GEN_RANDOM);
        BinaryTreeDemo.graphPrintBinaryTree(root);
        System.out.println("balance: " + IsBalanced_Solution(root));
        
        root = new BinaryTreeFactory(size) .genLinkedBinaryTree(size,BinaryTreeFactory.GEN_SORTED);
        BinaryTreeDemo.graphPrintBinaryTree(root);
        System.out.println("balance: " + IsBalanced_Solution(root));
    }

    // 是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树
    public boolean IsBalanced_Solution(BinaryTreeNode root) {
        if (root == null) return true;
        int left = 0;
        int right = 0;
        if (root.left != null) left = TreeDepth2(root.left);
        if (root.right != null) right = TreeDepth2(root.right);
        int delta = 0;
        if (left > right) delta = left - right;
        else delta = right - left;
        
        boolean bl = true;
        boolean br = true;
        if (root.left != null) bl = IsBalanced_Solution(root.left);
        if (root.right != null) br = IsBalanced_Solution(root.right);

        return bl && br && (delta <= 1); 
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