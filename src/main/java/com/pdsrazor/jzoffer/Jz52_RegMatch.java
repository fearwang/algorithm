package com.pdsrazor.jzoffer;

public class Jz52_RegMatch extends Solution {
    @Override
    void solute(String args[]) {
        System.out.println("aaa - a.a: " + match("aaa".toCharArray(), "a.a".toCharArray()));
        System.out.println("aaa - ab*ac*a: " + match("aaa".toCharArray(), "ab*ac*a".toCharArray()));
        System.out.println("aaa - ab*a: " + match("aaa".toCharArray(), "ab*a".toCharArray()));
        System.out.println(" - : " + match("".toCharArray(), "".toCharArray()));
        System.out.println("bbbba - .*a*a: " + match("bbbba".toCharArray(), ".*a*a".toCharArray()));
    }

    public boolean match(char[] str, char[] pattern) {
        if (str == null || pattern == null) return false;

        return matchCore(str, 0, pattern, 0);
    }

    boolean matchCore(char[] str, int i, char[] pattern, int j) {
        if (j >= pattern.length) {
            return i >= str.length;
        }
        // i >= length && j < length 不能直接返回，pattern剩余部分可能匹配空字符串
        if (j+1 < pattern.length && pattern[j+1] == '*') {
            if (i < str.length && (str[i] == pattern[j] || pattern[j] == '.')) {
                return matchCore(str, i+1, pattern, j) 
                    || matchCore(str, i+1, pattern, j+2)
                    || matchCore(str, i, pattern, j+2);
            } else {
                return matchCore(str, i, pattern, j+2);
            }
        }
        if (i < str.length && (str[i] == pattern[j] || pattern[j] == '.')) {
            return matchCore(str, i+1, pattern, j+1);
        }
        return false;
    }
}