package com.pdsrazor.jzoffer;

public class Jz1_find extends Solution {
    @Override
    void solute(String args[]) {
        int[][] array = {{1,2,3,4}, {5,6,7,8},{10,11,12,13}};
        System.out.println("find 4: " + Find(4, array));
    }
    public boolean Find(int target, int [][] array) {
        int row = array.length;
        int col = array[0] != null ? array[0].length : 0;
        if (row == 0 || col == 0) return false;
        
        int i = 0;
        int j = col-1;
        while (i < row && i >= 0 && j < col && j >= 0) {
            if (array[i][j] == target) {
                return true;
            } else if (array[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }
}