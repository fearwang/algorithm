package com.pdsrazor.jzoffer;

import java.util.LinkedList;

public class Jz54_FirstNotRepeatCharInStream extends Solution {
    @Override
    void solute(String[] args) {
        Insert('g');
        System.out.print(FirstAppearingOnce());
        Insert('o');
        System.out.print(FirstAppearingOnce());
        Insert('o');
        System.out.print(FirstAppearingOnce());
        Insert('g');
        System.out.print(FirstAppearingOnce());
        Insert('l');
        System.out.print(FirstAppearingOnce());
        Insert('e');
        System.out.println(FirstAppearingOnce());


        Insert2('g');
        System.out.print(FirstAppearingOnce2());
        Insert2('o');
        System.out.print(FirstAppearingOnce2());
        Insert2('o');
        System.out.print(FirstAppearingOnce2());
        Insert2('g');
        System.out.print(FirstAppearingOnce2());
        Insert2('l');
        System.out.print(FirstAppearingOnce2());
        Insert2('e');
        System.out.println(FirstAppearingOnce2());
    }

    //Insert one char from stringstream
    int[] arr = new int[256];
    LinkedList<Character> list = new LinkedList<>();
    
    public void Insert(char ch)
    {
        arr[ch]++;
        if(arr[ch] == 1) {
            list.add(ch);
        } else {
            list.remove(new Character(ch));
        }
    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce()
    {
        return list.size() > 0 ? list.get(0) : '#';
    }


    //Insert one char from stringstream
    int[] noRepeatFirsIdxArr = new int[256];// >= 0: no repeat's first idx, -1: not exist, -2: char repeat

    public Jz54_FirstNotRepeatCharInStream() {
        for (int i = 0; i < 256; i++) {
            noRepeatFirsIdxArr[i] = -1; // char not exist
        }
    }
    
    int cur = 0;
    public void Insert2(char ch)
    {
        if(noRepeatFirsIdxArr[ch] == -1) {
            noRepeatFirsIdxArr[ch] = cur;
        } else if (arr[ch] >= 0) {
            noRepeatFirsIdxArr[ch] = -2; // repeat
        }
        cur++;
    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce2()
    {
        char ret = '#';
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 256; i++) {
            if (noRepeatFirsIdxArr[i] >= 0 && noRepeatFirsIdxArr[i] < min) {
                min = noRepeatFirsIdxArr[i];
                ret = (char)i;
            }
        }
        return ret;
    }
}