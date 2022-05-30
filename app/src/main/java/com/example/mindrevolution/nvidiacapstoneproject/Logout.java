package com.example.mindrevolution.nvidiacapstoneproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Logout extends AppCompatActivity implements View.OnClickListener {

    Button bLogout, bBack;
    EditText etName, etAge, etUsername;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        etName = (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);
        etUsername = (EditText) findViewById(R.id.etUsername);
        bLogout = (Button) findViewById(R.id.bLogout);
        bBack = (Button) findViewById(R.id.btnBack);

        bLogout.setOnClickListener(this);
        bBack.setOnClickListener(this);
        userLocalStore = new UserLocalStore(this);
    }

    @Override
    protected  void onStart() {
        super.onStart();
        if (authenticate() == true){
            displayUserDetails();
        }else{
            startActivity(new Intent(Logout.this, Login.class));
        }
    }

    private boolean authenticate() {
        return userLocalStore.getUserLoggedIn();
    }

    private void displayUserDetails(){
        User user = userLocalStore.getLoggedInUser();

        etUsername.setText(user.username);
        etName.setText(user.name);
        etAge.setText(user.age + "");
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.bLogout:
                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);
                startActivity(new Intent(this, Login.class));
                break;

            case R.id.btnBack:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}
