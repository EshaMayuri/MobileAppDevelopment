package com.sample.labassignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class LoginActivity extends AppCompatActivity {

    private FbConnectHelper fbConnectHelper;

    TextView textView;
    LoginButton fbLoginButton;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);

        textView = (TextView)findViewById(R.id.ForgotPwdBtn);

        SpannableString content = new SpannableString("Forgot password");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        textView.setText(content);

        fbLoginButton = (LoginButton)findViewById(R.id.login_button);

        callbackManager = CallbackManager.Factory.create();
        fbLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getBaseContext(),"Login success!", Toast.LENGTH_LONG).show();
                Intent redirect = new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(redirect);
            }

            @Override
            public void onCancel() {
                Toast.makeText(getBaseContext(),"Login cancelled!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getBaseContext(),"Please try after sometime!", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void FacebookLogin(View view) {
        fbConnectHelper.connect();

    }

    public void SignIn(View view)
    {
        EditText userName = (EditText)findViewById(R.id.TextEdit_UserName);
        EditText password = (EditText)findViewById(R.id.TextEdit_Password);

        if(userName.getText().toString()!=null && password.getText().toString()!=null)
        {
            Intent redirect = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(redirect);
        }
        else if(userName.getText().toString() == null)
        {
            Toast.makeText(getBaseContext(),"Please enter a user name", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getBaseContext(),"Please enter a password", Toast.LENGTH_LONG).show();
        }
    }

    public void ForgotPassword(View view)
    {
        Intent redirect = new Intent(LoginActivity.this, PasswordRecovery.class);
        startActivity(redirect);
    }
}
