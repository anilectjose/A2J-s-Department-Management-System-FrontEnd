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

public class stu_t_markname extends AppCompatActivity {
    String BaseURL = "",RegUrl;
    String str_user_id,str_name,srole;

    ArrayList<mark_list_model> list=new ArrayList<mark_list_model>();
    ListView lv;
    mark_list_adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_t_markname);

        ActionBar newbar=getSupportActionBar();
        assert newbar != null;
        newbar.setTitle("Intenal Marks");

        lv=(ListView)findViewById(R.id.mark_name_list);

        RegUrl = getResources().getString(R.string.Base_URL)+"third_mark.php";

        new list_name().execute();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                str_user_id = adapter.getItem(position).getStu_id();
                str_name = adapter.getItem(position).getName();
                srole = adapter.getItem(position).getRoleid();
                //parse the id through intent to the percentage marking page
                //get the id from intent on that activity
                Log.e("ID SELECTED:", str_user_id);
                Log.e("NAME SELECTED:", str_name);
                Log.e("ROLE SELECTED:", srole);

                Intent newIn =new Intent(stu_t_markname.this,tea_f_markadd.class);
                newIn.putExtra("stud_id", str_user_id);
                newIn.putExtra("year_value","3");
                newIn.putExtra("stud_name", str_name);
                newIn.putExtra("stud_role", srole);
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
                        mark_list_model item=new mark_list_model();
                        listJsonObj= item_json.getJSONObject(i);

                        String st_id=listJsonObj.getString("st_id");
                        String name= listJsonObj.getString("name");
                        String mark= listJsonObj.getString("role_id");

                        item.setStu_id(st_id);
                        item.setName(name);
                        item.setRoleid(mark);

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
            adapter=new mark_list_adapter(stu_t_markname.this,list);
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




