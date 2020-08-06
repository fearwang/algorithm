package com.pdsrazor.jzoffer;

public class Jz65_hasPath extends Solution {
    @Override
    void solute(String args[]) {
        String matrix = "ABCESFCSADEE";
        int rows = 3;
        int cols = 4;
        String str = "ABCCED";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix.charAt(i*cols+j) + " ");
            }
            System.out.println();
        }
        System.out.println("has path, " + str + ": " + hasPath(matrix.toCharArray(), rows, cols, str.toCharArray()));
        
        String ma2 = "ABCEHJIGSFCSLOPQADEEMNOEADIDEJFMVCEIFGGS";
        String str2 = "SGGFIECVAASABCEHJIGQEM";
        rows = 5;
        cols = 8;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(ma2.charAt(i*cols+j) + " ");
            }
            System.out.println();
        }
        System.out.println("has path, " + str2 + ": " + hasPath(ma2.toCharArray(), rows, cols, str2.toCharArray()));
    }

    public boolean hasPath(char[] matrix, int rows, int cols, char[] str)
    {
        boolean visited[] = new boolean[rows*cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = 0; k < rows*cols; k++) {
                    visited[k] = false;
                }
                if (dfs(matrix, rows, cols, i, j, 0, str, visited)) return true;
            }
        }
        return false;
    }

    boolean dfs(char[] matrix, int rows, int cols, int i, int j, int idx,
                char[] str, boolean[] visited) {
        if (idx >= str.length) return true;
        
        if (i >= 0 && i < rows && j >= 0 && j < cols
                && visited[i*cols+j] == false
                && matrix[i*cols+j] == str[idx]) {
            visited[i*cols+j] = true;
            if (dfs(matrix, rows, cols, i+1, j, idx+1, str, visited)) return true;
            if (dfs(matrix, rows, cols, i-1, j, idx+1, str, visited)) return true;
            if (dfs(matrix, rows, cols, i, j+1, idx+1, str, visited)) return true;
            if (dfs(matrix, rows, cols, i, j-1, idx+1, str, visited)) return true;
            visited[i*cols+j] = false;
        }
        
        return false;
    }
}