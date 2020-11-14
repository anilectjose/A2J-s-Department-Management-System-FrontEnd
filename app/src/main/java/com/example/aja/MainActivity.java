package com.example.aja;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText edt_name,edt_password;
    TextView ln,ls;
    Button btn_login;
    String  strusername,strpassword, BaseUrl= "";

    SharedPreferences sp;
    String str_login_id,uname,passw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BaseUrl = getResources().getString(R.string.Base_URL)+"user_edit.php";
        edt_name=findViewById(R.id.username);
        edt_password=findViewById(R.id.password);
        ln=findViewById(R.id.logo_name);
        ls=findViewById(R.id.sig);
        btn_login=findViewById(R.id.login_button);

        sp = getSharedPreferences("login_daa", MODE_PRIVATE);
        str_login_id = sp.getString("login_id", "");
        uname = sp.getString("usrn", "");
        passw = sp.getString("pswd", "");

        getSupportActionBar().hide();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strusername=edt_name.getText().toString().trim();
                strpassword=edt_password.getText().toString().trim();

                if (strusername.equals(""))
                {
                    Toast.makeText(MainActivity.this,"Enter username",Toast.LENGTH_LONG).show();
                }
                else if(strpassword.equals(""))
                {
                    Toast.makeText(MainActivity.this,"Enter password",Toast.LENGTH_LONG).show();
                }
                else if((uname.equals(strusername))&&(strpassword.equals(passw)))
                {
                    Intent i=new Intent(MainActivity.this, Edit_Profile.class);
                    startActivity(i);

                }
                else {
                    Toast.makeText(MainActivity.this, "User Verification Failed", Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, "Confirm Username and Password", Toast.LENGTH_SHORT).show();
                }

            }
        });
            }

}

