package com.example.mindrevolution.nvidiacapstoneproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    int userNumber;
    TextView viewAccount, viewName, error;
    UserLocalStore userLocalStore;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupNvidiaButton();
        setupShopButton();

        error = (TextView) findViewById(R.id.txtError);

        viewAccount = (TextView) findViewById(R.id.txtViewAccount);
        viewAccount.setOnClickListener(this);

        signUp = (Button) findViewById(R.id.btnSignUp);
        signUp.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);
        User user = userLocalStore.getLoggedInUser();
        viewName = (TextView) findViewById(R.id.txtUsername);
        viewName.setText("Hi " + user.username);

        Button button2 = (Button) findViewById(R.id.btnVideoTip);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Videotip.class);
                startActivity(intent);
            }
        });
    }

    private void setupNvidiaButton()
    {
        Button button = (Button) findViewById(R.id.btnNvidia);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.nvidia.com/page/home.html")));
            }
        });
    }

    private void setupShopButton()
    {
        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        final EditText spendingCash = (EditText) findViewById(R.id.txtBudget);
        Button button = (Button) findViewById(R.id.btnBuy);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                try {
                    userNumber = Integer.parseInt(spendingCash.getText().toString());
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("key1", userNumber);
                    editor.commit();
                    startActivity(new Intent(MainActivity.this, architecture.class));
                }
                catch(NumberFormatException nfe)
                {
                    spendingCash.setText("");
                    error.setText("Invalid Entry.\n Please enter a number.");
                }
                catch (Exception e)
                {
                    spendingCash.setText("Invalid input.");
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.txtViewAccount:
                startActivity(new Intent(this, Logout.class));
                break;
            case R.id.btnSignUp:
                startActivity(new Intent(this, Login.class));
        }
    }
}