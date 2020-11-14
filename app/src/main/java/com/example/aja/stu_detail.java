package com.example.aja;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class stu_detail extends AppCompatActivity {
    String  RegUrl,BaseUrl;
    String group_id,str_user_id="0";
    SearchView searchView;
    detail_list_adapter adapter;

    ArrayList<detail_list_model> list=new ArrayList<detail_list_model>();
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_detail);

        ActionBar newbar = getSupportActionBar();
        assert newbar != null;
        newbar.setTitle("Student Details");

        RegUrl = getResources().getString(R.string.Base_URL)+"detail_view.php";
        lv=(ListView)findViewById(R.id.detail_list);
        searchView = findViewById(R.id.search);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)) {
                    adapter.filter("");
                    lv.clearTextFilter();
                } else {
                    adapter.filter(s);
                }
                return true;
            }
        });
        new list_detail().execute();
    }

    class list_detail extends AsyncTask<String,String, JSONObject> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        public void onPostExecute(JSONObject jsonObject) {
            try {
                int success = jsonObject.getInt("success");

                if (success == 1) {

                    JSONArray item_json = jsonObject.getJSONArray("result");//of api
                    for (int i = 0; i < item_json.length(); i++) {
                        JSONObject listJsonObj = new JSONObject();
                        detail_list_model item = new detail_list_model();
                        listJsonObj = item_json.getJSONObject(i);

                        String detail_id = listJsonObj.getString("stu_id");
                        String name = listJsonObj.getString("nme");
                        String reg = listJsonObj.getString("register_no");
                        String year = listJsonObj.getString("year");
                        String mobi = listJsonObj.getString("mobile");
                        String mail = listJsonObj.getString("email");
                        String gender = listJsonObj.getString("gender");

                        item.setDetail_id(detail_id);
                        item.setName(name);
                        item.setReg(reg);
                        item.setYear(year);
                        item.setMob(mobi);
                        item.setMail(mail);
                        item.setGen(gender);

                        list.add(item);
                    }
                } else {
                    Log.e("ERROR", "API ERROR");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            adapter = new detail_list_adapter(stu_detail.this, list);
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

