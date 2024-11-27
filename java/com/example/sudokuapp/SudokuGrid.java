package com.example.sudokuapp;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.GridLayout;

public class SudokuGrid {

    private final EditText[][] cells = new EditText[9][9];

    public SudokuGrid(Context context, GridLayout gridLayout) {
        gridLayout.removeAllViews();

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                EditText cell = new EditText(context);
                cell.setTextSize(18);
                cell.setGravity(Gravity.CENTER);
                cell.setTextColor(Color.BLACK);
                cell.setBackgroundColor(Color.TRANSPARENT);
                cell.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);

                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = 0;
                params.height = 0;
                params.rowSpec = GridLayout.spec(row, 1, 1f);
                params.columnSpec = GridLayout.spec(col, 1, 1f);

                int borderWidth = 3;
                params.setMargins(
                        col % 3 == 0 ? borderWidth * 2 : borderWidth,
                        row % 3 == 0 ? borderWidth * 2 : borderWidth,
                        col == 8 ? borderWidth * 2 : borderWidth,
                        row == 8 ? borderWidth * 2 : borderWidth
                );

                cell.setLayoutParams(params);
                gridLayout.addView(cell);
                cells[row][col] = cell;
            }
        }
    }

    public void loadPuzzle(int[][] puzzle) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (puzzle[row][col] != 0) {
                    cells[row][col].setText(String.valueOf(puzzle[row][col]));
                    cells[row][col].setEnabled(false);
                } else {
                    cells[row][col].setText("");
                    cells[row][col].setEnabled(true);
                }
            }
        }
    }

    public void resetGrid() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                cells[row][col].setText("");
                cells[row][col].setEnabled(true);
            }
        }
    }

    public boolean checkSolution() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                String value = cells[row][col].getText().toString();
                if (value.isEmpty() || !value.matches("[1-9]")) {
                    return false;
                }
            }
        }
        return true;
    }
}

