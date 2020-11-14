package com.example.aja;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class tea_third_attendance extends AppCompatActivity {
    EditText ejan,efeb,emar,ejun,ejul,eaug,esep,eoct,enov,edec,etot;
    String sjan,sfeb,smar,sjun,sjul,saug,ssep,soct,snov,sdec,stot;
    Button btn_attend;
    String BaseURL = "",RegUrl;
    String str_stud_id;
    String str_mentor_cls;
    SharedPreferences shp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea_third_attendance);
        ActionBar newbar=getSupportActionBar();
        assert newbar != null;
        newbar.setTitle("Edit Attendance");
        shp = getSharedPreferences("login_daa", MODE_PRIVATE);
        str_mentor_cls = shp.getString("mentor_cls", "");

        Intent in = getIntent();
        str_stud_id = in.getStringExtra("stud_id");

        BaseURL = getResources().getString(R.string.Base_URL)+"third_attendance_submit.php";
        RegUrl = getResources().getString(R.string.Base_URL)+"get_percentage.php";
        ejan=findViewById(R.id.jan);
        efeb=findViewById(R.id.feb);
        emar=findViewById(R.id.mar);
        ejun=findViewById(R.id.jun);
        ejul=findViewById(R.id.jul);
        eaug=findViewById(R.id.aug);
        esep=findViewById(R.id.sep);
        eoct=findViewById(R.id.oct);
        enov=findViewById(R.id.nov);
        edec=findViewById(R.id.dec);
        etot=findViewById(R.id.tot);
        btn_attend=findViewById(R.id.submit_attend);

        if (str_mentor_cls.equals("1")||str_mentor_cls.equals("2")||str_mentor_cls.equals("")){
            Toast.makeText(this, "Only mentor can edit..", Toast.LENGTH_SHORT).show();
            ejan.setFocusable(false);
            efeb.setFocusable(false);
            emar.setFocusable(false);
            ejun.setFocusable(false);
            ejul.setFocusable(false);
            eaug.setFocusable(false);
            esep.setFocusable(false);
            eoct.setFocusable(false);
            enov.setFocusable(false);
            edec.setFocusable(false);
            etot.setFocusable(false);
            btn_attend.setVisibility(View.GONE);
        }

        new GetPercentageResult().execute();

        btn_attend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stot=etot.getText().toString().trim();
                sjan=ejan.getText().toString().trim();
                sfeb=efeb.getText().toString().trim();
                smar=emar.getText().toString().trim();
                sjun=ejun.getText().toString().trim();
                sjul =ejul.getText().toString().trim();
                saug=eaug.getText().toString().trim();
                ssep=esep.getText().toString().trim();
                soct=eoct.getText().toString().trim();
                snov=enov.getText().toString().trim();
                sdec=edec.getText().toString().trim();

                if (stot.equals(""))
                {
                    Toast.makeText(tea_third_attendance.this, "Total percentage is required", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    new attendTask().execute();
                }

            }
        });
    }
    private class GetPercentageResult extends AsyncTask<String,String, JSONObject>
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


                    String var1= jsonObject.getJSONObject("result").getString("jan");
                    String var2= jsonObject.getJSONObject("result").getString("feb");
                    String var3= jsonObject.getJSONObject("result").getString("mar");
                    String var4= jsonObject.getJSONObject("result").getString("jun");
                    String var5= jsonObject.getJSONObject("result").getString("jul");
                    String var6= jsonObject.getJSONObject("result").getString("aug");
                    String var7= jsonObject.getJSONObject("result").getString("sep");
                    String var8= jsonObject.getJSONObject("result").getString("oct");
                    String var9= jsonObject.getJSONObject("result").getString("nov");
                    String var0= jsonObject.getJSONObject("result").getString("dece");

                    ejan.setText(var1);
                    efeb.setText(var2);
                    emar.setText(var3);
                    ejun.setText(var4);
                    ejul.setText(var5);
                    eaug.setText(var6);
                    esep.setText(var7);
                    eoct.setText(var8);
                    enov.setText(var9);
                    edec.setText(var0);

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
            hashmap.put("atid", str_stud_id);

            JSONObject jsonObject=parser.makeHttpRequest(RegUrl,"POST", hashmap);

            return jsonObject;
        }
    }
    private class attendTask extends AsyncTask<String,String, JSONObject>
    {


        @Override
        protected JSONObject doInBackground(String... strings)
        {
            JSONParser parser = new JSONParser();
            Map<String,String> hashmap = new HashMap<>();
            hashmap.put("atid", str_stud_id);
            hashmap.put("jan", sjan);
            hashmap.put("feb", sfeb);
            hashmap.put("mar", smar);
            hashmap.put("jun", sjun);
            hashmap.put("jul", sjul);
            hashmap.put("aug",saug);
            hashmap.put("sep",ssep);
            hashmap.put("oct",soct);
            hashmap.put("nov",snov);
            hashmap.put("dec",sdec);
            hashmap.put("tot",stot);

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
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
    }
}
