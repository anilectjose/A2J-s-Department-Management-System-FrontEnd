package com.example.aja;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class tea_marklist extends AppCompatActivity {

    TextView fir,sec,thi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea_marklist);

        ActionBar newbar=getSupportActionBar();
        assert newbar != null;
        newbar.setTitle("Attendance");

        fir=findViewById(R.id.first);
        sec=findViewById(R.id.second);
        thi=findViewById(R.id.third);
        fir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),tea_f_mark.class);
                startActivity(i);
            }
        });
        sec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),tea_s_mark.class);
                startActivity(i);
            }
        });
        thi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(getApplicationContext(),tea_t_mark.class);
                startActivity(j);
            }
        });
    }
}
