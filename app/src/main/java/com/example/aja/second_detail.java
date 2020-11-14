package com.example.aja;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class second_detail extends AppCompatActivity {
    String  RegUrl,BaseUrl;
    String group_id,str_user_id;

    ArrayList<attend_list_model> list=new ArrayList<attend_list_model>();
    ListView lv;
    attend_list_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_detail);

        ActionBar newbar=getSupportActionBar();
        assert newbar != null;
        newbar.setTitle("First Year");

        BaseUrl=getResources().getString(R.string.Base_URL);
        RegUrl=BaseUrl+"sec_attendance_name.php";//of api

        lv=(ListView)findViewById(R.id.att_name_list);

        new list_name().execute();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                str_user_id = adapter.getItem(position).getAtt_id();
                //parse the id through intent to the percentage marking page
                //get the id from intent on that activity
                Log.e("ID SELECTED:", str_user_id);

                Intent newIn =new Intent(second_detail.this,tea_second_attendance.class);
                newIn.putExtra("stud_id", str_user_id);
                startActivity(newIn);
            }
        });
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
                        attend_list_model item=new attend_list_model();
                        listJsonObj= item_json.getJSONObject(i);

                        String att_id=listJsonObj.getString("att_id");
                        String name= listJsonObj.getString("name");

                        item.setAtt_id(att_id);
                        item.setName(name);

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
            adapter=new attend_list_adapter(second_detail.this,list);
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


