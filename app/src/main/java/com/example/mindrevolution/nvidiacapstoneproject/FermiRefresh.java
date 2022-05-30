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

public class FermiRefresh extends AppCompatActivity {
    int spinnerPos;
    MediaPlayer mpFermiRefresh;
    int playing;
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fermi_refresh);
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
        button1.setOnClickListener(bFermiRefresh);
        mpFermiRefresh = new MediaPlayer();
        mpFermiRefresh = MediaPlayer.create(this,R.raw.fermi);
        playing = 0;


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerPos = group.getSelectedItemPosition();
                if (spinnerPos == 0) {
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("key1", myValue);
                    editor.commit();
                    Intent intent = (new Intent(FermiRefresh.this, GTX550TI.class));
                    startActivity(intent);
                } else if (spinnerPos == 1) {
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("key1", myValue);
                    editor.commit();
                    Intent intent = (new Intent(FermiRefresh.this, GTX560.class));
                    startActivity(intent);
                } else if (spinnerPos == 2) {
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("key1", myValue);
                    editor.commit();
                    Intent intent = (new Intent(FermiRefresh.this, GTX560TI.class));
                    startActivity(intent);
                } else if (spinnerPos == 3) {
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("key1", myValue);
                    editor.commit();
                    Intent intent = (new Intent(FermiRefresh.this, GTX570.class));
                    startActivity(intent);
                } else if (spinnerPos == 4) {
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("key1", myValue);
                    editor.commit();
                    Intent intent = (new Intent(FermiRefresh.this, GTX580.class));
                    startActivity(intent);
                } else if (spinnerPos == 5) {
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("key1", myValue);
                    editor.commit();
                    Intent intent = (new Intent(FermiRefresh.this, GTX590.class));
                    startActivity(intent);
                }

            }
        });
    }

    Button.OnClickListener bFermiRefresh = new Button.OnClickListener() {
        @Override
        public void onClick(View v){
            switch(playing){
                case 0:
                    mpFermiRefresh.start();
                    playing = 1;
                    button1.setText("Pause Audio");
                    break;
                case 1:
                    mpFermiRefresh.pause();
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
                Intent intent = new Intent(FermiRefresh.this, MainActivity.class);
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
                Intent intent = new Intent(FermiRefresh.this, architecture.class);
                startActivity(intent);
            }
        });
    }
}
