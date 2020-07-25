package com.pdsrazor.algo.trietree;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.lang.StringBuilder;
import java.util.*;

// 搜索引擎关键字匹配
// 利用字符串之间的公共前缀，将重复的前缀合并在一起
public class TrieTree {
    public static int doCmd(String args[]) {
        //if (args.length != 3)
        //    return 0;
        if (args[0].equals("trietree")) {
            TrieTree tree = new TrieTree();
            tree.insert("how");
            tree.insert("hi");
            tree.insert("her");
            tree.insert("hello");
            tree.insert("so");
            tree.insert("see");
            if (tree.find(args[1])) {
                System.out.println("find " + args[1]);
            } else {
                System.out.println("do not find " + args[1]);
            }
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
            }
            return 1;
        }
        return 0;
    }

    private TrieNode root = new TrieNode('/');
    public TrieNode getRoot() {
        return root;
    }
    public void insert(String text) {
        char[] t = new char[text.length()];
        text.getChars(0, text.length(), t, 0);
        insert(t);
    }

    // 将字符串集合构造成trie tree, 就是依次插入
    public void insert(char[] text) {
        TrieNode p = root;
        for (int i = 0; i < text.length; i++) { // 遍历插入字符串的所有字符
            if (p.children[text[i]-'a'] == null) {
                TrieNode node = new TrieNode(text[i]);
                p.children[text[i]-'a'] = node;
            }
            p = p.children[text[i]-'a']; // 更新指向当前字符对应的节点
        }
        p.length = text.length; //AC算法用到
        p.endingChar = true; // 最后一个字符，标记为endingChar
    }

    public boolean find(String text) {
        char[] t = new char[text.length()];
        text.getChars(0, text.length(), t, 0);
        return find(t);
    }

    // 在trie tree中查找
    public boolean find(char[] pattern) {
        TrieNode p = root;
        for (int i = 0; i < pattern.length; i++) {
            if (p.children[pattern[i]-'a'] != null) {
                p = p.children[pattern[i]-'a'];
            } else {
                return false;
            }
        }
        if (p.endingChar)
            return true;// 不能完全匹配 只是前缀
        return false;
    }

    // 类似搜索引擎的根据已经输入的字符，返回可能匹配的字符
    public ArrayList<String> match(String text) {
        if (text == null || text.length() == 0)
            return null;

        char[] t = new char[text.length()];
        text.getChars(0, text.length(), t, 0);
        String prefix = new String(t, 0, text.length()-1);
        Stack stack = new Stack<TrieNode>();
        ArrayList<String> strlist = new ArrayList<String>();

        // try to find node for last char
        TrieNode p = root;
        for (int i = 0; i < t.length; i++) {
            if (p.children[t[i]-'a'] != null) {
                p = p.children[t[i]-'a'];
            } else {
                return null;
            }
        }
        // 此时p指向了text最后一个字符对应的node
        if (p.endingChar) {
            String s = new String(text);
            strlist.add(s);
        }

        stack.push(p);
        p.childIndex = 0;
        while(!stack.empty()) {
            TrieNode n = (TrieNode)stack.peek();
            while (n.childIndex < 26) {
                if (n.children[n.childIndex] != null) {
                    stack.push(n.children[n.childIndex]);
                    n.children[n.childIndex].childIndex = 0; // 新入stack的node要reset child index
                    if (n.children[n.childIndex].endingChar) {
                        // add to result
                        strlist.add(getResultFromStack(stack, prefix));
                    }
                    n.childIndex++;
                    break;
                }
                n.childIndex++;
            }
            if (n.childIndex == 26) {
                // pop
                stack.pop();
            }
        }
        return strlist;
    }

    private String getResultFromStack(Stack stack, String prefix) {
        StringBuilder builder = new StringBuilder(prefix);
        Stack tmpstack = new Stack<TrieNode>();
        while (!stack.empty()) {
            TrieNode p = (TrieNode)stack.pop();
            //System.out.println("stack: " + p.data);
            tmpstack.push(p);
        }
        while (!tmpstack.empty()) {
            TrieNode p = (TrieNode)tmpstack.pop();
            stack.push(p);
            builder.append(p.data);
        }
        return builder.toString();
    }

    // 使用队列的数据结构匹配
    // 上面使用stack匹配，先找到的总是最长的字符串
    // 有问题，queue在这个问题上不适用，因为入队列的并不都是合法字符串中的字符
    public ArrayList<String> match2(String text) {
        if (text == null || text.length() == 0)
            return null;

        char[] t = new char[text.length()];
        text.getChars(0, text.length(), t, 0);
        String prefix = new String(t, 0, text.length()-1);
        Queue<TrieNode> queue = new LinkedList<TrieNode>(); // bfs需要
        ArrayList<String> strlist = new ArrayList<String>();

        // try to find node for last char
        TrieNode p = root;
        for (int i = 0; i < t.length; i++) {
            if (p.children[t[i]-'a'] != null) {
                p = p.children[t[i]-'a'];
            } else {
                return null;
            }
        }
        // 此时p指向了text最后一个字符对应的node
        if (p.endingChar) {
            String s = new String(text);
            strlist.add(s);
        }

        queue.add(p);
        p.childIndex = 0;
        TrieNode n = null;
        while((n = (TrieNode)queue.peek()) != null) {
            while (n.childIndex < 26) {
                if (n.children[n.childIndex] != null) {
                    queue.add(n.children[n.childIndex]);
                    n.children[n.childIndex].childIndex = 0; // 新入queue的node要reset child index
                    if (n.children[n.childIndex].endingChar) {
                        // add to result
                        strlist.add(getResultFromQueue(queue, prefix));
                    }
                    n.childIndex++;
                    break;
                }
                n.childIndex++;
            }
            if (n.childIndex == 26) {
                queue.remove();
            }
        }
        return strlist;
    }

    private String getResultFromQueue(Queue queue, String prefix) {
        StringBuilder builder = new StringBuilder(prefix);
        Queue tmpqueue = new LinkedList<TrieNode>();
        TrieNode p = null;
        while ((p = (TrieNode)queue.poll()) != null) {
            //TrieNode p = (TrieNode)queue.poll();
            //System.out.println("stack: " + p.data);
            tmpqueue.add(p);
        }
        while ((p = (TrieNode)tmpqueue.poll()) != null) {
            //TrieNode p = (TrieNode)tmpqueue.poll();
            queue.add(p);
            builder.append(p.data);
        }
        return builder.toString();
    }

}