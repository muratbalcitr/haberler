package com.murat.haberler.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.murat.haberler.R;
import com.murat.haberler.fragment.Business_Fragment;
import com.murat.haberler.fragment.Entertainment_Fragment;
import com.murat.haberler.fragment.Health_Fragment;
import com.murat.haberler.fragment.Science_Fragment;
import com.murat.haberler.fragment.Son_Dakika_fragment;
import com.murat.haberler.fragment.Sport_Fragment;
import com.murat.haberler.fragment.Technology_Fragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;

    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragment = new Business_Fragment();
        toolbar.setTitle("İş Haberleri");
        loadFragment(fragment);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_business:

                fragment = new Business_Fragment();
                toolbar.setTitle("İş Haberleri");
                loadFragment(fragment);
                break;
            case R.id.nav_entertainment:
                fragment = new Entertainment_Fragment();
                toolbar.setTitle("Magazin Haberleri");
                loadFragment(fragment);
                break;
            case R.id.nav_health:
                fragment = new Health_Fragment();
                toolbar.setTitle("Sağlık Haberleri");
                loadFragment(fragment);
                break;
            case R.id.nav_science:
                fragment = new Science_Fragment();
                toolbar.setTitle("Bilim Haberleri");
                loadFragment(fragment);
                break;
            case R.id.nav_sport:
                fragment = new Sport_Fragment();
                toolbar.setTitle("Spor Haberleri");
                loadFragment(fragment);
                break;
            case R.id.nav_technology:
                fragment = new Technology_Fragment();
                toolbar.setTitle("Teknoloji Haberleri");
                loadFragment(fragment);
                break;
            case R.id.nav_news:
                fragment = new Son_Dakika_fragment();
                toolbar.setTitle("Son Dakika Haberleri");
                loadFragment(fragment);
                break;
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            Toast.makeText(this, "yenile", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

}
