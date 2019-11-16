package com.bretthirschberger.project3;

import android.graphics.drawable.Drawable;

final public class Level {
    private Drawable mGameBoard;
    private Direction[] mCorrectAnswer;
    private int[] mAnimationCoards;
    private String mName;
    public Level(Drawable gameBoard,String name, int[] animationCoards,Direction... correctAnswer) {
        mGameBoard = gameBoard;
        mName = name;
        mCorrectAnswer = correctAnswer;
        mAnimationCoards=animationCoards;
    }

    public Drawable getGameBoard() {
        return mGameBoard;
    }

    public Direction[] getCorrectAnswer() {
        return mCorrectAnswer;
    }

    public String getName() {
        return mName;
    }

    public int[] getAnimationCoards() {
        return mAnimationCoards;
    }
}
