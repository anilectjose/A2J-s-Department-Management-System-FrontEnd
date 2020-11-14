package com.example.aja;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toolbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class groups extends AppCompatActivity {
    Toolbar toolbar;
    SharedPreferences sharedPreferences;

    String  RegUrl,BaseUrl;
    String group_id,str_user_id="0";

    ArrayList<group_list_model> list=new ArrayList<group_list_model>();

    /*SharedPreferences sp;
    EditText cmt;*/

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);

        /*Intent in =getIntent();
        str_user_id = in.getStringExtra("key_name_to_store");//event id from login.java*/

        BaseUrl=getResources().getString(R.string.Base_URL);
        RegUrl=BaseUrl+"group_students.php";//of api

        lv=(ListView)findViewById(R.id.group_list_id1);//id of list view in xml

        sharedPreferences=getSharedPreferences("user_id",MODE_PRIVATE);
        toolbar=findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        ActionBar newbar=getSupportActionBar();
        assert newbar != null;
        newbar.setTitle("Students");


        new list_groups().execute();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                group_id = ""+list.get(position).getGroup_id();

                Log.e("GROUP ID",group_id);
                Intent intent=new Intent(getApplicationContext(),splash_screen.class);
                intent.putExtra("key_student_id", group_id);
                startActivity(intent);

            }
        });
    }


    class list_groups extends AsyncTask<String,String, JSONObject> {


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
            group_list_adapter adapter=new group_list_adapter(groups.this,list);
            lv.setAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {
                    group_id = ""+list.get(position).getGroup_id();

                    Log.e("GROUP ID",group_id);
                    Intent intent=new Intent(getApplicationContext(),splash_screen.class);
                    intent.putExtra("key_name_to_store", group_id);
                    startActivity(intent);

                }
            });
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
