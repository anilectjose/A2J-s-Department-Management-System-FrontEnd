package com.example.aja;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class forgot_password extends AppCompatActivity {
    TextInputLayout emailid;
    Button forget;
    String strmail,BaseUrl= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        getSupportActionBar().hide();

        BaseUrl = getResources().getString(R.string.Base_URL)+"forgot_password.php";
        emailid=(TextInputLayout)findViewById(R.id.fmail);
        forget=findViewById(R.id.forget);

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strmail=emailid.getEditText().getText().toString().trim();

                if (strmail.equals(""))
                {
                    Toast.makeText(forgot_password.this,"Enter email",Toast.LENGTH_LONG).show();
                }
                else
                {
                    new fpassword().execute();

                }
            }
        });
    }
    private class fpassword extends AsyncTask<String,String, JSONObject>
    {


        @Override
        protected JSONObject doInBackground(String... strings)
        {
            JSONParser parser = new JSONParser();
            Map<String,String> hashmap = new HashMap<>();
            hashmap.put("email", strmail);

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

