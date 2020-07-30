package com.pdsrazor.jzoffer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

import com.pdsrazor.algo.tree.BinaryTreeDemo;
import com.pdsrazor.algo.utils.BinaryTreeFactory;
import com.pdsrazor.algo.utils.BinaryTreeNode;

public class Jz59_PrintZ extends Solution {
    @Override
    void solute(String[] args) {
        int size = Integer.parseInt(args[0]);
        BinaryTreeNode root = new BinaryTreeFactory(size).genLinkedBinaryTree(size, BinaryTreeFactory.GEN_RANDOM);
        BinaryTreeDemo.graphPrintBinaryTree(root);
        System.out.println("Z of tree: "  + Print(root));
    }

    public ArrayList<ArrayList<Integer> > Print(BinaryTreeNode pRoot) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        if (pRoot == null) return list;
        
        BinaryTreeNode p = pRoot;
        boolean toRight = false;
        int curTotal = 1; // 第一层只有root一个节点
        int nextTotal = 0;
        int cnt = 0;
        LinkedList<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(pRoot);
        ArrayList<Integer> level = null;
        Stack<Integer> stack = new Stack<>();
        while (queue.size() > 0) {
            if (cnt == 0) { // 新层，重新搞一个数组
                level = new ArrayList<>();
                toRight = !toRight;
            }
            p = queue.poll();
            cnt++;
            if (toRight) {
                level.add(p.value);
            } else {
                stack.push(p.value);
            }

            if (p.left != null) {
                nextTotal++;
                queue.add(p.left);
            }
            if (p.right != null) {
                nextTotal++;
                queue.add(p.right);
            }

            if (cnt >= curTotal) {
                cnt = 0;
                curTotal = nextTotal;
                nextTotal = 0;
                if (!toRight) {
                    while (stack.size() > 0) {
                        level.add(stack.pop());
                    }
                }
                list.add(level); // 一层结束
            }
        }
        return list;
    }
}