package com.bretthirschberger.project3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.net.Uri;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class GameActivity extends AppCompatActivity implements ActionHolder.ActionHolderListener {

    private ImageView mImageView;
    private ImageView mImageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mImageView = findViewById(R.id.imageView);
        mImageView2 = findViewById(R.id.imageView2);
        mImageView.setOnLongClickListener(v -> {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDragAndDrop(data, shadowBuilder, v, 0);
            return true;
        });
        mImageView2.setOnLongClickListener(v -> {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDragAndDrop(data, shadowBuilder, v, 0);
            return true;
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
