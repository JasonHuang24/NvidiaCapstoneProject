package com.example.mindrevolution.nvidiacapstoneproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.DecimalFormat;

public class KeplerRefresh extends AppCompatActivity {
    int spinnerPos;
    MediaPlayer mpKeplerRefresh;
    int playing;
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kepler_refresh);
        setupHomeButton();
        setupBackButton();

        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        final int myValue = sharedPref.getInt("key1", 0);
        DecimalFormat currency = new DecimalFormat("$###,###.##");
        final EditText resultView = (EditText) findViewById(R.id.txtCashAmount);
        resultView.setText(currency.format(myValue));

        final Spinner group = (Spinner) findViewById(R.id.spinnerCardSelect);
        final Button button = (Button) findViewById(R.id.btnSelect);

        button1 = (Button) findViewById(R.id.btnSeriesAudio);
        button1.setOnClickListener(bKeplerRefresh);
        mpKeplerRefresh = new MediaPlayer();
        mpKeplerRefresh = MediaPlayer.create(this,R.raw.keplerrefresh);
        playing = 0;


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerPos = group.getSelectedItemPosition();
                if (spinnerPos == 0) {
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("key1", myValue);
                    editor.commit();
                    Intent intent = (new Intent(KeplerRefresh.this, GTX750.class));
                    startActivity(intent);
                } else if (spinnerPos == 1) {
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("key1", myValue);
                    editor.commit();
                    Intent intent = (new Intent(KeplerRefresh.this, GTX750TI.class));
                    startActivity(intent);
                } else if (spinnerPos == 2) {
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("key1", myValue);
                    editor.commit();
                    Intent intent = (new Intent(KeplerRefresh.this, GTX760.class));
                    startActivity(intent);
                }else if (spinnerPos == 3) {
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("key1", myValue);
                    editor.commit();
                    Intent intent = (new Intent(KeplerRefresh.this, GTX770.class));
                    startActivity(intent);
                }else if (spinnerPos == 4) {
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("key1", myValue);
                    editor.commit();
                    Intent intent = (new Intent(KeplerRefresh.this, GTX780.class));
                    startActivity(intent);
                }else if (spinnerPos == 5) {
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("key1", myValue);
                    editor.commit();
                    Intent intent = (new Intent(KeplerRefresh.this, GTXTitan.class));
                    startActivity(intent);
                }else if (spinnerPos == 6) {
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("key1", myValue);
                    editor.commit();
                    Intent intent = (new Intent(KeplerRefresh.this, GTX780TI.class));
                    startActivity(intent);
                }else if (spinnerPos == 7) {
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("key1", myValue);
                    editor.commit();
                    Intent intent = (new Intent(KeplerRefresh.this, GTXTitanBlack.class));
                    startActivity(intent);
                }else if (spinnerPos == 8) {
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("key1", myValue);
                    editor.commit();
                    Intent intent = (new Intent(KeplerRefresh.this, GTXTitanZ.class));
                    startActivity(intent);
                }

            }
        });
    }

    Button.OnClickListener bKeplerRefresh = new Button.OnClickListener() {
        @Override
        public void onClick(View v){
            switch(playing){
                case 0:
                    mpKeplerRefresh.start();
                    playing = 1;
                    button1.setText("Pause Audio");
                    break;
                case 1:
                    mpKeplerRefresh.pause();
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
                Intent intent = new Intent(KeplerRefresh.this, MainActivity.class);
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
                Intent intent = new Intent(KeplerRefresh.this, architecture.class);
                startActivity(intent);
            }
        });
    }
}
