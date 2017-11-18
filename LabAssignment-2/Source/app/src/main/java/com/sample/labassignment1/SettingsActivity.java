package com.sample.labassignment1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    TextView textView;
    EditText firstName;
    EditText lastName;
    EditText emailId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        textView = (TextView)findViewById(R.id.textview);
        firstName = (EditText)findViewById(R.id.firstName);
        lastName = (EditText)findViewById(R.id.lastName);
        emailId = (EditText)findViewById(R.id.emailID);


        firstName.setText(RegistrationActivity.fName);
        lastName.setText(RegistrationActivity.lName);
        emailId.setText(RegistrationActivity.eId);

        drawerLayout = (DrawerLayout)findViewById(R.id.activity_settings);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        NavigationView myDrawer = (NavigationView)findViewById(R.id.nav_view);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupDrawerContent(myDrawer);
    }

    public void Update(View view)
    {
        Intent redirect = new Intent(SettingsActivity.this, HomeActivity.class);
        startActivity(redirect);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(toggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void selectItemDrawer(MenuItem item)
    {
        Class fragmentClass;
        switch (item.getItemId())
        {
            case R.id.nav_help:
                drawerLayout.closeDrawers();
                StartHelpActivity();
                break;
            case R.id.nav_logout:
                drawerLayout.closeDrawers();
                StartLogoutActivity();
                break;
            case R.id.nav_home:
                drawerLayout.closeDrawers();
                StartHomeActivity();
                break;
            default:
                break;
        }
    }

    private void StartHelpActivity()
    {
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }
    private void StartLogoutActivity()
    {
        Intent intent = new Intent(this, LogoutActivity.class);
        startActivity(intent);
    }
    private void StartHomeActivity()
    {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
    private void setupDrawerContent(NavigationView navigationView)
    {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                selectItemDrawer(item);
                return true;
            }
        });
    }
}
