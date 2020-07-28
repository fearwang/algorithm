package com.pdsrazor.algo.greedyalgo;

import java.util.*;

import com.pdsrazor.algo.MyDemo;
import com.pdsrazor.algo.sort.SortDemo;
import com.pdsrazor.algo.utils.RandomArrayGenerator;

// 贪心算法思想
// 哪类问题要想到贪心算法: 针对一组数据，定义限制值和期望值，从中选出几个数据，在满足限制值的前提下，期望值最大
// 解法：每次都选择当前情况下，对限制值贡献相同情况下，对期望值贡献最大的数据。
// 贪心算法不是总能给出最优解，比如有权图的最短路径问题，主要原因是前面的选择会影响后面的选择。
public class GreedyAlgoDemo extends MyDemo {
    public static int BOUND = 100;

    @Override
    public int doCmd(String args[]) {
        if (args[0].equals("huffman")) {
            PriorityQueue<WeightCharNode> list = new PriorityQueue<>(new WeightCharNodeComparator());
            list.add(new WeightCharNode('f', 20));
            list.add(new WeightCharNode('e', 30));
            list.add(new WeightCharNode('d', 60));
            list.add(new WeightCharNode('c', 90));
            list.add(new WeightCharNode('b', 350));
            list.add(new WeightCharNode('a', 450));
            HuffmanCode(list); 
            return 1;
        }
        // 分糖果
        // 我们有 m 个糖果和 n 个孩子。我们现在要把糖果分给这些孩子吃，但是糖果少，孩子多（m<n），所以糖果只能分配给一部分孩子。
        // 每个糖果的大小不等，这 m 个糖果的大小分别是 s1，s2，s3，……，sm。
        // 除此之外，每个孩子对糖果大小的需求也是不一样的，只有糖果的大小大于等于孩子的对糖果大小的需求的时候，孩子才得到满足。
        // 假设这 n 个孩子对糖果大小的需求分别是 g1，g2，g3，……，gn。我的问题是，如何分配糖果，能尽可能满足最多数量的孩子？
        if (args[0].equals("candybreaker")) {
            int nCandy = Integer.parseInt(args[1]);
            int nChildren = Integer.parseInt(args[2]);
            if (nCandy < nChildren) {
                int[] candies = RandomArrayGenerator.genIntArray(nCandy, BOUND);
                int[] childrens = RandomArrayGenerator.genIntArray(nChildren, BOUND); // 每个孩子对糖果大小的需求
                System.out.print(nCandy + " candies, all candy size: ");
                RandomArrayGenerator.printIntArray(candies);
                System.out.print(nChildren + " childrens, all needed size: ");
                RandomArrayGenerator.printIntArray(childrens);
                System.out.println("we can satisfy " + satisfyMostChilren(candies, childrens) + " children at most");
            }
            return 1;
        }
        if (args[0].equals("changeofcoin")) {
            int numofcointype = Integer.parseInt(args[1]);
            int target = Integer.parseInt(args[2]);
            int[] coins = RandomArrayGenerator.genIntArray(numofcointype, 10);
            System.out.print("coins: ");
            RandomArrayGenerator.printIntArray(coins);
            System.out.println("at least need " + minimumCoins(coins, target) + " coins to pay " + target);
            return 1;
        }
        if (args[0].equals("intervalCoverage")) {
            int size = Integer.parseInt(args[1]);
            int min = Integer.parseInt(args[2]);
            int max = Integer.parseInt(args[3]);
            
            return 1;
        }
        return 0;
    }

    @Override
    public void usage() {
        System.out.println("GreedyAlgoDemo:");
        System.out.println("  huffman");
        System.out.println("  candybreaker <nCandy> <nChildren>");
        System.out.println("  changeofcoin <numofcointype> <target>");
        System.out.println("  intervalCoverage <sectionSize> <min> <max>");
    }

    // sample1：哈夫曼编码，利用贪心算法给字符编码，使得出现概率最高的字符编码最短。
    // 哈夫曼编码的要求是某个字符的编码不会是另外一个字符的编码的前缀，方便解压缩
    // 假设文本的所有字符的出现概率都一致，则不需要设计不等长编码了。
    static class WeightCharNode {
        public char data;
        public int weight;
        public WeightCharNode left;
        public WeightCharNode right;
        public int visit;

        public WeightCharNode(char c, int w) {
            this(w);
            this.data = c;
        }

        public WeightCharNode(int w) {
            this.data = 0;
            this.weight = w;
            this.left = null;
            this.right = null;
            this.visit = 0;
        }
    }

    static class WeightCharNodeComparator implements Comparator<WeightCharNode> {
        public int compare(WeightCharNode n1, WeightCharNode n2) {
            return (n1.weight - n2.weight);
        }
    }

    private static void printCode(WeightCharNode node, ArrayList<Integer> code) {
        System.out.print("char: " + node.data + ", code: ");
        for (int i = 0; i < code.size(); i++) {
            System.out.print(code.get(i) + "");
        }
        System.out.println();
    }

    // 和专栏的答案有点不一样
    public static void HuffmanCode(PriorityQueue<WeightCharNode> weightTexts) {
        if (weightTexts.size() <= 2) {
            for (int i = 0; i < weightTexts.size(); i++) {
                WeightCharNode node = weightTexts.poll();
                System.out.println("char: " + node.data + ", code: " + ('0'+i));
            }
        }
        WeightCharNode tmp = null;
        while(!weightTexts.isEmpty()) {
            WeightCharNode node1 = weightTexts.poll();
            WeightCharNode node2 = weightTexts.poll();
            tmp = new WeightCharNode(node1.weight+node2.weight);
            if (!weightTexts.isEmpty()) weightTexts.add(tmp);
            tmp.left = node1;
            tmp.right = node2;
        }
        // tmp is root node
        Stack<WeightCharNode> stack = new Stack<>();
        ArrayList<Integer> code = new ArrayList<>();
        stack.push(tmp);
        while (!stack.isEmpty()) {
            WeightCharNode p = stack.peek();
            if (p.left != null && p.visit == 0) {
                p.visit = 1;
                stack.push(p.left);
                code.add(0);
                if (p.left.data != 0) printCode(p.left, code);
            } else if (p.right != null && p.visit == 1) {
                p.visit = 2;
                stack.push(p.right);
                code.add(1);
                if (p.right.data != 0) printCode(p.right, code);
            } else {
                stack.pop();
                if (code.size() > 0) // for root node, we do not add a code
                    code.remove(code.size()-1);
            }
        }
        return;
    }

    // 返回满足的孩子的数量
    public int satisfyMostChilren(int[] candies, int[] children) {
        SortDemo.quickSort(candies);
        SortDemo.quickSort(children);
        int unusedIdx = 0;
        int ret = 0;
        for (int i = 0; i < children.length; i++) { // 从需求最小的孩子开始，挑最小的糖果满足他
            int j = unusedIdx;
            for (;j < candies.length; j++) {
                if (candies[j] >= children[i]) {
                    ret++;
                    System.out.println("candy" + j + ", size=" + candies[j]
                         + ", satisfy child" + i + ", needed=" + children[i]);
                    break;
                }
            }
            unusedIdx = j+1; // 指向下一个可用的糖果
            if (j >= candies.length) break;
        }
        return ret;
    }

    // coins: 下标代表钱币的面值， 值代表钱币的数量
    // targe: 需要支付的钱
    // 返回需要支付的最少数量的钱币
    public int minimumCoins(int[] coins, int target) {
        int nextcoin = coins.length-1;
        int ret = 0;
        while (target > 0) {
            int i;
            for (i = nextcoin; i > 0; i--) {
                if (i <= target && coins[i] > 0) {
                    target -= i;
                    nextcoin = i;
                    coins[i]--; // 硬币减少一个
                    ret++;
                    System.out.println("pay " + i);
                }
            }
            if (coins[1] <= 0 && target > 0) {
                System.out.println("can not pay " + target);
                return -1;
            } 
        }
        return ret;
    }
}