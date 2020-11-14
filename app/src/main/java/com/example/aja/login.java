package com.example.aja;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class login extends AppCompatActivity {
    TextInputLayout usr,psd;
    TextView ln,ls;
    Button btn_login,reg,forget;
    String  strusername,strpassword, BaseUrl= "";

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        BaseUrl = getResources().getString(R.string.Base_URL)+"user_login.php";

        ln=findViewById(R.id.logo_name);
        ls=findViewById(R.id.sig);
        usr=(TextInputLayout)findViewById(R.id.username);
        psd=(TextInputLayout)findViewById(R.id.password);
        forget=findViewById(R.id.fp);
        btn_login=findViewById(R.id.login_button);
        reg=findViewById(R.id.register_link);

        sp = getSharedPreferences("login_daa", MODE_PRIVATE);

        getSupportActionBar().hide();

        /*forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(getApplicationContext(),forgot_password.class);
                startActivity(j);
            }
        });*/

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login.this, regi.class);
                Pair[] pairs = new Pair[6];
                pairs[0]=new Pair<View,String>(ln,"title");
                pairs[1]=new Pair<View,String>(ls,"name");
                pairs[2]=new Pair<View,String>(usr,"usrn");
                pairs[3]=new Pair<View,String>(psd,"psd");
                pairs[4]=new Pair<View,String>(btn_login,"bttn");
                pairs[5]=new Pair<View,String>(reg,"lnk");
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(login.this,pairs);
                    startActivity(i,options.toBundle());
                }
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login.this, forgot_password.class);
                Pair[] pairs = new Pair[4];
                pairs[0]=new Pair<View,String>(usr,"nme");
                pairs[1]=new Pair<View,String>(ln,"logo_text");
                pairs[2]=new Pair<View,String>(ls,"title");
                pairs[3]=new Pair<View,String>(btn_login,"bttn");
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(login.this,pairs);
                    startActivity(i,options.toBundle());
                }
            }
        });


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strusername=usr.getEditText().getText().toString().trim();
                strpassword=psd.getEditText().getText().toString().trim();

                if (strusername.equals(""))
                {
                    Toast.makeText(login.this,"Enter username",Toast.LENGTH_LONG).show();
                }
                else if(strpassword.equals(""))
                {
                    Toast.makeText(login.this,"Enter password",Toast.LENGTH_LONG).show();
                }
                else
                {
                    new LoginTask().execute();

                }
            }
        });
    }
    private void checkPermission(String readExternalStorage, int storagePermissionCode)
    {
        if (ContextCompat.checkSelfPermission(login.this, readExternalStorage)
                == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(login.this,
                    new String[] { readExternalStorage },
                    storagePermissionCode);
        }
    }
    private class LoginTask extends AsyncTask<String,String, JSONObject>
    {


        @Override
        protected JSONObject doInBackground(String... strings)
        {
            JSONParser parser = new JSONParser();
            Map<String,String> hashmap = new HashMap<>();
            hashmap.put("username", strusername);
            hashmap.put("password",strpassword);

            JSONObject jObj = parser.makeHttpRequest(BaseUrl,"POST", hashmap);

            return jObj;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            try {
                int success_value = jsonObject.getInt("success");

                if(success_value==1)
                {
                    String msg = jsonObject.getString("message");

                    String user_id = jsonObject.getJSONObject("data").getString("role_id");
                    String user_role = jsonObject.getJSONObject("data").getString("role");
                    String usr_name = jsonObject.getJSONObject("data").getString("name");
                    String usr_pass = jsonObject.getJSONObject("data").getString("password");
                    String mentor_cls = jsonObject.getJSONObject("data").getString("mentor");

                    if(user_role.equals("student"))
                    {

                        SharedPreferences.Editor e = sp.edit();
                        e.putString("login_id",user_id); //login_id is the key for user id
                        e.putString("user_role",user_role);
                        e.putString("usrn",usr_name);
                        e.putString("pswd",usr_pass);


                        e.commit();

                        Intent i=new Intent(login.this, StudentDashboard.class);
                        startActivity(i);

                    }

                    else if(user_role.equals("teacher"))
                    {
                        SharedPreferences.Editor e = sp.edit();
                        e.putString("login_id",user_id);
                        e.putString("user_role",user_role);
                        e.putString("usrn",usr_name);
                        e.putString("pswd",usr_pass);
                        e.putString("mentor_cls",mentor_cls);

                        e.commit();

                        Intent i=new Intent(login.this, TeacherDashboard.class);
                        startActivity(i);

                    }


                }
                else
                {
                    String msg = jsonObject.getString("message");

                    Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();

                }
            } catch (JSONException ex) {
                ex.printStackTrace();
            }

        }
    }


}


