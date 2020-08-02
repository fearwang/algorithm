package com.pdsrazor.jzoffer;

import java.util.Stack;

import com.pdsrazor.algo.tree.BinaryTreeDemo;
import com.pdsrazor.algo.utils.BinaryTreeNode;

public class Jz62_KthNodeInBST extends Solution {
    @Override
    void solute(String[] args) {
        int size = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);
        BinaryTreeNode root = BinaryTreeDemo.genBST(size);
        BinaryTreeDemo.graphPrintBinaryTree(root);
        BinaryTreeNode ret = KthNode2(root, k);
        System.out.println("kth node of tree: "  + (ret != null ? ret.value : "don't exist"));
    }

    public int cur = 0;
    BinaryTreeNode KthNode(BinaryTreeNode pRoot, int k) {
        if (pRoot == null || k <= 0) return null;
        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode p = pRoot;
        do {
            // 新节点
            if (stack.isEmpty() || p != stack.peek()) {
                // 存在左孩子，则新节点入stack等待左子树遍历完毕
                if (p.left != null) {
                    stack.push(p);
                    p = p.left;
                } else {
                    // 否则先遍历自己
                    if (++cur == k) return p;
                    System.out.print(p.value + " ");
                    // 然后如果有右孩子，则新节点为右孩子，下个循环就处理右子树
                    if (p.right != null) {
                        p = p.right;
                    } else {
                        // 如果新节点没有左孩子 也没有右孩子，则下个循环遍历stack顶的节点
                        if (stack.size() <= 0) break;
                        p = stack.peek();
                    }
                }
            } else {
                // p是stack上保存的，处理到此类节点代表它的左子树遍历完毕
                // 首先遍历自己
                p = stack.pop();
                if (++cur == k) return p;
                System.out.print(p.value + " ");
                // 然后如果有右子树，则继续遍历右子树
                if (p.right != null) {
                    p = p.right;
                } else {
                    // 没有右子树则 继续遍历下个stack顶的节点
                    if (stack.size() <= 0) break;
                    p = stack.peek();
                }
            }
        } while (true);
        System.out.println();
        return null;
    }

    int tk;
    BinaryTreeNode ret = null;
    BinaryTreeNode KthNode2(BinaryTreeNode pRoot, int k) {
        if (pRoot == null || k <= 0) return null;
        tk = k;
        walkInOrder(pRoot);
        return ret;
    }

    void walkInOrder(BinaryTreeNode root) {
        if (root.left != null) walkInOrder(root.left);
        if (++cur == tk) {
            ret = root;
            return;
        }
        if (root.right != null) walkInOrder(root.right);
    }
}