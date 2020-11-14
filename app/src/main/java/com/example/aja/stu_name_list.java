package com.example.aja;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class stu_name_list extends AppCompatActivity {
    String  RegUrl,BaseUrl;
    String group_id,str_user_id="0";

    ArrayList<group_list_model> list=new ArrayList<group_list_model>();
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_name_list);

        ActionBar newbar=getSupportActionBar();
        assert newbar != null;
        newbar.setTitle("Attendance");
        BaseUrl=getResources().getString(R.string.Base_URL);
        RegUrl=BaseUrl+"attendance_name.php";//of api

        lv=(ListView)findViewById(R.id.leave_list);

        new list_name().execute();
    }

    class list_name extends AsyncTask<String,String, JSONObject> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        public void onPostExecute(JSONObject jsonObject) {
            try {
                int success = jsonObject.getInt("success");

                if(success==1)
                {

                    JSONArray item_json = jsonObject.getJSONArray("result");//of api
                    for (int i = 0; i < item_json.length(); i++)
                    {
                        JSONObject listJsonObj =new JSONObject();
                        group_list_model item=new group_list_model();
                        listJsonObj= item_json.getJSONObject(i);

                        String new_group_id=listJsonObj.getString("st_id");
                        String group_name= listJsonObj.getString("name");

                        item.setGroup_id(new_group_id);
                        item.setGroup_name(group_name);


                        list.add(item);
                    }
                }
                else
                {
                    Log.e("ERROR","API ERROR");
                }
            }
            catch(JSONException e)
            {
                e.printStackTrace();
            }
            group_list_adapter adapter=new group_list_adapter(stu_name_list.this,list);
            lv.setAdapter(adapter);
        }

        @Override
        protected JSONObject doInBackground(String... strings)
        {
            JSONParser parser=new JSONParser();


            JSONObject jsonObject=parser.makeHttpRequest(RegUrl,"POST",null);

            return jsonObject;
        }
    }

}

