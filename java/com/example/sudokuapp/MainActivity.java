package com.example.sudokuapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SudokuGrid sudokuGrid;
    private SudokuGenerator generator;
    private String difficulty;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        difficulty = intent.getStringExtra("difficulty");

        Log.d(TAG, "Difficulty selected: " + difficulty);

        GridLayout gridLayout = findViewById(R.id.sudokuGrid);
        Button btnCheck = findViewById(R.id.btnCheck);
        Button btnReset = findViewById(R.id.btnReset);
        Button btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> {
            Log.d(TAG, "Navigating back to MainMenuActivity");
            Intent backIntent = new Intent(MainActivity.this, MainMenuActivity.class);
            startActivity(backIntent);
        });

        sudokuGrid = new SudokuGrid(this, gridLayout);

        generator = new SudokuGenerator(difficulty);

        loadNewPuzzle();

        btnCheck.setOnClickListener(v -> {
            Log.d(TAG, "Checking solution...");
            if (sudokuGrid.checkSolution()) {
                Log.d(TAG, "Solution is correct");
                Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
            } else {
                Log.d(TAG, "Solution is incorrect");
                Toast.makeText(MainActivity.this, "Try again.", Toast.LENGTH_SHORT).show();
            }
        });

        btnReset.setOnClickListener(v -> {
            Log.d(TAG, "Resetting puzzle...");
            loadNewPuzzle();
        });
    }

    private void loadNewPuzzle() {
        Log.d(TAG, "Generating new puzzle...");
        int[][] newPuzzle = generator.generatePuzzle();
        sudokuGrid.loadPuzzle(newPuzzle);
        Log.d(TAG, "New puzzle generated and loaded into grid");
    }
}


