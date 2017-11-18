package com.sample.labassignment1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        drawerLayout = (DrawerLayout)findViewById(R.id.activity_home);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        NavigationView myDrawer = (NavigationView)findViewById(R.id.nav_view);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupDrawerContent(myDrawer);

    }

    public void GuardianAPI(View view)
    {
        Intent redirect = new Intent(HomeActivity.this, GuardianActivity.class);
        startActivity(redirect);
    }
    public void BooksAPI(View view)
    {
        Intent redirect = new Intent(HomeActivity.this, BooksActivity.class);
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
            case R.id.nav_settings:
                drawerLayout.closeDrawers();
                StartSettingsActivity();
                break;
            case R.id.nav_logout:
                drawerLayout.closeDrawers();
                StartLogoutActivity();
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
    private void StartSettingsActivity()
    {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
    private void StartLogoutActivity()
    {
        Intent intent = new Intent(this, LogoutActivity.class);
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
