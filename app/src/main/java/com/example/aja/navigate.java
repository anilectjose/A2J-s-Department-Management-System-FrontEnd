package com.example.aja;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class navigate extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    static final float END_SCALE=0.7f;
    LinearLayout contentView;
    private DrawerLayout adl;
    private NavigationView navi;
    private ActionBarDrawerToggle atoggle;

    SharedPreferences shp;
    String str_login_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigate);
        shp = getSharedPreferences("login_daa", MODE_PRIVATE);
        str_login_id = shp.getString("login_id","");

        adl = (DrawerLayout) findViewById(R.id.drawer);
        navi = (NavigationView) findViewById(R.id.navigationview);
        contentView=findViewById(R.id.content);
        navi.bringToFront();
        Menu m= navi.getMenu();
       /* m.findItem(R.id.nav_profile).setVisible(false);
        m.findItem(R.id.nav_logout).setVisible(false);*/
        atoggle = new ActionBarDrawerToggle(this, adl, R.string.open, R.string.close);
        adl.addDrawerListener((atoggle));
        atoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navi.setCheckedItem(R.id.nav_assi);
        navi.setNavigationItemSelectedListener(this);

        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {
        adl.setScrimColor(getResources().getColor(R.color.lightcolor));

        //Add any color or remove it to use the default one!
        // To make it transparent use Color.Transparent in side setScrimColor();
        //drawerLayout.setScrimColor(Color.TRANSPARENT);
         adl.addDrawerListener(new DrawerLayout.SimpleDrawerListener()
         {
        @Override public void onDrawerSlide(View drawerView, float slideOffset) {
            /* Scale the View based on current slide offset */
            final float diffScaledOffset = slideOffset * (1 - END_SCALE);
             final float offsetScale = 1 - diffScaledOffset;
             contentView.setScaleX(offsetScale);
             contentView.setScaleY(offsetScale);
             // Translate the View, accounting for the scaled width
            final float xOffset = drawerView.getWidth() * slideOffset;
            final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
            final float xTranslation = xOffset - xOffsetDiff;
            contentView.setTranslationX(xTranslation); } });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (atoggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected((item));

    }

    @Override
    public void onBackPressed() {
        if (adl.isDrawerOpen(GravityCompat.START)){
            adl.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_settings:
                Intent i=new Intent(getApplicationContext(),login.class);
                startActivity(i);
                break;
            case R.id.nav_logout:
                Toast.makeText(getApplicationContext(), "Logging out", Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = shp.edit();
                editor.clear();
                editor.commit();
                Intent j = new Intent(getApplicationContext(), splash_screen.class);
                startActivity(j);
                finish();
                break;
            case R.id.nav_about:
                Intent k=new Intent(getApplicationContext(),about.class);
                startActivity(k);
                break;
        }
        adl.closeDrawer(GravityCompat.START);
        return true;
    }
}