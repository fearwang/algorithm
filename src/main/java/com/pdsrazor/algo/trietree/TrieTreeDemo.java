package com.pdsrazor.algo.trietree;

import java.util.ArrayList;
import java.util.Stack;

import com.pdsrazor.algo.MyDemo;

public class TrieTreeDemo extends MyDemo {

    @Override
    public void usage() {
        System.out.println("TrieTreeDemo:");
        System.out.println("  find <str>");
        System.out.println("  match <str>");
        System.out.println("  printAll");
    }

    @Override
    public int doCmd(String[] args) {
        MyTrieTree tree = new MyTrieTree();
        tree.insert("how");
        tree.insert("hi");
        tree.insert("her");
        tree.insert("hello");
        tree.insert("so");
        tree.insert("see");
        if (args[0].equals("find")) {
            if (tree.find(args[1])) {
                System.out.println("find " + args[1]);
            } else {
                System.out.println("do not find " + args[1]);
            }
            /*
            ArrayList<String> ret1 = tree.match(args[1]);
            if (ret1 != null) {
                for(int i = 0; i < ret1.size(); i++) {
                    System.out.println("match: " + ret1.get(i));
                }
            } else {
                System.out.println("match nothing");
            }
            ArrayList<String> ret = tree.match2(args[1]);
            if (ret != null) {
                for(int i = 0; i < ret.size(); i++) {
                    System.out.println("match2: " + ret.get(i));
                }
            } else {
                System.out.println("match2 nothing");
            }*/
            return 1;
        }
        if (args[0].equals("match")) {
            System.out.println("target: " + args[1] + "\nmatch: " + tree.match(args[1]));
            return 1;
        }
        if (args[0].equals("printAll")) {
            System.out.println("print list:" +  tree.toList());
            return 1;
        }
        return 0;
    } 
}

class MyTrieNode {
    char mdata;
    MyTrieNode mChildren[];
    boolean mIsEndOfStr;
    int mIdx = 0; // 匹配前缀字串时用来判断当前节点是否已经找完所有子节点

    public MyTrieNode(char data) {
        mdata = data;
        mIsEndOfStr = false;
        mChildren = new MyTrieNode[26]; // 支持a-z
    }
}

class MyTrieTree {
    public MyTrieNode mRoot;

    public MyTrieTree() {
        mRoot = new MyTrieNode('\0');
    }

    public void insert(String str) {
        MyTrieNode p = mRoot;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (p.mChildren[c-'a'] == null) {
                p.mChildren[c-'a'] = new MyTrieNode(c);
            }
            p = p.mChildren[c-'a'];
        }
        p.mIsEndOfStr = true;
    }

    public boolean find(String str) {
        MyTrieNode p = mRoot;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (p.mChildren[c-'a'] == null) {
                // System.out.println(c + " not found");
                return false;
            }
            p = p.mChildren[c-'a'];
        }
        
        return p.mIsEndOfStr ? true : false;
    }

    // 遍历打印字符串
    public ArrayList<String> toList() {
        MyTrieNode p = mRoot;
        Stack<MyTrieNode> stack = new Stack<>();
        ArrayList<String> rets = new ArrayList<>();
        stack.add(p);
        if (p.mIsEndOfStr) {
            rets.add(buildString(stack));
        }
        while (stack.size() > 0) {
            MyTrieNode node = stack.peek();
            for (int i = node.mIdx; i < 26; i++) {
                node.mIdx = i+1;
                if (node.mChildren[i] != null) {
                    stack.add(node.mChildren[i]);
                    if (node.mChildren[i].mIsEndOfStr) {
                        rets.add(buildString(stack));
                    }
                    break;
                }
            }
            if (node.mIdx >= 26) {
                node.mIdx = 0;
                stack.pop();
            }   
        }

        return rets;
    }

    // 类似搜索关键词提示功能
    public ArrayList<String> match(String str) {
        ArrayList<String> mstr = new ArrayList<>();
        Stack<MyTrieNode> chs = new Stack<>();
        // 第一步还是看能不能找到前缀字串
        MyTrieNode p = mRoot;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (p.mChildren[c-'a'] == null) return null;
            chs.add(p.mChildren[c-'a']);
            p = p.mChildren[c-'a'];
        }
        if (p.mIsEndOfStr) { // 特殊情况，前缀字串本身是一个字符串
            mstr.add(buildString(chs));
        }
         // 以str最后一个字符对应节点为root开始寻找以str为前缀字串的字符串
        while (chs.size() >= str.length()) { // 用来判断结束条件
            MyTrieNode n = chs.peek();
            for (int i = n.mIdx; i < 26; i++) {
                n.mIdx = i+1; // 代表为处理的下标
                if (n.mChildren[i] != null) {
                    chs.add(n.mChildren[i]);
                    if (n.mChildren[i].mIsEndOfStr) {
                        mstr.add(buildString(chs));
                    }
                    break; // 深度优先 递归处理子节点
                }
            }
            if (n.mIdx >= 26) {
                n.mIdx = 0; // pop之前reset idx
                chs.pop();
            }
        }

        return mstr;
    }

    private String buildString(Stack<MyTrieNode> stack) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < stack.size(); i++) {
            builder.append(stack.get(i).mdata);
        }
        return builder.toString();
    }
}
