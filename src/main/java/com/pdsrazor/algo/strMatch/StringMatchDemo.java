package com.pdsrazor.algo.strMatch;

import com.pdsrazor.algo.MyDemo;

// 字符串匹配算法:
// BF暴力算法，虽然复杂度O(n*m)很高，但是工程中很常用，尤其是主串和模式串都不太长时，而且·很简单
//
// RK算法(Rabin-Karp)：对主串中的所有符合长度的字串求hash，然后和模式串比较。此时hash算法的设计十分重要。
// 如果某些操作耗时，则可以尝试事先计算并缓存。
//
// 思考：二维字符矩阵的匹配
//
// Boyer-Moore算法: 匹配的时候看做子串不停的在主串中滑动，bf算法就是每次滑动一个位置。BM算法就是找到
// 一次多滑动几个位置的方法，提高效率。
// BM算法中的:bad character rule , good suffix shift
// 核心：从尾部开始倒着匹配， 前缀字串(abc的a和ab)，后缀字串(abc的bc和c)
//
//
public class StringMatchDemo extends MyDemo {
    @Override
    public int doCmd(String args[]) {
        if (args.length != 2)
            return 0;
        String sub = args[1];
        String par = args[0];
        char[] parA = new char[par.length()];
        par.getChars(0, par.length(), parA, 0);
        char[] subA = new char[sub.length()];
        sub.getChars(0, sub.length(), subA, 0);
        System.out.println("BF: mainstr: " + args[0] + ", sub: " + args[1]
                + ", found: " + BruteForce(parA, subA));
        System.out.println("RK: mainstr: " + args[0] + ", sub: " + args[1]
                + ", found: " + RabinKarp(parA, subA));
        
        //BoyerMoore(args[1], args[2]);
        //KMP(args[1], args[2]);
        return 1;
    }

    @Override
    public void usage() {
        System.out.println("StringMatchDemo <main str> <sub str>");
    }

    // 返回字串在主串中第一次出现的下标
    public static int BruteForce(char[] par, char[] sub) {
        if (sub.length > par.length) return -1;
        for (int i = 0; i < (par.length-sub.length+1); i++) {
            boolean found = true;
            for (int j = 0; j < sub.length; j++) {
                if (par[i+j] != sub[j]) {
                    found = false;
                    break;
                }
            }
            if (found == true) {
                return i;
            }
        }
        return -1;
    }

    // 计算主串中 所有长度为字串长度的字串 的hash值， 加速BF的匹配速度
    // hash设计的关键，否则和BF没啥去别
    // 支持0-9，a-z,A-Z: 62进制
    // 子串很长，导致hash值太大怎么办? 允许冲突？ 如何减少hash值大小并减少冲突？
    // 允许存在冲突的时候，遇到hash一致时还要再对比下字符串才能最终确定是否找到子串
    public static int RADIX = 62;
    public static int changeTo62(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'a' && c <= 'z') {
            return c - 'a' + 10;
        }
        if (c >= 'A' && c <= 'Z') {
            return c - 'A' + 36;
        }
        return -1;
    }

    public static long getRadixPower(int data, int radix, int N) {
        long ret = data;
        for (int i = 0; i < N; i++) {
            ret = ret * radix;
        }
        return ret;
    }

    public static int RabinKarp(char[] par, char[] sub) {
        if (sub.length > par.length) return -1;
        long subHash = 0;
        for (int i = 0; i < sub.length; i++) {
            subHash = subHash*RADIX + changeTo62(sub[i]);
        }
        //System.out.println("subhash" + "=" + subHash);
        long[] hash = new long[par.length-sub.length+1];
        // 第一个字串  
        for (int i = 0; i < sub.length; i++) {
            hash[0] = hash[0]*RADIX + changeTo62(par[i]);
        }

        for (int i = 1; i < (par.length-sub.length+1); i++) {
            hash[i] = (hash[i-1] - getRadixPower(changeTo62(par[i-1]), RADIX, sub.length-1))*RADIX
                    + changeTo62(par[i+sub.length-1]);
        }

        for (int i = 0; i < par.length - sub.length+1; i++) {
            //System.out.println("hash" + i + "=" + hash[i]);
            if (hash[i] == subHash) return i;
        }

        return -1;
    }
}