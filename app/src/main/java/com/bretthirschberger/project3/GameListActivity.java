package com.bretthirschberger.project3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class GameListActivity extends AppCompatActivity {

    private ListView mWorlds;
    private final static String WORLD_NUMBER = "world";
    private final static String USERNAME_KEY = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);
        mWorlds = findViewById(R.id.levels);
        ArrayList<String> names = new ArrayList<>();
        names.add("World 1");
        names.add("World 2");
        ArrayAdapter<String> worldNames = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, names);
        mWorlds.setAdapter(worldNames);
        mWorlds.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getApplicationContext(), GameActivity.class);
            intent.putExtra(WORLD_NUMBER, position);
            intent.putExtra(USERNAME_KEY, getIntent().getStringExtra(USERNAME_KEY));
            startActivity(intent);
        });
    }

    public void logOut(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
