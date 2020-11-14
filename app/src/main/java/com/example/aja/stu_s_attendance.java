package com.example.aja;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class stu_s_attendance extends AppCompatActivity {
    TextView ejan, efeb, emar, ejun, ejul, eaug, esep, eoct, enov, edec, etot,ename;
    String BaseURL;
    String str_stud_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_s_attendance);

        ActionBar newbar = getSupportActionBar();
        assert newbar != null;
        newbar.setTitle("Attendance");

        Intent in = getIntent();
        str_stud_id = in.getStringExtra("stud_id");

        BaseURL = getResources().getString(R.string.Base_URL) + "get_percentage.php";
        ename = findViewById(R.id.name);
        ejan = findViewById(R.id.jan);
        efeb = findViewById(R.id.feb);
        emar = findViewById(R.id.mar);
        ejun = findViewById(R.id.jun);
        ejul = findViewById(R.id.jul);
        eaug = findViewById(R.id.aug);
        esep = findViewById(R.id.sep);
        eoct = findViewById(R.id.oct);
        enov = findViewById(R.id.nov);
        edec = findViewById(R.id.dec);
        etot = findViewById(R.id.tot);

        new GetPercentageResult().execute();

    }

    private class GetPercentageResult extends AsyncTask<String, String, JSONObject> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            try {
                int success = jsonObject.getInt("success");
                Log.e("RESPONSE", "" + success);
                if (success == 1) {

                    String varn = jsonObject.getJSONObject("result").getString("name");
                    String var1 = jsonObject.getJSONObject("result").getString("jan");
                    String var2 = jsonObject.getJSONObject("result").getString("feb");
                    String var3 = jsonObject.getJSONObject("result").getString("mar");
                    String var4 = jsonObject.getJSONObject("result").getString("jun");
                    String var5 = jsonObject.getJSONObject("result").getString("jul");
                    String var6 = jsonObject.getJSONObject("result").getString("aug");
                    String var7 = jsonObject.getJSONObject("result").getString("sep");
                    String var8 = jsonObject.getJSONObject("result").getString("oct");
                    String var9 = jsonObject.getJSONObject("result").getString("nov");
                    String var0 = jsonObject.getJSONObject("result").getString("dece");
                    String vart = jsonObject.getJSONObject("result").getString("total");

                    ename.setText(varn);
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
                    etot.setText(vart);

                } else {
                    Log.e("ERROR", "API ERROR");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected JSONObject doInBackground(String... strings) {
            JSONParser parser = new JSONParser();
            Map<String, String> hashmap = new HashMap<>();
            hashmap.put("atid", str_stud_id);

            JSONObject jsonObject = parser.makeHttpRequest(BaseURL, "POST", hashmap);

            return jsonObject;
        }
    }
}


