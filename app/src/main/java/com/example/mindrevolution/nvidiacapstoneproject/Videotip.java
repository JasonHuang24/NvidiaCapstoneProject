package com.example.mindrevolution.nvidiacapstoneproject;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;
import android.util.DisplayMetrics;

public class Videotip extends AppCompatActivity {
    private MediaController media_control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videotip);
        setupHomeButton();
        VideoView video_view = (VideoView) findViewById(R.id.videoView);
        Uri uri = Uri.parse("android.resource://com.example.mindrevolution.nvidiacapstoneproject/raw/gpushop");

        media_control = new MediaController(this);
        video_view.setMediaController(media_control);
        video_view.setZOrderOnTop(true);
        video_view.setVideoURI(uri);
        video_view.start();
    }

    private void setupHomeButton()
    {
        Button button = (Button) findViewById(R.id.btnHome);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Videotip.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
