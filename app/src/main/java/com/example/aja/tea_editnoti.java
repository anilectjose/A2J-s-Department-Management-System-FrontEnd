package com.example.aja;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
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

public class tea_editnoti extends AppCompatActivity {

    EditText edt_content;
    String str_con;
    Button btn_noti;
    String BaseURL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea_editnoti);

        ActionBar newbar = getSupportActionBar();
        assert newbar != null;
        newbar.setTitle("Edit Notification");
        BaseURL = getResources().getString(R.string.Base_URL)+"tea_editnoti.php";

        edt_content=findViewById(R.id.content);
        btn_noti=findViewById(R.id.noti_btn);
        edt_content.setRawInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);

        btn_noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_con=edt_content.getText().toString().trim();


                if (str_con.equals(""))
                {
                    Toast.makeText(tea_editnoti.this, "Content is required", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    new notiTask().execute();
                }

            }
        });
    }

    private class notiTask extends AsyncTask<String,String, JSONObject>
    {


        @Override
        protected JSONObject doInBackground(String... strings)
        {
            JSONParser parser = new JSONParser();
            Map<String,String> hashmap = new HashMap<>();
            hashmap.put("content", str_con);

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
