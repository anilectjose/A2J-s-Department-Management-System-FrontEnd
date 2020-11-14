package com.example.aja;


import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class  about extends AppCompatActivity {

 private TextView link;
 private EditText sub,ourmail;
  Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ActionBar newbar=getSupportActionBar();
        assert newbar != null;
        newbar.setTitle("About");

        //FFNQQAXBG5MR   355398071259252
        ourmail = findViewById(R.id.email);
        sub = findViewById(R.id.subject);
        send = findViewById(R.id.mailit);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String outEmail=ourmail.getText().toString();
                String subj=sub.getText().toString();
                String[] email_divide = outEmail.split(",");
                Intent send = new Intent(Intent.ACTION_SEND);
                send.putExtra(Intent.EXTRA_EMAIL, email_divide);
                send.putExtra(Intent.EXTRA_SUBJECT, subj);
                send.setType("message/rfc822");
                send.setPackage("com.google.android.gm");
                startActivity(send);

            }
        });

        link = findViewById(R.id.link);
        link.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
