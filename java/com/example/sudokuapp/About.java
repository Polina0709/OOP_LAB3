package com.example.sudokuapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Button btnBackM = findViewById(R.id.btnBackM);

        btnBackM.setOnClickListener(v -> {
            Intent intent = new Intent(About.this, MainMenuActivity.class);
            startActivity(intent);
        });
    }
}