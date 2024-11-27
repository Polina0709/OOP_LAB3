package com.example.sudokuapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button btnStartNewGame = findViewById(R.id.btnStartNewGame);
        Button btnExit = findViewById(R.id.btnExit);
        Button btnAbout = findViewById(R.id.btnAbout);
        Button btnChangeLanguage = findViewById(R.id.btnChangeLanguage);

        btnStartNewGame.setOnClickListener(v -> {
            showDifficultyDialog();
        });

        btnAbout.setOnClickListener(v -> {
            Intent intent = new Intent(MainMenuActivity.this, About.class);
            startActivity(intent);
        });

        btnExit.setOnClickListener(v -> {
            moveTaskToBack(true);
        });

        btnChangeLanguage.setOnClickListener(v -> {
            String currentLanguage = Locale.getDefault().getLanguage();
            if (currentLanguage.equals("uk")) {
                setLanguage("en");
            } else if(currentLanguage.equals("en")){
                setLanguage("uk");
            }
            recreate();
        });

    }
    private void setLanguage(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }

    private void showDifficultyDialog() {
        String[] difficultyLevels = {"Easy", "Medium", "Hard"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Difficulty")
                .setItems(difficultyLevels, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selectedDifficulty = difficultyLevels[which];
                        Toast.makeText(MainMenuActivity.this, "Selected: " + selectedDifficulty, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(MainMenuActivity.this, MainActivity.class);
                        intent.putExtra("difficulty", selectedDifficulty);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }
}
