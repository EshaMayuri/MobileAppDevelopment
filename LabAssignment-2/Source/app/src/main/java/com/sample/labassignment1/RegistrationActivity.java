package com.sample.labassignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    public static String fName = "";
    public static String lName = "";
    public static String eId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    public void SignUp(View view)
    {
        EditText firstName = (EditText)findViewById(R.id.TextEdit_FirstName);
        EditText lastName = (EditText)findViewById(R.id.TextEdit_LastName);
        EditText emailId = (EditText)findViewById(R.id.TextEdit_EmailId);
        EditText userName = (EditText)findViewById(R.id.TextEdit_UserName);
        EditText password = (EditText)findViewById(R.id.TextEdit_Password);
        EditText dob = (EditText)findViewById(R.id.TextEdit_DOB);
        EditText address = (EditText)findViewById(R.id.TextEdit_Address);


        if(firstName.getText().toString()!= "" && lastName.getText().toString() != ""
                && emailId.getText().toString()!="" && userName.getText().toString()!=""
                && password.getText().toString() != "" && dob.getText().toString() != ""
                && address.getText().toString() != "")
        {
            fName = firstName.getText().toString();
            lName = lastName.getText().toString();
            eId = emailId.getText().toString();
            Intent redirect = new Intent(RegistrationActivity.this, HomeActivity.class);
            startActivity(redirect);
        }
        else
        {
            Toast.makeText(getBaseContext(), "Please enter valid information", Toast.LENGTH_LONG).show();
        }
    }
}
