package com.bretthirschberger.project3;

import android.media.Image;

public enum Direction {
    UP(null),DOWN(null),LEFT(null),RIGHT(null);
    private Image mImage;
    Direction(Image image){
        mImage=image;
    }
}
