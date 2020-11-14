package com.example.aja;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class regi extends AppCompatActivity {
    TextInputLayout edt_name, edt_password, edt_mail, edt_num, edt_usr,edt_regno, edt_year;
    TextView register_link, txt_name;
    EditText edt_code;
    Button btn_register;
    RadioGroup gender;
    RadioButton rd_Gender;
    String str_name,str_pswd,str_mail,str_num,str_usr,str_reg, str_gender,str_year,str_code_entered,str_reg_tpe;
    String supplied_teacher_code="A2J";
    String flag="0";
    String BaseURL = "";
    Spinner mySpinner;
    String chactegory_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);


        BaseURL = getResources().getString(R.string.Base_URL)+"user_registration.php";

        edt_name = findViewById(R.id.name);
        edt_mail = findViewById(R.id.email);
        edt_num = findViewById(R.id.mobile);
        /*edt_year=findViewById(R.id.year);*/
        edt_usr = findViewById(R.id.username);
        edt_password = findViewById(R.id.password);
        gender=findViewById(R.id.radiogroup);
        edt_code=findViewById(R.id.securitycode);
        btn_register = findViewById(R.id.register_button);
        mySpinner = (Spinner)findViewById(R.id.spinner_id);
        register_link=findViewById(R.id.register_link);;
        edt_regno=findViewById(R.id.registerno);
        /*edt_name.setRawInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_FLAG_CAP_WORDS);*/

        getSupportActionBar().hide();
        register_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(regi.this,login.class);
                startActivity(i);
            }
        });

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                str_reg_tpe = parent.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int in_gender = gender.getCheckedRadioButtonId();

                rd_Gender = (RadioButton)findViewById(in_gender);


                str_gender = rd_Gender.getText().toString();

                str_name=edt_name.getEditText().getText().toString().trim();
                str_mail=edt_mail.getEditText().getText().toString().trim();
                str_num=edt_num.getEditText().getText().toString().trim();
                /*str_year=edt_year.getText().toString().trim();*/
                str_reg= edt_regno.getEditText().getText().toString().trim();
                str_usr=edt_usr.getEditText().getText().toString().trim();
                str_pswd=edt_password.getEditText().getText().toString().trim();
                str_code_entered=edt_code.getText().toString().trim();

                if (str_code_entered.equals(supplied_teacher_code))
                {
                    flag="1";
                }

                if (str_name.equals(""))
                {
                    Toast.makeText(regi.this, "Name is required", Toast.LENGTH_SHORT).show();
                }
                else if (str_mail.equals(""))
                {
                    Toast.makeText(regi.this, "Email is required", Toast.LENGTH_SHORT).show();
                }
                else if (str_num.equals(""))
                {
                    Toast.makeText(regi.this, "Phone number is required", Toast.LENGTH_SHORT).show();
                }
                else if (str_reg.equals(""))
                {
                    Toast.makeText(regi.this, "Reg no requied", Toast.LENGTH_SHORT).show();
                }
                else if (str_gender.equals(""))
                {
                    Toast.makeText(regi.this, "Gender requied", Toast.LENGTH_SHORT).show();
                }
                else if (str_usr.equals(""))
                {
                    Toast.makeText(regi.this, "Username is requied", Toast.LENGTH_SHORT).show();
                }
                else if (str_pswd.equals(""))
                {
                    Toast.makeText(regi.this, "Password is requied", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    new RegistrationTask().execute();
                }

            }
        });


    }

    private class RegistrationTask extends AsyncTask<String,String, JSONObject>
    {


        @Override
        protected JSONObject doInBackground(String... strings)
        {
            JSONParser parser = new JSONParser();
            Map<String,String> hashmap = new HashMap<>();
            hashmap.put("name", str_name);
            hashmap.put("email_id", str_mail);
            hashmap.put("gender", str_gender);
            hashmap.put("mobile_num", str_num);
            hashmap.put("register_no", str_reg);
            hashmap.put("username", str_usr);
            hashmap.put("password",str_pswd);
            hashmap.put("flag",flag);
            hashmap.put("regtype", str_reg_tpe);

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

                    finish();

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
}