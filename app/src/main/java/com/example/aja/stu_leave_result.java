package com.example.aja;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class stu_leave_result extends AppCompatActivity {
    String  RegUrl,BaseUrl;
    Button btn_leave;
    String str_user_id;

    ArrayList<leave_result_list> list=new ArrayList<leave_result_list>();
    ListView lv;
    leave_result_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_leave_result);

        ActionBar newbar=getSupportActionBar();
        assert newbar != null;
        newbar.setTitle("View Leave Result");

        BaseUrl=getResources().getString(R.string.Base_URL);
        RegUrl=BaseUrl+"leave_result.php";//of api

        lv=(ListView)findViewById(R.id.leave_result);

        /*lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                str_user_id = adapter.getItem(position).getLea_id();
                Log.e("ID SELECTED:", str_user_id);
                Toast.makeText(tea_leaveofstud.this, str_user_id, Toast.LENGTH_SHORT).show();
                Intent newIn =new Intent(tea_leaveofstud.this,tea_leaveofstud.class);
                newIn.putExtra("stud_id", str_user_id);
                startActivity(newIn);
            }
        });*/

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
                        leave_result_list item=new leave_result_list();
                        listJsonObj= item_json.getJSONObject(i);

                        String lea_id=listJsonObj.getString("leave_id");
                        String name= listJsonObj.getString("name");
                        String date= listJsonObj.getString("date");
                        String year= listJsonObj.getString("year");
                        String res= listJsonObj.getString("reason");
                        String reg= listJsonObj.getString("reg_no");
                        String days= listJsonObj.getString("no_of_days");
                        String result= listJsonObj.getString("result");
                        String rslt= listJsonObj.getString("t_result");

                        item.setLea_id(lea_id);
                        item.setName(name);
                        item.setDate(date);
                        item.setYear(year);
                        item.setRes(res);
                        item.setReg(reg);
                        item.setDays(days);
                        item.setResult(result);
                        item.setRslt(rslt);
                      /*  if (rslt.equals("Approved"))
                        {
                            if (result.equals(""))
                            {
                                item.setResult("Pending by HOD");
                            }
                            else
                            {
                                item.setResult(result);
                            }
                        }
                        else if (rslt.equals("Rejected"))
                        {
                            item.setResult("Rejected");
                        }
                        else
                        {
                            item.setResult("Pending by Mentor");
                        }*/

                        //create this method in setter class

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
            adapter=new leave_result_adapter(stu_leave_result.this,list);
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

