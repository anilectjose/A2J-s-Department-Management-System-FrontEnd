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

public class tea_notification extends AppCompatActivity {
    String  RegUrl,BaseUrl;
    String group_id,str_user_id="0";
    int number=1;

    ArrayList<noti_list_model> list=new ArrayList<noti_list_model>();
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea_notification);
        ActionBar newbar=getSupportActionBar();
        assert newbar != null;
        newbar.setTitle("Notification");

        BaseUrl=getResources().getString(R.string.Base_URL);
        RegUrl=BaseUrl+"notific_teacher.php";//of api

        lv=(ListView)findViewById(R.id.notifiy_list_view);

        new list_leave().execute();
    }

    class list_leave extends AsyncTask<String,String, JSONObject> {


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
                        noti_list_model item=new noti_list_model();
                        listJsonObj= item_json.getJSONObject(i);

                        String noti_id=listJsonObj.getString("notification_id");
                        String name= listJsonObj.getString("content");
                        String slno= String.valueOf(i);

                        item.setNoti_id(noti_id);
                        item.setName(name);
                        item.setSlno(slno);

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
            noti_list_adapter adapter=new noti_list_adapter(tea_notification.this,list);
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

