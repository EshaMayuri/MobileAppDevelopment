package com.sample.labassignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.Login;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

    }
    public void Login(View view)
    {
        Intent redirect = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(redirect);
    }

    public void Signup(View view)
    {
        Intent redirect = new Intent(MainActivity.this,RegistrationActivity.class);
        startActivity(redirect);
    }





}
