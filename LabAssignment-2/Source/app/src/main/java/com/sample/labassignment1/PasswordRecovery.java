package com.sample.labassignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.facebook.login.widget.LoginButton;

public class PasswordRecovery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);
    }

    public void UpdatePassword(View view)
    {
        Intent redirect = new Intent(PasswordRecovery.this, LoginActivity.class);
        startActivity(redirect);
    }
}
