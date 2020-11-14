package com.example.aja;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class tea_f_markadd extends AppCompatActivity {
    TextView tname,tsub,subid;
    EditText emark;
    String smark;
    Button btn_mark;
    String BaseURL = "",RegUrl;

    String str_stud_id,str_sub_id,str_name,str_year;
    String str_mentor_cls;
    SharedPreferences shp;

    ArrayList<subject_list_model> list=new ArrayList<subject_list_model>();
    ArrayList<subject_list_model> list2=new ArrayList<subject_list_model>();
    ListView lv,lv_2;
    subject_list_adapter adapter,adapter_2;

    String strMarks;
    String strSubIds;

    String strMarks2;
    String strSubIds2,str_role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea_f_markadd);

        Intent yInt= getIntent();
        str_year = yInt.getStringExtra("year_value");

        ActionBar newbar=getSupportActionBar();
        assert newbar != null;
        newbar.setTitle("Edit Mark");
        shp = getSharedPreferences("login_daa", MODE_PRIVATE);
        str_mentor_cls = shp.getString("mentor_cls", "");
        str_role = shp.getString("user_role", "");

        lv=(ListView)findViewById(R.id.mark_list);
        lv_2=(ListView)findViewById(R.id.sem2);

        Intent in = getIntent();
        str_stud_id = in.getStringExtra("stud_role");
        str_name = in.getStringExtra("stud_name");

        BaseURL = getResources().getString(R.string.Base_URL)+"mark_submit.php";
        RegUrl = getResources().getString(R.string.Base_URL)+"get_mark.php";
       tname=findViewById(R.id.mark_name);
       tname.setText(str_name);

        btn_mark=findViewById(R.id.mark_btn);

        if (str_mentor_cls.equals("3")||str_mentor_cls.equals("2")||str_mentor_cls.equals("")){
            if (str_role.equals("student")){
                btn_mark.setVisibility(View.GONE);
            }
            else
            {
              Toast.makeText(this, "Only mentor can edit..", Toast.LENGTH_SHORT).show();
              btn_mark.setVisibility(View.GONE);
            }
        }


        new GetMarkResult().execute();
        btn_mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i=0;i<list.size();i++)
                {
                    View view = lv.getChildAt(i);
                    EditText editText = view.findViewById(R.id.add_mark);
                    TextView txtSubId = view.findViewById(R.id.subj_id);
                    strMarks = editText.getText().toString();
                    strSubIds = txtSubId.getText().toString();

                    new markTask().execute(str_stud_id,strMarks,strSubIds);

                    /*Log.e("SUBID: ", strSubIds);
                    Log.e("MARKS: ", strMarks);*/

                }

                for(int j=0;j<list2.size();j++)
                {
                    View view2 = lv_2.getChildAt(j);
                    EditText editText2 = view2.findViewById(R.id.add_mark);
                    TextView txtSubId2 = view2.findViewById(R.id.subj_id);
                    strMarks2 = editText2.getText().toString();
                    strSubIds2 = txtSubId2.getText().toString();

                    new markTask().execute(str_stud_id,strMarks2,strSubIds2);

                }
            }
        });

    }
    private class GetMarkResult extends AsyncTask<String,String, JSONObject>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            try {
                int success = jsonObject.getInt("success");
                Log.e("RESPONSE",""+success);
                    if(success==1)
                    {

                        JSONArray item_json = jsonObject.getJSONArray("result");//of api
                        JSONArray item_json2 = jsonObject.getJSONArray("result_2");//of api
                        for (int i = 0; i < item_json.length(); i++) {
                            JSONObject listJsonObj = new JSONObject();
                            subject_list_model item = new subject_list_model();
                            listJsonObj = item_json.getJSONObject(i);

                            String var1=listJsonObj.getString("sub_id");
                            String var2= listJsonObj.getString("sub_name");
                            String var3= listJsonObj.getString("mark");

                            item.setSub_id(var1);
                            item.setSubject(var2);
                            item.setMark(var3);

                            list.add(item);

                        }
                        for (int i = 0; i < item_json2.length(); i++) {
                            JSONObject listJsonObj2 = new JSONObject();
                            subject_list_model item2 = new subject_list_model();
                            listJsonObj2 = item_json2.getJSONObject(i);

                            String var1=listJsonObj2.getString("sub_id");
                            String var2= listJsonObj2.getString("sub_name");
                            String var3= listJsonObj2.getString("mark");

                            item2.setSub_id(var1);
                            item2.setSubject(var2);
                            item2.setMark(var3);

                            list2.add(item2);

                        }
                }
                else
                {
                    Log.e("ERROR","API ERROR");
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
            adapter=new subject_list_adapter(tea_f_markadd.this,list);
            lv.setAdapter(adapter);
            adapter_2=new subject_list_adapter(tea_f_markadd.this,list2);
            lv_2.setAdapter(adapter_2);
        }
        @Override
        protected JSONObject doInBackground(String... strings)
        {
            JSONParser parser=new JSONParser();
            Map<String,String> hashmap = new HashMap<>();
            hashmap.put("stid", str_stud_id);
            hashmap.put("year", str_year);

            JSONObject jsonObject=parser.makeHttpRequest(RegUrl,"POST", hashmap);

            return jsonObject;
        }
    }
    private class markTask extends AsyncTask<String,String, JSONObject>
    {


        @Override
        protected JSONObject doInBackground(String... strings)
        {
            String st1 = strings[0];
            String m1 = strings[1];
            String sb1 = strings[2];


            JSONParser parser = new JSONParser();
            Map<String,String> hashmap = new HashMap<>();
            hashmap.put("stid", st1);
            hashmap.put("subid", sb1);
            hashmap.put("mark", m1);

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
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
    }
}

