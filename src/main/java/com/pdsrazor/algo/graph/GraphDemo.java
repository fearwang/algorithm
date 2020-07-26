package com.pdsrazor.algo.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

import com.pdsrazor.algo.MyDemo;

import jdk.nashorn.api.tree.ForOfLoopTree;

public class GraphDemo extends MyDemo {
    @Override
    public int doCmd(String args[]) {
        MyGraph graph = new MyGraph(100);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 5);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(2, 8); // 验证bfs dfs区别
        graph.addEdge(3, 4);
        //graph.addEdge(4, 5);
        //graph.addEdge(4, 6);
        graph.addEdge(5, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);
        graph.addEdge(6, 8);
        if (args[0].equals("search")) {
            int start = Integer.parseInt(args[1]);
            int end = Integer.parseInt(args[2]);
            System.out.println("bfs: ");
            graph.bfs(start, end);
            System.out.println();
            System.out.println("dfs: ");
            graph.dfs(start, end);
            return 1;
        }
        if (args[0].equals("depth")) {
            int start = Integer.parseInt(args[1]);
            int depth = Integer.parseInt(args[2]);
            graph.depthBFS(start, depth);
            graph.depthDFS(start, depth);
        }
        return 0;
    }

    @Override
    public void usage() {
        System.out.println("GraphDemo:");
        System.out.println("  search");
        System.out.println("  depth <depth>"); // 抽象几度好友问题
    }
}

class MyGraphNode {
    int mData; // 可以是复合数据
    LinkedList<MyGraphNode> madjs;
    int mIdx; // 搜索时用

    public MyGraphNode(int data) {
        mData = data;
        mIdx = 0;
        madjs = new LinkedList<>();
    }
}

// 无向图?
class MyGraph {
    int mV = 0; // 顶点数量
    LinkedList<Integer> mAdjacents[]; 

    public MyGraph(int size) {
        mV = size;
        mAdjacents = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            mAdjacents[i] = new LinkedList<Integer>();
        }
    }

    public void addEdge(int start, int end) {
        LinkedList<Integer> list = mAdjacents[start];
        if (list.indexOf(end) == -1) {
            list.add(end);
        }
        LinkedList<Integer> list2 = mAdjacents[end];
        if (list2.indexOf(start) == -1) {
            list.add(start);
        }
    }

    private void printFootPrint(int[] fp, int start, int end) {
        if (end != start) {
            printFootPrint(fp, start, fp[end]);
        }
        System.out.print(end + " ");
    }

    public void bfs(int start, int end) {
        if (start == end) return;
        // visited数组是关键
        boolean visited[] = new boolean[mV];
        for (int i = 0; i < mV; i++) {
            visited[i] = false;
        }
        int footprint[] = new int[mV];
        for (int i = 0; i < mV; i++) {
            footprint[i] = -1;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int val : mAdjacents[node]) {
                if (!visited[val]) {
                    queue.add(val);
                    visited[val] = true;
                    footprint[val] = node;
                    if (val == end) {
                        printFootPrint(footprint, start, end);
                        return;
                    }
                }
            }
        }
    }

    public void dfs(int start, int end) {
        if (start == end) return;
        // visited数组是关键
        boolean visited[] = new boolean[mV];
        for (int i = 0; i < mV; i++) {
            visited[i] = false;
        }
        int footprint[] = new int[mV];
        for (int i = 0; i < mV; i++) {
            footprint[i] = -1;
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        visited[start] = true;
        while (!stack.isEmpty()) {
            int node = stack.peek();
            for (int val : mAdjacents[node]) {
                if (!visited[val]) {
                    stack.push(val);
                    visited[val] = true;
                    footprint[val] = node;
                    if (val == end) {
                        printFootPrint(footprint, start, end);
                        return;
                    }
                    break;
                }
            }
            if (stack.peek() != node) continue;
            // 没有continue，意味着node的所有子节点都访问过，pop
            stack.pop();
        }
    }

    public void depthBFS(int start, int depth) {
        LinkedList<Integer> queue = new LinkedList<>();
        boolean visited[] = new boolean[mV];
        for (int i = 0; i < mV; i++) {
            visited[i] = false;
        }
        int dist[] = new int[mV]; // 还有一种方法就是记录当前bfs到第几层了，在于如何判断当前层是否结束
        for (int i = 0; i < mV; i++) {
            dist[i] = 0;
        }
        // ArrayList<Integer> ret = new ArrayList<>();
        queue.add(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int val : mAdjacents[node]) {
                if (!visited[val]) {
                    visited[val] = true;
                    queue.add(val);
                    dist[val] = dist[node]+1;
                    // ret.add(val);
                }
            }
        }
        System.out.print(depth + " bfs dp of " + start + " is ");
        for (int i = 0; i < mV; i++) {
            if (dist[i] <= depth && dist[i] > 0) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    public void depthDFS(int start, int depth) {
        boolean visited[] = new boolean[mV];
        for (int i = 0; i < mV; i++) {
            visited[i] = false;
        }
        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> ret = new ArrayList<>();
        stack.push(start);
        visited[start] = true;
        // int dp = 0; 
        while (!stack.isEmpty()) {
            int node = stack.peek();
            for (int val : mAdjacents[node]) {
                if (!visited[val] && /*dp < depth*/ stack.size() < depth) {
                    visited[val] = true;
                    stack.push(val);
                    // dp++;
                    //if (dp <= depth)
                    ret.add(val);
                    break;
                }
            }
            if (stack.peek() == node) {
                stack.pop();
                // dp--;
            }
        }
        System.out.println(depth + " dfs dp of " + start + " is " + ret);
    }
}