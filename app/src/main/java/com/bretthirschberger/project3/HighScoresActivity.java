package com.bretthirschberger.project3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HighScoresActivity extends AppCompatActivity {

    private static final String WORLD_KEY = "world";
    private final static String USERNAME_KEY = "username";

    private TextView mScoreView;
    private TextView mTitle;
    private int mWorld;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("scores");
        mScoreView = findViewById(R.id.scores);
        mTitle = findViewById(R.id.title);

        mWorld = getIntent().getIntExtra(WORLD_KEY, 1);
        mTitle.setText("World " + (mWorld+1) + " Scores");
        mScoreView.setText(getScores());
    }

    public void goToWorldSelect(View view) {
        Log.i("User",getIntent().getStringExtra(USERNAME_KEY));
        startActivity(new Intent(getApplicationContext(), GameListActivity.class).putExtra(USERNAME_KEY, getIntent().getStringExtra(USERNAME_KEY)));
    }

    private String getScores() {
        String scores = "";
        try (Scanner reader = new Scanner(new File(getFilesDir().getAbsolutePath() + "/scores" + (mWorld + 1) + ".txt"))) {
            while (reader.hasNextLine()) {
                scores += reader.nextLine() + "\n";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return scores;
    }
}
