package com.example.mindrevolution.nvidiacapstoneproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.DecimalFormat;

public class architecture extends AppCompatActivity {

    int spinnerPos;
    int spinnerPos2;
    Spinner group, group2;
    MediaPlayer mpHistory;
    Button button1;
    int playing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_architecture);

        setupHomeButton();

        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        final int myValue = sharedPref.getInt("key1", 0);
        DecimalFormat currency = new DecimalFormat("$###,###.##");
        final EditText resultView = (EditText) findViewById(R.id.txtCashAmount);
        resultView.setText(currency.format(myValue));

        group = (Spinner) findViewById(R.id.spinnerLatest);
        group2 = (Spinner) findViewById(R.id.spinnerLegacy);

        final Button button = (Button) findViewById(R.id.btnContinue);

        button1 = (Button) findViewById(R.id.btnAboutArchitecture);
        button1.setOnClickListener(bHistory);
        mpHistory = new MediaPlayer();
        mpHistory = MediaPlayer.create(this,R.raw.historygpu);
        playing = 0;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerPos = group.getSelectedItemPosition();
                spinnerPos2 = group2.getSelectedItemPosition();

                if (spinnerPos == 1) {
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("key1", myValue);
                    editor.commit();
                    Intent intent = (new Intent(architecture.this, Maxwell.class));
                    startActivity(intent);
                }
                else if (spinnerPos == 2) {
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("key1", myValue);
                    editor.commit();
                    Intent intent = (new Intent(architecture.this, Pascal.class));
                    startActivity(intent);
                }
                else if (spinnerPos2 == 1) {
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("key1", myValue);
                    editor.commit();
                    Intent intent = (new Intent(architecture.this, FermiRefresh.class));
                    startActivity(intent);
                }
                else if (spinnerPos2 == 2) {
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("key1", myValue);
                    editor.commit();
                    Intent intent = (new Intent(architecture.this, Kepler.class));
                    startActivity(intent);
                }
                else if (spinnerPos2 == 3) {
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("key1", myValue);
                    editor.commit();
                    Intent intent = (new Intent(architecture.this, KeplerRefresh.class));
                    startActivity(intent);
                }
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinnerLatest, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        group.setAdapter(new NothingSelectedSpinnerAdapter(adapter, R.layout.contact_spinner_row_nothing_selected, this));

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.spinnerLegacy, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        group2.setAdapter(new NothingSelectedSpinnerAdapter(adapter2, R.layout.contact_spinner_row_nothing_selected, this));

        group2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                spinnerPos = group.getSelectedItemPosition();
                if (spinnerPos != 0) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(architecture.this, R.array.spinnerLatest, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        group.setAdapter(new NothingSelectedSpinnerAdapter(adapter, R.layout.contact_spinner_row_nothing_selected, architecture.this));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        group.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                spinnerPos2 = group2.getSelectedItemPosition();
                if (spinnerPos2 != 0) {
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(architecture.this, R.array.spinnerLegacy, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        group2.setAdapter(new NothingSelectedSpinnerAdapter(adapter2, R.layout.contact_spinner_row_nothing_selected, architecture.this));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
}
    Button.OnClickListener bHistory = new Button.OnClickListener() {
        @Override
        public void onClick(View v){
            switch(playing){
                case 0:
                    mpHistory.start();
                    playing = 1;
                    button1.setText("Pause Audio");
                    break;
                case 1:
                    mpHistory.pause();
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
                Intent intent = new Intent(architecture.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
