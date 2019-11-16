package com.bretthirschberger.project3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class GameActivity extends AppCompatActivity
        implements ActionHolder.ActionHolderListener, DirectionFragment.DirectionFragmentListener, GameBoardFragment.OnFragmentInteractionListener {

    private final static String WORLD_NUMBER = "world";
    private final static String USERNAME_KEY = "username";

    private ActionHolder mActionHolder;
    private GameBoardFragment mGameBoardFragment;
    private FragmentManager mFragmentManager;
    private TextView mLevelLabel;
    private TextView mAttemptsLabel;
    private Level mLevels[][];
    private int mLevel;
    private int mWorld;
    private int mAttempts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        mLevelLabel = findViewById(R.id.level_label);
        mAttemptsLabel = findViewById(R.id.attempts);
        mWorld = getIntent().getExtras().getInt(WORLD_NUMBER);
        mGameBoardFragment = (GameBoardFragment) getSupportFragmentManager().findFragmentById(R.id.game_board);
        mLevels = new Level[][]{
                {new Level(getResources().getDrawable(R.drawable.level_1), "Level 1-1",
                        new int[]{0, 512, 0, 1000},
                        Direction.RIGHT, Direction.DOWN, Direction.RIGHT),
                        new Level(getResources().getDrawable(R.drawable.level_2), "Level 1-2",
                                new int[]{-512, 512, 0, 750},
                                Direction.RIGHT, Direction.DOWN, Direction.RIGHT, Direction.UP),
                        new Level(getResources().getDrawable(R.drawable.level_3), "Level 1-3",
                                new int[]{0, 512, 0, 750},
                                Direction.RIGHT, Direction.DOWN, Direction.RIGHT, Direction.UP, Direction.RIGHT)}
                ,
                {new Level(getResources().getDrawable(R.drawable.level_4), "Level 2-1",
                        new int[]{-500, 0, 0, 700},
                        Direction.RIGHT, Direction.UP),
                        new Level(getResources().getDrawable(R.drawable.level_5), "Level 2-2",
                                new int[]{512, 480, 0, 750},
                                Direction.RIGHT, Direction.DOWN, Direction.RIGHT, Direction.DOWN),
                        new Level(getResources().getDrawable(R.drawable.level_6), "Level 2-3",
                                new int[]{0, 400, 0, 1000},
                                Direction.DOWN, Direction.RIGHT, Direction.UP, Direction.RIGHT)}};
        mActionHolder = ActionHolder.newInstance(mLevels[mWorld][mLevel].getCorrectAnswer().length);
        fragmentTransaction.replace(R.id.holder_layout, mActionHolder).commit();
        Log.i("Level", mWorld + "-" + mLevel);
        mGameBoardFragment.setGameBoard(mLevels[mWorld][mLevel].getGameBoard());
        mLevelLabel.setText(mLevels[mWorld][mLevel].getName());
        mAttemptsLabel.setText(mAttempts + "");
    }

    @Override
    public void goToNextLevel() {
        if (++mLevel < mLevels[mWorld].length) {
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            mActionHolder = ActionHolder.newInstance(mLevels[mWorld][mLevel].getCorrectAnswer().length);
            fragmentTransaction.replace(R.id.holder_layout, mActionHolder).commit();
            Log.i("Level", mWorld + "-" + mLevel);
            mGameBoardFragment.setGameBoard(mLevels[mWorld][mLevel].getGameBoard());
            mLevelLabel.setText(mLevels[mWorld][mLevel].getName());
            mGameBoardFragment.reset(0);
        } else {
            writeHighScore();
            Intent intent = new Intent(this, HighScoresActivity.class);
            intent.putExtra(WORLD_NUMBER, mWorld);
            intent.putExtra(USERNAME_KEY, getIntent().getStringExtra(USERNAME_KEY));
            startActivity(intent);
        }
    }

    public void writeHighScore() {
        try (FileWriter writer = new FileWriter(new File(getFilesDir().getAbsolutePath() + "/scores" + (mWorld + 1) + ".txt"), true)) {
            writer.write(new Score(getIntent().getStringExtra(USERNAME_KEY), mAttempts).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reset(View view) {
        mGameBoardFragment.reset(0);
    }

    public void startMove(View view) {
        mGameBoardFragment.movePlayer(mActionHolder.getDirections(), mLevels[mWorld][mLevel].getCorrectAnswer(), mLevels[mWorld][mLevel].getAnimationCoards());
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void increaseAttempts() {
        mAttempts++;
        mAttemptsLabel.setText(mAttempts + "");
    }

    public void exitGame(View view) {
        AlertDialog confirmDialog = new AlertDialog.Builder(this).setMessage(getString(R.string.confirm_msg)).
                setCancelable(false).
                setPositiveButton(getString(R.string.yes), (dialog, which) -> startActivity(new Intent(getApplicationContext(), GameListActivity.class))).
                setNegativeButton(getString(R.string.no), (dialog, which) -> dialog.cancel()).
                setTitle(getString(R.string.confirm_title)).create();
        confirmDialog.show();
    }

}
