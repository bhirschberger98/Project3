package com.bretthirschberger.project3;

import androidx.annotation.NonNull;

public class Score {
    private String user;
    private int score;

    public Score(String user, int score) {
        this.user = user;
        this.score = score;
    }

    public String getUser() {
        return user;
    }

    public int getScore() {
        return score;
    }

    @NonNull
    @Override
    public String toString() {
        return user + ": " + score + " Attempts\n";
    }
}
