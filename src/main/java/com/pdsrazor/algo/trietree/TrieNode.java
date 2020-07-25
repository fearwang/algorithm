package com.pdsrazor.algo.trietree;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.lang.StringBuilder;
import java.util.*;

public class TrieNode {
    public char data;
    public int length = -1; // AC自动机使用,当endingChar=true时记录字串串的长度
    public TrieNode fail = null; // AC自动机使用
    public TrieNode[] children; // 只支持a-z
    public boolean endingChar;
    public int childIndex;

    TrieNode(char c) {
        children = new TrieNode[26]; // 只支持a-z
        for (int i = 0; i < 26; i++) {
            children[i] = null;
        }
        length = -1;
        fail = null;
        this.data = c;
        endingChar = false;
    }
}