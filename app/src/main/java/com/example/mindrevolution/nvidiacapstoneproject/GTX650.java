package com.example.mindrevolution.nvidiacapstoneproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class GTX650 extends AppCompatActivity {
    DBAdapter myDb;
    int count = 0;
    private ImageView imgScale;
    private TextView text;
    private Animation mScaleAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gtx650);
        imgScale = (ImageView) findViewById(R.id.imgGTXBench);
        text = (TextView) findViewById(R.id.txtScore);

        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        final int myValue = sharedPref.getInt("key1", 0);
        DecimalFormat currency = new DecimalFormat("$###,###.##");
        final EditText resultView = (EditText) findViewById(R.id.etxtCashAmount);
        resultView.setText(currency.format(myValue));

        setupHomeButton();
        setupBackButton();
        openDB();
        setupAddRecordButton();

        Button buttonViewCart = (Button) findViewById(R.id.btnViewCart);
        buttonViewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("key1", myValue);
                editor.commit();
                Intent intent = new Intent(GTX650.this, Shoppingcart.class);
                startActivity(intent);
            }
        });
    }
    private void setupHomeButton()
    {
        Button button = (Button) findViewById(R.id.btnHome);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GTX650.this, MainActivity.class);
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
                Intent intent = new Intent(GTX650.this, Kepler.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeDB();
    }

    private void openDB() {
        myDb = new DBAdapter(this);
        myDb.open();
    }
    private void closeDB() {
        myDb.close();
    }

    private void setupAddRecordButton() {

        Button button = (Button) findViewById(R.id.btnAddCart);
        button.setOnClickListener(new View.OnClickListener() {

            final TextView countText = (TextView) findViewById(R.id.txtAddCartCount);
            EditText quantity = (EditText) findViewById(R.id.etxtQuantity);

            @Override
            public void onClick(View v) {
                String quantity2 = quantity.getText().toString();

                try {
                    int quantity3 = Integer.parseInt(quantity2);
                    int sum = quantity3 *50;
                    count++;
                    long newId = myDb.insertRow("GTX 650", 50, quantity3, sum);
                    myDb.getRow(newId);
                    countText.setText("Successfully added " + count + " Times");
                } catch (NumberFormatException nfe) {
                    countText.setText("Quantity needed!");
                }
            }
        });
    }
    public void scaleAnimation(View view){
        mScaleAnim = AnimationUtils.loadAnimation(this, R.anim.benchmark650);
        imgScale.startAnimation(mScaleAnim);

        Handler myHandler = new Handler();
        myHandler.postDelayed(mMyRunnable, 4000);
    }
    private Runnable mMyRunnable = new Runnable()
    {
        @Override
        public void run()
        {
            text.setText("3DMark Fire Strike Score: ~2100");
        }
    };
}
