package com.pdsrazor.jzoffer;

import java.util.LinkedList;

public class Jz66_movingCount extends Solution {
    @Override
    void solute(String args[]) {
        System.out.print("move cnt: " + movingCount(5, 10, 10));
    }

    public int movingCount(int threshold, int rows, int cols) {
        int cnt = 0;
        boolean visited[][] = new boolean[rows][cols];
        LinkedList<Integer> queue = new LinkedList<>();
        if (accept(0, 0, threshold)) {
            queue.add(0);
            queue.add(0);
            visited[0][0] = true;
            cnt++;
        }
        while (queue.size() > 0) {
            int r = queue.poll();
            int c = queue.poll();
            if (r+1 < rows && visited[r+1][c] == false && accept(r+1, c, threshold)) {
                visited[r+1][c] = true;
                queue.add(r+1);
                queue.add(c);
                cnt++;
            }
            if (r-1 >= 0 && visited[r-1][c] == false && accept(r-1, c, threshold)) {
                visited[r-1][c] = true;
                queue.add(r-1);
                queue.add(c);
                cnt++;
            }
            if (c+1 < cols && visited[r][c+1] == false && accept(r, c+1, threshold)) {
                visited[r][c+1] = true;
                queue.add(r);
                queue.add(c+1);
                cnt++;
            }
            if (c-1 >= 0 && visited[r][c-1] == false && accept(r, c-1, threshold)) {
                visited[r][c-1] = true;
                queue.add(r);
                queue.add(c-1);
                cnt++;
            }
        }
        return cnt;
    }

    boolean accept(int row, int col, int threshold) {
        int sum = 0;
        while(row > 0) {
            sum += (row % 10);
            row = row / 10;
        }
        while (col > 0) {
            sum += (col % 10);
            col = col / 10;
        }
        if (sum <= threshold) return true;
        return false;
    }
}