package com.pdsrazor.jzoffer;

public class Jz53_isNumeric extends Solution {
    @Override
    void solute(String[] args) {
        System.out.println("123.45e+6 : " + isNumeric("123.45e+6".toCharArray()));
        System.out.println("e1 : " + isNumeric("e1".toCharArray()));
    }

    public boolean isNumeric(char[] str) {
        if (str == null || str.length <= 0) return false;
        
        int i = 0;
        int start = 0;
        
        if (i < str.length && (str[i] == '+' || str[i] == '-')) {
            i++;
        }
        
        if (i < str.length && str[i] != '.') {
            start = i;
            while (i < str.length && str[i] >= '0' && str[i] <= '9') {
                i++;
            }
            if (i <= start) return false;
        }
        
        if (i < str.length && str[i] == '.') {
            i++;
            start = i;
            while (i < str.length && str[i] >= '0' && str[i] <= '9') {
                i++;
            }
            if (i <= start) return false;
        }
        
        if (i < str.length && (str[i] == 'e' || str[i] == 'E')) {
            i++;
            if (i < str.length && (str[i] == '+' || str[i] == '-')) {
                i++;
            }
            
            start = i;
            while (i < str.length && str[i] >= '0' && str[i] <= '9') {
                i++;
            }
            if (i <= start) return false;
        }
        
        return (i >= str.length);
    }
}