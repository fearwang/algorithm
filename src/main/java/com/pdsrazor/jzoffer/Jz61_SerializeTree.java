package com.pdsrazor.jzoffer;

import java.util.ArrayList;
import java.util.LinkedList;

import com.pdsrazor.algo.tree.BinaryTreeDemo;
import com.pdsrazor.algo.utils.BinaryTreeFactory;
import com.pdsrazor.algo.utils.BinaryTreeNode;

public class Jz61_SerializeTree extends Solution {
    @Override
    void solute(String[] args) {
        int size = Integer.parseInt(args[0]);
        BinaryTreeNode root = new BinaryTreeFactory(size).genLinkedBinaryTree(size, BinaryTreeFactory.GEN_RANDOM);
        BinaryTreeDemo.graphPrintBinaryTree(root);
        String serial = Serialize(root);
        System.out.println("serialize of tree: "  + serial);
        BinaryTreeNode deSerialRoot = Deserialize(serial);
        System.out.println("deserialize: ");
        BinaryTreeDemo.graphPrintBinaryTree(deSerialRoot);
        //ArrayList<Integer> list = new ArrayList<>();
        //list.add(0, 1);
        //list.add(1, 2);
        //System.out.println("array: " + list.size());
    }

    String Serialize(BinaryTreeNode root) {
        if (root == null) return null;
        LinkedList<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        BinaryTreeNode p = null;
        StringBuilder sb = new StringBuilder();
        sb.append(root.value);
        sb.append("!");
        while (queue.size() > 0) {
            p = queue.poll();
            if (p.left != null) {
                queue.add(p.left);
                sb.append(p.left.value);
                sb.append("!");
            }  else {
                sb.append("#");
            }
            if (p.right != null) {
                queue.add(p.right);
                sb.append(p.right.value);
                sb.append("!");
            }  else {
                sb.append("#");
            }
        }
        return sb.toString();
    }
    
    BinaryTreeNode Deserialize(String str) {
        if (str == null || str.length() == 0 || str.equals("#")) return null;

        LinkedList<BinaryTreeNode> queue = new LinkedList<>();
        char[] arr = str.toCharArray();
        int start = 0;
        int end = 0;
        int i = 0;
        // root节点单独处理
        for (i = start; i < arr.length; i++) {
            if (arr[i] == '!' || arr[i] == '#') {
                break;
            }
        }
        end = i;
        String tmpstr = new String(arr, start, end-start);
        int val = Integer.parseInt(tmpstr);
        start = end + 1;
        BinaryTreeNode root = new BinaryTreeNode(val);
        
        queue.add(root);
        BinaryTreeNode p = null;
        while (queue.size() > 0) {
            p = queue.poll();
            for (i = start; i < arr.length; i++) {
                if (arr[i] == '!' || arr[i] == '#') {
                    break;
                }
            }
            end = i;
            tmpstr = new String(arr, start, arr[start] == '#' ? 1 : (end-start));
            start = end + 1;
            if (!tmpstr.equals("#")) {
                val = Integer.parseInt(tmpstr);
                p.left = new BinaryTreeNode(val);
                queue.add(p.left);
            }
            
            for (i = start; i < arr.length; i++) {
                if (arr[i] == '!' || arr[i] == '#') {
                    end = i;
                    break;
                }
            }
            tmpstr = new String(arr, start, arr[start] == '#' ? 1 : (end-start));
            start = end + 1;
            if (!tmpstr.equals("#")) {
                val = Integer.parseInt(tmpstr);
                p.right = new BinaryTreeNode(val);
                queue.add(p.right);
            }
        }
        return root;
    }
}