package com.example.sudokuapp;

import java.util.Random;

public class SudokuGenerator {

    private final int[][] grid = new int[9][9];
    private String difficulty;

    public SudokuGenerator(String difficulty) {
        this.difficulty = difficulty;
    }

    public int[][] generatePuzzle() {
        fillGrid();
        removeNumbers();
        return grid;
    }

    private void fillGrid() {
        solve(0, 0);
    }

    private boolean solve(int row, int col) {
        if (row == 9) {
            return true;
        }

        int nextRow = (col == 8) ? row + 1 : row;
        int nextCol = (col == 8) ? 0 : col + 1;

        for (int num = 1; num <= 9; num++) {
            if (isSafe(row, col, num)) {
                grid[row][col] = num;
                if (solve(nextRow, nextCol)) {
                    return true;
                }
                grid[row][col] = 0; // Backtrack
            }
        }
        return false;
    }

    private boolean isSafe(int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (grid[row][i] == num || grid[i][col] == num) {
                return false;
            }
        }

        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[startRow + i][startCol + j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    private void removeNumbers() {
        Random random = new Random();

        int cellsToRemove = getCellsToRemove();

        while (cellsToRemove > 0) {
            int row = random.nextInt(9);
            int col = random.nextInt(9);

            if (grid[row][col] != 0) {
                grid[row][col] = 0;
                cellsToRemove--;
            }
        }
    }

    private int getCellsToRemove() {
        int cellsToRemove = 0;

        switch (difficulty) {
            case "Easy":
                cellsToRemove = 35;
                break;
            case "Medium":
                cellsToRemove = 45;
                break;
            case "Hard":
                cellsToRemove = 55;
                break;
            default:
                cellsToRemove = 40;
                break;
        }
        return cellsToRemove;
    }
}

