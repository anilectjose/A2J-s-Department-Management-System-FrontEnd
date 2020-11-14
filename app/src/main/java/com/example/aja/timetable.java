package com.example.aja;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class timetable extends AppCompatActivity {
    TextView m11,m12,m13,m14,m15;
    TextView t21,t22,t23,t24,t25;
    TextView w31,w32,w33,w34,w35;
    TextView t41,t42,t43,t44,t45;
    TextView f51,f52,f53,f54,f55;
    String BaseURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        ActionBar newbar=getSupportActionBar();
        assert newbar != null;
        newbar.setTitle("Time Table");

        BaseURL = getResources().getString(R.string.Base_URL) + "get_timetable.php";
        m11 = findViewById(R.id.t11);
        m12 = findViewById(R.id.t12);
        m13 = findViewById(R.id.t13);
        m14 = findViewById(R.id.t14);
        m15 = findViewById(R.id.t15);
        t21 = findViewById(R.id.t21);
        t22 = findViewById(R.id.t22);
        t23 = findViewById(R.id.t23);
        t24 = findViewById(R.id.t24);
        t25 = findViewById(R.id.t25);
        w31 = findViewById(R.id.t31);
        w32 = findViewById(R.id.t32);
        w33 = findViewById(R.id.t33);
        w34 = findViewById(R.id.t34);
        w35 = findViewById(R.id.t35);
        t41 = findViewById(R.id.t41);
        t42 = findViewById(R.id.t42);
        t43 = findViewById(R.id.t43);
        t44 = findViewById(R.id.t44);
        t45 = findViewById(R.id.t45);
        f51 = findViewById(R.id.t51);
        f52 = findViewById(R.id.t52);
        f53 = findViewById(R.id.t53);
        f54 = findViewById(R.id.t54);
        f55 = findViewById(R.id.t55);

        new GetTimetable().execute();

    }

    private class GetTimetable extends AsyncTask<String, String, JSONObject> {
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

                    String var1 = jsonObject.getJSONObject("result").getString("monday");
                    String var2 = jsonObject.getJSONObject("result").getString("monday");
                    String var3 = jsonObject.getJSONObject("result").getString("monday");
                    String var4 = jsonObject.getJSONObject("result").getString("monday");
                    String var5 = jsonObject.getJSONObject("result").getString("monday");

                    String var6 = jsonObject.getJSONObject("result").getString("tuesday");
                    String var7 = jsonObject.getJSONObject("result").getString("tuesday");
                    String var8 = jsonObject.getJSONObject("result").getString("tuesday");
                    String var9 = jsonObject.getJSONObject("result").getString("tuesday");
                    String var10 = jsonObject.getJSONObject("result").getString("tuesday");

                    String var11 = jsonObject.getJSONObject("result").getString("wednesday");
                    String var12 = jsonObject.getJSONObject("result").getString("wednesday");
                    String var13 = jsonObject.getJSONObject("result").getString("wednesday");
                    String var14 = jsonObject.getJSONObject("result").getString("wednesday");
                    String var15 = jsonObject.getJSONObject("result").getString("wednesday");

                    String var16 = jsonObject.getJSONObject("result").getString("thursday");
                    String var17 = jsonObject.getJSONObject("result").getString("thursday");
                    String var18 = jsonObject.getJSONObject("result").getString("thursday");
                    String var19 = jsonObject.getJSONObject("result").getString("thursday");
                    String var20 = jsonObject.getJSONObject("result").getString("thursday");

                    String var21 = jsonObject.getJSONObject("result").getString("friday");
                    String var22 = jsonObject.getJSONObject("result").getString("friday");
                    String var23 = jsonObject.getJSONObject("result").getString("friday");
                    String var24 = jsonObject.getJSONObject("result").getString("friday");
                    String var25 = jsonObject.getJSONObject("result").getString("friday");

                    m11.setText(var1);
                    m12.setText(var2);
                    m13.setText(var3);
                    m14.setText(var4);
                    m15.setText(var5);
                    t21.setText(var6);
                    t22.setText(var7);
                    t23.setText(var8);
                    t24.setText(var9);
                    t25.setText(var10);

                    w31.setText(var11);
                    w32.setText(var12);
                    w33.setText(var13);
                    w34.setText(var14);
                    w35.setText(var15);
                    t41.setText(var16);
                    t42.setText(var17);
                    t43.setText(var18);
                    t44.setText(var19);
                    t45.setText(var20);

                    f51.setText(var21);
                    f52.setText(var22);
                    f53.setText(var23);
                    f54.setText(var24);
                    f55.setText(var25);

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
            JSONObject jsonObject = parser.makeHttpRequest(BaseURL, "POST", null);

            return jsonObject;
        }
    }
}

