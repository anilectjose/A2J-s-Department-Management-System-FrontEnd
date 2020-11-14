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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

public class TeacherDashboard extends AppCompatActivity {
    ImageView detail,noti,leave,timetbl,assig,attend,sleave,ednoti,internal;
    SharedPreferences shp;
    String str_login_id;
    String str_role,str_mentor_cls;

    Button log;
    TextView navi,login,abo;

    private int[] mImages = new int[] {
            R.drawable.quote0, R.drawable.quote1, R.drawable.quote3, R.drawable.quote4,
            R.drawable.quote5};

    private String[] mImagesTitle = new String[] {
            "Tony Robbins", "Les Brown", "Judy Garland", "Ryan Blair", "Les Brown"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);

        ActionBar newbar=getSupportActionBar();
        assert newbar != null;
        newbar.setTitle("Teacher Dashboard");

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
                Toast.makeText(TeacherDashboard.this, mImagesTitle[position], Toast.LENGTH_SHORT).show();
            }
        });

        shp = getSharedPreferences("login_daa", MODE_PRIVATE);
        str_login_id = shp.getString("login_id",""); //you can get logind id in any activity like this
        str_mentor_cls = shp.getString("mentor_cls", "");

        detail=findViewById(R.id.detail);
        assig=findViewById(R.id.assig);
        noti=findViewById(R.id.notification);
        leave=findViewById(R.id.leave);
        timetbl=findViewById(R.id.table);
        internal=findViewById(R.id.edit_mark);
        attend=findViewById(R.id.atten);
        sleave=findViewById(R.id.lea_app);
        ednoti=findViewById(R.id.edit_noti);


        navi=findViewById(R.id.navig);
        login=findViewById(R.id.login);
        abo=findViewById(R.id.about);
        log=findViewById(R.id.logout);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),OnBoarding.class);
                startActivity(i);
            }
        });
        navi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),navigate.class);
                startActivity(i);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),login.class);
                startActivity(i);
            }
        });
        abo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),about.class);
                startActivity(i);
            }
        });




        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),stu_detail.class);
                startActivity(i);
            }
        });
        assig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),tea_assignment.class);
                startActivity(i);
            }
        });
        noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(getApplicationContext(),tea_notification.class);
                startActivity(j);
            }
        });
        leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(getApplicationContext(),teacher_leaveform.class);
                j.putExtra("mentor", str_mentor_cls);
                startActivity(j);
            }
        });
        timetbl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(getApplicationContext(),timetable.class);
                startActivity(j);
            }
        });
        internal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(getApplicationContext(),tea_marklist.class);
                startActivity(j);
            }
        });
        attend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(getApplicationContext(),tea_attendance.class);
                startActivity(j);
            }
        });
        sleave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(getApplicationContext(),tea_leaveofstud.class);
                startActivity(j);
            }
        });
        ednoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(getApplicationContext(),tea_editnoti.class);
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
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(TeacherDashboard.this);
        builder.setTitle("Really Exit?")
        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TeacherDashboard.super.onBackPressed();
                Toast.makeText(TeacherDashboard.this, "See U soon", Toast.LENGTH_SHORT).show();
            }
        })
        .setNegativeButton("Cancel", null).setCancelable(false);
        AlertDialog alert= builder.create();
        alert.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.profile_id) {
            Intent i = new Intent(TeacherDashboard.this, user_profile.class);
            i.putExtra("key_login_id", str_login_id);
            startActivity(i);
        }

        return true;


    }
}
