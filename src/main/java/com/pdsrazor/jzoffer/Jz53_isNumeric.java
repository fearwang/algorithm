package com.pdsrazor.jzoffer;

public class Jz53_isNumeric extends Solution {
    @Override
    void solute(String[] args) {
        System.out.println("123.35e+6 : " + isNumeric("123.35e+6".toCharArray()));
        System.out.println("e1 : " + isNumeric("e1".toCharArray()));
        System.out.println("23. : " + isNumeric("23.".toCharArray()));
        System.out.println("+ : " + isNumeric("+".toCharArray()));
        System.out.println("+.1 : " + isNumeric("+.1".toCharArray()));
    }

    public boolean isNumeric(char[] str) {
        if (str == null || str.length <= 0) return false;

        int[] end = new int[1];
        boolean numeric = scanInteger(str, 0, end);

        if (end[0] < str.length && str[end[0]] == '.') {
            end[0]++;
            numeric = scanUnsignedInteger(str, end[0], end) || numeric;
        }
        if (end[0] < str.length && (str[end[0]] == 'e' || str[end[0]] == 'E')) {
            end[0]++;
            numeric = numeric && scanInteger(str, end[0], end);
        }
        return numeric && (end[0] >= str.length);
    }

    boolean scanInteger(char[] str, int start, int[] end) {
        int i = start;
        if (i < str.length && (str[i] == '+' || str[i] == '-')) {
            i++;
        }
        return scanUnsignedInteger(str, i, end);
    }

    boolean scanUnsignedInteger(char[] str, int start, int[] end) {
        int i = start;
        while (i < str.length && str[i] >= '0' && str[i] <= '9') {
            i++;
        }
        end[0] = i;
        return i > start;
    }
}