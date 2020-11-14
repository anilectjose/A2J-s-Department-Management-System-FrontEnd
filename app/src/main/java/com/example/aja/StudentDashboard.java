package com.example.aja;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

public class StudentDashboard extends AppCompatActivity {

    ImageView assig, noti, leave, timetbl, internal, attend;
    SharedPreferences shp;
    String str_login_id;

    private int[] mImages = new int[] {
            R.drawable.quote0, R.drawable.quote1, R.drawable.quote3, R.drawable.quote4,
            R.drawable.quote5};

    private String[] mImagesTitle = new String[] {
            "Tony Robbins", "Les Brown", "Judy Garland", "Ryan Blair", "Les Brown"
    };

    Button log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        ActionBar newbar = getSupportActionBar();
        assert newbar != null;
        newbar.setTitle("Student Dashboard");

        CarouselView carouselView = findViewById(R.id.carousel);
        carouselView.setPageCount(mImages.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImages[position]);
            }
        });
        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(StudentDashboard.this, mImagesTitle[position], Toast.LENGTH_SHORT).show();
            }
        });

        shp = getSharedPreferences("login_daa", MODE_PRIVATE);
        str_login_id = shp.getString("login_id", ""); //you can get logind id in any activity like this

        assig = findViewById(R.id.assigment);
        noti = findViewById(R.id.notification);
        leave = findViewById(R.id.leave);
        timetbl = findViewById(R.id.table);
        internal = findViewById(R.id.mark);
        attend = findViewById(R.id.attendance);


        log=findViewById(R.id.logout);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Logging out", Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = shp.edit();
                editor.clear();
                editor.commit();
                Intent j = new Intent(getApplicationContext(), splash_screen.class);
                startActivity(j);
                finish();
            }
        });



        assig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Assignment.class);
                startActivity(i);
            }
        });
        noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(getApplicationContext(), notific.class);
                startActivity(j);
            }
        });
        leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(getApplicationContext(), leaveform.class);
                startActivity(j);
            }
        });
        timetbl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(getApplicationContext(), timetable.class);
                startActivity(j);
            }
        });
        internal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(getApplicationContext(), intmark.class);
                startActivity(j);
            }
        });
        attend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(getApplicationContext(), attendance.class);
                startActivity(j);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.profile_id) {
            Intent i = new Intent(StudentDashboard.this, user_profile.class);
            i.putExtra("key_login_id", str_login_id);
            startActivity(i);
        }

        return true;
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(StudentDashboard.this);
        builder.setTitle("Really Exit?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StudentDashboard.super.onBackPressed();
                        Toast.makeText(StudentDashboard.this, "See U soon", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null).setCancelable(false);
        AlertDialog alert= builder.create();
        alert.show();
    }
}
