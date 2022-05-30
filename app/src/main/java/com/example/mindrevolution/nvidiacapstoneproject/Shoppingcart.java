package com.example.mindrevolution.nvidiacapstoneproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Shoppingcart extends AppCompatActivity {
    DBAdapter myDb;
    int finalAmount;
    long rowIDNumber;
    EditText rowID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppingcart);

        openDB();
        setupAddClearButton();
        setupAddDisplayButton();
        setupTotalButton();
        setupDeleteRowButton();

        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        final int myValue = sharedPref.getInt("key1", 0);
        final DecimalFormat currency = new DecimalFormat("$###,###.##");
        final EditText resultView = (EditText) findViewById(R.id.etxtCashAmount);
        rowID = (EditText) findViewById(R.id.etxtRowID);

        resultView.setText(currency.format(myValue));
        finalAmount = myValue;

        Button buttonPurchase = (Button) findViewById(R.id.btnPurchase);
        buttonPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = myDb.sumColumnTotal();
                int total = c.getInt(c.getColumnIndex("myTotal"));
                int amount = myValue - total;
                if (amount < 0) {
                    resultView.setText("Budget exceeded!");
                } else
                {
                    resultView.setText(currency.format(amount));
                    finalAmount = amount;
                }
                }
        });


        Button button = (Button) findViewById(R.id.btnBack);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("key1", finalAmount);
                editor.commit();
                Intent intent = new Intent(Shoppingcart.this, architecture.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeDB();
    }

    private void closeDB() {
        myDb.close();
    }

    private void openDB() {
        myDb = new DBAdapter(this);
        myDb.open();
    }

    private void displayText(String message) {
        TextView textView = (TextView) findViewById(R.id.textDisplay);
        textView.setText(message);
    }

    private void setupAddDisplayButton() {
        Button button = (Button) findViewById(R.id.btnDisplay);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayText("Clicked display record!");

                Cursor cursor = myDb.getAllRows();
                displayRecordSet(cursor);
            }
        });
    }
    private void setupAddClearButton() {
        Button button = (Button) findViewById(R.id.btnClear);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayText("Clicked clear all!");
                myDb.deleteAll();
            }
        });
    }

    private void setupDeleteRowButton() {
        Button button = (Button) findViewById(R.id.btnDeleteRow);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    rowIDNumber = Integer.parseInt(rowID.getText().toString());
                    myDb.deleteRow(rowIDNumber);
                    displayText("Deleted Row");
                    rowID.setText("");
                }
                catch(NumberFormatException nfe)
                {
                    displayText("Must be numeric!");
                }

            }
        });
    }
    private void displayRecordSet(Cursor cursor) {
        String message = "";

        if (cursor.moveToFirst()) {
            do {

                int id = cursor.getInt(DBAdapter.COL_ROWID);
                String name = cursor.getString(DBAdapter.COL_NAME);
                int price = cursor.getInt(DBAdapter.COL_PRICE);
                int quantity = cursor.getInt(DBAdapter.COL_QUANTITY);
                int sum = cursor.getInt(DBAdapter.COL_SUM);

                message += "id=" + id
                        +", Name=" + name
                        +", Price= $" + price
                        +", Quantity=" + quantity
                        +", Sum=" + sum
                        +"\n";
            } while(cursor.moveToNext());
        }
        cursor.close();
        displayText(message);
    }

    private void setupTotalButton(){
        Button button = (Button) findViewById(R.id.btnShopTotal);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = myDb.sumColumnTotal();
                int total = c.getInt(c.getColumnIndex("myTotal"));
                String total2 = Integer.toString(total);
                displayText("$" + total2);
            }
        });
    }

    //The ID only resets to 1 when you change the DATABASE_VERSION number in the DBAdapter. Some call it a Schema Version change.
}
