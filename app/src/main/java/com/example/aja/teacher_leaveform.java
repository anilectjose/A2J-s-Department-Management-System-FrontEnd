package com.example.aja;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class teacher_leaveform extends AppCompatActivity {
    EditText edt_name,edt_reg,edt_date,edt_days,edt_reason;
    String str_name,str_reg,str_date,str_days,str_reason;
    Button btn_leave;
    String BaseURL = "";
    TextView result;

    SharedPreferences shp;
    String str_role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_leaveform);

        ActionBar newbar=getSupportActionBar();
        assert newbar != null;
        newbar.setTitle("Leave Form");

        shp = getSharedPreferences("login_daa", MODE_PRIVATE);
        str_role = shp.getString("user_role", "");

        BaseURL = getResources().getString(R.string.Base_URL)+"teacher_leaveform.php";

        edt_name=findViewById(R.id.name);
        edt_reg=findViewById(R.id.regno);
        edt_date=findViewById(R.id.date);
        edt_days=findViewById(R.id.days);
        edt_reason=findViewById(R.id.reason);
        btn_leave=findViewById(R.id.leave_btn);
        result=findViewById(R.id.link);
        edt_name.setRawInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        edt_reason.setRawInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), tea_leave_result.class);
                startActivity(i);
            }
        });

        btn_leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_name=edt_name.getText().toString().trim();
                str_reg= edt_reg.getText().toString().trim();
                str_date=edt_date.getText().toString().trim();
                str_days=edt_days.getText().toString().trim();
                str_reason=edt_reason.getText().toString().trim();

                if (str_name.equals(""))
                {
                    Toast.makeText(teacher_leaveform.this, "Name is required", Toast.LENGTH_SHORT).show();
                }
                else if (str_reg.equals(""))
                {
                    Toast.makeText(teacher_leaveform.this, "Reg no requied", Toast.LENGTH_SHORT).show();
                }
                else if (str_date.equals(""))
                {
                    Toast.makeText(teacher_leaveform.this, "Date is requied", Toast.LENGTH_SHORT).show();
                }
                else if(str_days.equals(""))
                {
                    Toast.makeText(teacher_leaveform.this, "Days requied", Toast.LENGTH_SHORT).show();
                }
                else if (str_reason.equals(""))
                {
                    Toast.makeText(teacher_leaveform.this, "Reason is requied", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    new leaveTask().execute();
                }

            }
        });
    }

    private class leaveTask extends AsyncTask<String,String, JSONObject>
    {


        @Override
        protected JSONObject doInBackground(String... strings)
        {
            JSONParser parser = new JSONParser();
            Map<String,String> hashmap = new HashMap<>();
            hashmap.put("name", str_name);
            hashmap.put("regno", str_reg);
            hashmap.put("date", str_date);
            hashmap.put("days", str_days);
            hashmap.put("reason",str_reason);
            JSONObject jObj = parser.makeHttpRequest(BaseURL,"POST", hashmap);

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

            }
        }
    }
}
