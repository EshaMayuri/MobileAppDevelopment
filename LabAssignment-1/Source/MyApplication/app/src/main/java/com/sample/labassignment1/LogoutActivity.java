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
import android.support.design.widget.NavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class LogoutActivity extends AppCompatActivity {

    TextView textview;
    Button yesBtn;
    Button noBtn;

    NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        textview = (TextView)findViewById(R.id.textView13);
        yesBtn = (Button)findViewById(R.id.YesBtn);
        noBtn = (Button)findViewById(R.id.NoBtn);

        drawerLayout = (DrawerLayout)findViewById(R.id.activity_logout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        NavigationView myDrawer = (NavigationView)findViewById(R.id.nav_view);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupDrawerContent(myDrawer);
    }

    public void Yes(View view)
    {
        Intent redirect = new Intent(this, MainActivity.class);
        startActivity(redirect);
    }
    public void No(View view)
    {
        Fragment fragment = null;
        Class fragmentClass = HomeActivity.class;
        try
        {
            fragment = (Fragment)fragmentClass.newInstance();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flcontent,new Fragment()).commit();

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
