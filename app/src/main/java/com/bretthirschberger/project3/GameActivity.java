package com.bretthirschberger.project3;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.graphics.Matrix;
import android.graphics.Path;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


public class GameActivity extends AppCompatActivity
        implements ActionHolder.ActionHolderListener, DirectionFragment.DirectionFragmentListener {

    private ActionHolder mActionHolder;
    private ImageView mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mActionHolder = (ActionHolder) getSupportFragmentManager().findFragmentById(R.id.holder_fragment);
        mPlayer = findViewById(R.id.player);

    }


    public void startMove(View view) {
        int delay=0;
        for (Direction direction : mActionHolder.getDirections()) {
            ObjectAnimator animation;
            switch (direction) {
                case UP:
                    animation = ObjectAnimator.ofFloat(mPlayer, "translationY", -200f * (delay + 1));
                    break;
                case DOWN:
                    animation = ObjectAnimator.ofFloat(mPlayer, "translationY", 200f * (delay + 1));
                    break;
                case LEFT:
                    animation = ObjectAnimator.ofFloat(mPlayer, "translationX", -400f * (delay + 1));
                    break;
                case RIGHT:
                    animation = ObjectAnimator.ofFloat(mPlayer, "translationX", 400f * (delay + 1));
                    break;
                default:
                    animation = null;
                    break;
            }
            if (animation != null){
                animation.setDuration(2000);
                animation.setStartDelay(2000 * delay);
                animation.start();
                delay++;
            }
            Log.i("Direction", direction.toString());
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
