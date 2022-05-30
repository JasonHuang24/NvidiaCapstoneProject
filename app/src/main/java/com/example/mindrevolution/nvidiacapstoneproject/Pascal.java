package com.example.mindrevolution.nvidiacapstoneproject;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Pascal extends AppCompatActivity {
    MediaPlayer mpPascal;
    Button button1;
    int playing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pascal);

        setupHomeButton();
        setupBackButton();

        button1 = (Button) findViewById(R.id.btnAudioSeries);
        button1.setOnClickListener(bPascal);
        mpPascal = new MediaPlayer();
        mpPascal = MediaPlayer.create(this,R.raw.pascal);
        playing = 0;
    }

    Button.OnClickListener bPascal = new Button.OnClickListener() {
        @Override
        public void onClick(View v){
            switch(playing){
                case 0:
                    mpPascal.start();
                    playing = 1;
                    button1.setText("Pause Audio");
                    break;
                case 1:
                    mpPascal.pause();
                    playing = 0;
                    button1.setText("Resume Audio");
                    break;
            }
        }
    };
    private void setupHomeButton()
    {
        Button button = (Button) findViewById(R.id.btnHome);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pascal.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupBackButton()
    {
        Button button = (Button) findViewById(R.id.btnBack);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pascal.this, architecture.class);
                startActivity(intent);
            }
        });
    }
}
