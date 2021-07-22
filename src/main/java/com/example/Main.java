package com.example;

public class Main {
    public static void main(String[] args) {
        int rows = 4;
        int cols = 5;
        short[][] matrix = {
                {100, 100, 0, 50, 0},
                {100, 100, 0, 50, 0},
                {100, 0, 0, 0, 0},
                {0, 100, 0, 0, 50}
        };

        Solution solution = new Solution();
        solution.solve(matrix, rows, cols);
    }
}
