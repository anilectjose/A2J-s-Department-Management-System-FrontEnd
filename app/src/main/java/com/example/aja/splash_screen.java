package com.example.aja;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class splash_screen extends AppCompatActivity {
Handler  handler;
Animation top,bot,bs;
ImageView img;
TextView logo,name,slogan;
SharedPreferences sp;
String str_user_role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        sp = getSharedPreferences("login_daa", MODE_PRIVATE);
        str_user_role = sp.getString("user_role","");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        handler=new Handler();

        top= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bot= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        bs= AnimationUtils.loadAnimation(this,R.anim.smallbig);

        img=findViewById(R.id.img1);
        logo=findViewById(R.id.txt1);
        name=findViewById(R.id.txt2);
        slogan=findViewById(R.id.txt3);

        img.setAnimation(bs);
        logo.setAnimation(top);
        name.setAnimation(bot);
        slogan.setAnimation(bot);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if(str_user_role.equals("teacher"))
                {
                    Intent intent=new Intent(getApplicationContext(),TeacherDashboard.class);
                    startActivity(intent);
                }
                else if (str_user_role.equals("student")) {
                    Intent intent=new Intent(getApplicationContext(),StudentDashboard.class);
                    startActivity(intent);
                    finish();
                }

                else
                {
                    Intent intent=new Intent(splash_screen.this,OnBoarding.class);
                    startActivity(intent);
                    finish();
                }
/*
for logout
                Toast.makeText(this, "Logging out", Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.commit();*/


            }
        },3000);
    }
}
