package com.pdsrazor.algo.graph;

import java.util.*;


// 图涉及的基本概念：顶点，边，边的度
// 有向图的边中存在 入度和出度，带权图(好友的亲密指数)
//
// 图的存储：邻接矩阵(二维数组)，特点是基于数组获取顶点关系时高效，缺点是浪费空间(尤其是稀疏图)
// 邻接表，有点像链表解决冲突的hash表，确定顶点关系的时候需要遍历链表，效率不高(可以改进为二叉查找树)
// 如果要知道有向图中哪些顶点指向自己，则需要再维护一个逆邻接表(微博中被哪些用户关注了？)
//
// 数据量大的时候，可以利用hash数据分片的方法将邻接表存储在不同机器上。
// 或者利用外部存储(数据库)
//
// 图上的搜索算法，常见的暴力算法：DFS BFS
public class Graph { // 无向图
    private int v; // 顶点数量
    private LinkedList<Integer> adj[]; // 邻接表
    
    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for(int i = 0; i < v; i++) {
            adj[i] = new LinkedList<Integer>();
        }
    }

    public void addEdge(int s, int t) {
        adj[s].add(t); // 无向图add两次
        adj[t].add(s);
    }

    // 搜索一条从s到t的路径, 不一定 是唯一的路径
    public void bfs(int s, int t) {
        if (s == t)
            return;
        boolean visited[] = new boolean[v];
        visited[s] = true; // 起点
        Queue<Integer> queue = new LinkedList<Integer>(); // bfs需要
        queue.add(s); // 起点一定被访问
        int[] prev = new int[v]; // 用于打印路径
        for (int i = 0; i < v; i++) {
            prev[i] = -1;
        }

        while(queue.size() > 0) {
            int w = queue.poll(); // head
            for (int i = 0; i < adj[w].size(); i++) { // 访问每个可达的直接顶点
                int q = adj[w].get(i);
                if (visited[q] == false) {
                    prev[q] = w;
                    if (q == t) {
                        print(prev, s, t);
                        return;
                    }
                    visited[q] = true;
                    queue.add(q);
                }
            }
        }
    }

    public void print(int[] prev, int s, int t) {
        if (prev[t] != -1 && t != s) {
            print(prev, s, prev[t]);
        }
        System.out.print(t + " ");
    }

    public void dfs(int s, int t) {
        if (s == t) return;
        boolean found = false;
        int prev[] = new int[v];
        boolean visited[] = new boolean[v];
        for (int i = 0; i < v; i++) {
            prev[i] = -1;
            visited[i] = false;
        }
        visited[s] = true; // 起点
        Stack stack = new Stack<Integer>();
        stack.push(new Integer(s));
        boolean allVisited = false;
        while (!stack.empty() && !found) {
            allVisited = true; // 某个顶点的所有出边都访问过了。
            Integer integer = (Integer)stack.peek();
            int w = integer.intValue();
            int q = -1; // 当前访问的顶点
            for (int i = 0; i < adj[w].size(); i++) {
                q = adj[w].get(i); // 指向的顶点
                if (!visited[q]) {
                    allVisited = false;
                    break;
                } else {
                    //System.out.println(q + " visited");
                }
            }
            if (!allVisited) {
                visited[q] = true;
                stack.push(new Integer(q));
                prev[q] = w;
                if (q == t) {
                    found = true;
                    break;
                }
            } else {
                Integer integer2 = (Integer)stack.pop();
                int tmp = integer2.intValue();
                print(prev, s, tmp);
                System.out.println("");
            }
        }
        if (found) {
            System.out.println("result: ");
            print(prev, s, t);
        } else {
            System.out.println("do not find a path from " + s + " to " + t);
        }
    }
}