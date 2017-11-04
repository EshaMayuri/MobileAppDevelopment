package com.sample.labassignment1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {

    TextView helpText;
    EditText helpSearch;

    NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        helpText = (TextView)findViewById(R.id.textView12);
        helpSearch = (EditText)findViewById(R.id.editText2);

        drawerLayout = (DrawerLayout)findViewById(R.id.activity_help);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        NavigationView myDrawer = (NavigationView)findViewById(R.id.nav_view);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupDrawerContent(myDrawer);
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
            case R.id.nav_logout:
                drawerLayout.closeDrawers();
                StartLogoutActivity();
                break;
            case R.id.nav_settings:
                drawerLayout.closeDrawers();
                StartSettingsActivity();
                break;
            case R.id.nav_home:
                drawerLayout.closeDrawers();
                StartHomeActivity();
                break;
            default:
                break;
        }
    }

    private void StartLogoutActivity()
    {
        Intent intent = new Intent(this, LogoutActivity.class);
        startActivity(intent);
    }
    private void StartSettingsActivity()
    {
        Intent intent = new Intent(this, SettingsActivity.class);
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
