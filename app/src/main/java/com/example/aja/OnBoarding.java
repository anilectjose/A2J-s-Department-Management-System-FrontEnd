package com.example.aja;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class OnBoarding extends AppCompatActivity {
    ViewPager vp;
    LinearLayout dots;

    slider_adapter sliderAdapter;
    TextView[] dot;
    Button start;
    Animation anima;
    int currentPosi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        vp = findViewById(R.id.slider);
        dots = findViewById(R.id.sli);
        start = findViewById(R.id.get_start);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), login.class);
                startActivity(i);
            }
        });
        //call adapter
        sliderAdapter = new slider_adapter(this);
        vp.setAdapter(sliderAdapter);

        addDots(0);
        vp.addOnPageChangeListener(changeListener);
    }

    public void skip(View view) {
        startActivity(new Intent(getApplicationContext(), login.class));
    }

    public void next(View view) {
        vp.setCurrentItem(currentPosi + 1);
    }


    private void addDots(int position) {

        dot = new TextView[4];
        dots.removeAllViews();

        for (int i = 0; i < dot.length; i++) {
            dot[i] = new TextView(this);
            dot[i].setText(Html.fromHtml("&#8226;"));
            dot[i].setTextSize(35);

            dots.addView(dot[i]);

        }
        if (dot.length > 0) {
            dot[position].setTextColor(getResources().getColor(R.color.colorRed));
        }
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentPosi = position;

            if (position == 0) {
                start.setVisibility(View.INVISIBLE);
            }
            else if (position == 1) {
                start.setVisibility(View.INVISIBLE);
            }
            else if (position == 2) {
                start.setVisibility(View.INVISIBLE);
            }
            else
                {
                anima = AnimationUtils.loadAnimation(OnBoarding.this, R.anim.bottom_animation);
                start.setAnimation(anima);
                start.setVisibility(View.VISIBLE);
               }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
