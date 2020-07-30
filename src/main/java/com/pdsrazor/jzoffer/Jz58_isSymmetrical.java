package com.pdsrazor.jzoffer;

import java.util.LinkedList;
import java.util.Stack;

import com.pdsrazor.algo.tree.BinaryTreeDemo;
import com.pdsrazor.algo.utils.BinaryTreeFactory;
import com.pdsrazor.algo.utils.BinaryTreeNode;

public class Jz58_isSymmetrical extends Solution {
    @Override
    void solute(String[] args) {
        int size = Integer.parseInt(args[0]);
        BinaryTreeNode root = new BinaryTreeFactory(size).genLinkedBinaryTree(size, BinaryTreeFactory.GEN_RANDOM);
        BinaryTreeDemo.graphPrintBinaryTree(root);
        System.out.println("tree is isSymmetrical =  "  + isSymmetrical2(root));

        root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(2);
        BinaryTreeDemo.graphPrintBinaryTree(root);
        System.out.println("tree is isSymmetrical =  "  + isSymmetrical2(root));
    }

    boolean isSymmetrical2(BinaryTreeNode pRoot) {
        if (pRoot == null) return true;
        if (pRoot.left == null && pRoot.right == null) return true;

        LinkedList<BinaryTreeNode> queueL = new LinkedList<>();
        LinkedList<BinaryTreeNode> queueR = new LinkedList<>();
        if (pRoot.left != null && pRoot.right != null) {
            queueL.add(pRoot.left);
            queueR.add(pRoot.right);
            BinaryTreeNode pl = null;
            BinaryTreeNode pr = null;
            while (queueL.size() > 0 && queueR.size() > 0) {
                pl = queueL.poll();
                pr = queueR.poll();
                if (pl.value != pr.value) return false;
                
                if (pl.left != null && pr.right == null) return false;
                if (pl.left == null && pr.right != null) return false;
                
                if (pl.right != null && pr.left == null) return false;
                if (pl.right == null && pr.left != null) return false;

                if (pl.left != null && pr.right != null) {
                    queueL.add(pl.left);
                    queueR.add(pr.right);
                }

                if (pl.right != null && pr.left != null) {
                    queueL.add(pl.right);
                    queueR.add(pr.left);
                }
            }
            return true;
        }

        return false;
    }

    boolean isSymmetrical(BinaryTreeNode pRoot)
    {
        if (pRoot == null) return true;
        if (pRoot.left == null && pRoot.right == null) return true;
        Stack<BinaryTreeNode> sLeft = new Stack<>();
        Stack<BinaryTreeNode> sRight = new Stack<>();
        if (pRoot.left != null && pRoot.right != null) {
            sLeft.push(pRoot.left);
            sRight.push(pRoot.right);
            BinaryTreeNode pl = pRoot.left;
            BinaryTreeNode pr = pRoot.right;
            while (sLeft.size() > 0 && sRight.size() > 0) {
                if (pl.left != null && pr.right != null) {
                    sLeft.push(pl.left);
                    sRight.push(pr.right);
                    pl = pl.left;
                    pr = pr.right;
                } else if (pl.left == null && pr.right == null) {
                    // pop
                    pl = sLeft.pop();
                    pr = sRight.pop();
                    if (pl.value != pr.value) return false;
                    if (pl.right != null && pr.left != null) {
                        sLeft.push(pl.right);
                        sRight.push(pr.left);
                        pl = pl.right;
                        pr = pr.left;
                    } else if (pl.right == null && pr.left == null) {
                        if (sLeft.size() > 0 && sRight.size() > 0) {
                            pl = sLeft.pop();
                            pr = sRight.pop();
                            if (pl.value != pr.value) return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}