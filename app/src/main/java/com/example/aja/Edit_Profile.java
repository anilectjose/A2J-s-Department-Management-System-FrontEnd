package com.example.aja;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Edit_Profile extends AppCompatActivity {
    TextInputLayout edt_name, edt_password, edt_mail, edt_num, edt_usr,edt_regno, edt_code, edt_year;
    Button btn_register;
    String str_name,str_pswd,str_mail,str_num,str_usr,str_reg,str_year;
    String BaseURL = "",RegUrl,str_login_id,str_role;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__profile);
        ActionBar newbar=getSupportActionBar();
        assert newbar != null;
        newbar.setTitle("Edit Profile");

        sp = getSharedPreferences("login_daa", MODE_PRIVATE);
        str_login_id = sp.getString("login_id", "");
        str_role = sp.getString("user_role", "");

        BaseURL = getResources().getString(R.string.Base_URL)+"update_profile.php";
        RegUrl = getResources().getString(R.string.Base_URL)+"get_details.php";

        edt_name = findViewById(R.id.edit_name);
        edt_mail = findViewById(R.id.edit_email);
        edt_num = findViewById(R.id.edit_num);
        edt_usr = findViewById(R.id.username);
        edt_password = findViewById(R.id.password);
        edt_regno=findViewById(R.id.edit_reg);
        edt_year=findViewById(R.id.edit_year);

        btn_register = findViewById(R.id.edit_update);

        new getDetails().execute();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_name=edt_name.getEditText().getText().toString().trim();
                str_mail=edt_mail.getEditText().getText().toString().trim();
                str_num=edt_num.getEditText().getText().toString().trim();
                str_year=edt_year.getEditText().getText().toString().trim();
                str_reg= edt_regno.getEditText().getText().toString().trim();
                str_usr=edt_usr.getEditText().getText().toString().trim();
                str_pswd=edt_password.getEditText().getText().toString().trim();

                if (str_name.equals(""))
                {
                    Toast.makeText(Edit_Profile.this, "Name is required", Toast.LENGTH_SHORT).show();
                }
                else if (str_mail.equals(""))
                {
                    Toast.makeText(Edit_Profile.this, "Email is required", Toast.LENGTH_SHORT).show();
                }
                else if (str_num.equals(""))
                {
                    Toast.makeText(Edit_Profile.this, "Phone number is required", Toast.LENGTH_SHORT).show();
                }
                else if (str_reg.equals(""))
                {
                    Toast.makeText(Edit_Profile.this, "Reg no requied", Toast.LENGTH_SHORT).show();
                }
                else if (str_year.equals(""))
                {
                    Toast.makeText(Edit_Profile.this, "Year requied", Toast.LENGTH_SHORT).show();
                }
                else if (str_usr.equals(""))
                {
                    Toast.makeText(Edit_Profile.this, "Username is requied", Toast.LENGTH_SHORT).show();
                }
                else if (str_pswd.equals(""))
                {
                    Toast.makeText(Edit_Profile.this, "Password is requied", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    new updateTask().execute();
                }
            }
        });
    }

    private class updateTask extends AsyncTask<String,String, JSONObject>
    {


        @Override
        protected JSONObject doInBackground(String... strings)
        {
            JSONParser parser = new JSONParser();
            Map<String,String> hashmap = new HashMap<>();
            hashmap.put("name", str_name);
            hashmap.put("email_id", str_mail);
            hashmap.put("year", str_year);
            hashmap.put("mobile_num", str_num);
            hashmap.put("register_no", str_reg);
            hashmap.put("username", str_usr);
            hashmap.put("password",str_pswd);
            hashmap.put("atid",str_login_id);
            hashmap.put("role",str_role);

            JSONObject jObj = parser.makeHttpRequest(BaseURL,"POST", hashmap);

            return jObj;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject)
        {
            try
            {
                int success_value = jsonObject.getInt("success");

                if(success_value==1)
                {
                    String msg = jsonObject.getString("message");

                    Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();

                   // finish();

                }
                else
                {
                    String msg = jsonObject.getString("message");

                    Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();


                }
            }


            catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private class getDetails extends AsyncTask<String,String, JSONObject>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            try {
                int success = jsonObject.getInt("success");
                Log.e("RESPONSE",""+success);
                if(success==1)
                {

                    String var1= jsonObject.getJSONObject("result").getString("nme");
                    String var2= jsonObject.getJSONObject("result").getString("mobile");
                    String var3= jsonObject.getJSONObject("result").getString("email");
                    String var4= jsonObject.getJSONObject("result").getString("register_no");
                    String var5= jsonObject.getJSONObject("result").getString("year");
                    String var6= jsonObject.getJSONObject("result").getString("name");
                    String var7= jsonObject.getJSONObject("result").getString("password");

                    edt_name.getEditText().setText(var1);
                    edt_num.getEditText().setText(var2);
                    edt_mail.getEditText().setText(var3);
                    edt_regno.getEditText().setText(var4);
                    edt_year.getEditText().setText(var5);
                    edt_usr.getEditText().setText(var6);
                    edt_password.getEditText().setText(var7);
                }
                else
                {
                    Log.e("ERROR","API ERROR");
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
        @Override
        protected JSONObject doInBackground(String... strings)
        {
            JSONParser parser=new JSONParser();
            Map<String,String> hashmap = new HashMap<>();
            hashmap.put("atid", str_login_id);

            JSONObject jsonObject=parser.makeHttpRequest(RegUrl,"POST", hashmap);

            return jsonObject;
        }
    }

}
