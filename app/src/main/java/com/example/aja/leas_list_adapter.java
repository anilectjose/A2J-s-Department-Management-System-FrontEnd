package com.example.aja;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class leas_list_adapter extends BaseAdapter{
    private Context activity;
    private LayoutInflater inflater;
    String ApproveURL,RejectUrl;
    String str_mentor_cls;
    SharedPreferences shp;

    private final List<leas_list_model> event_list;

    public leas_list_adapter(Context activity, List<leas_list_model> event_list)
    {
        this.activity=activity;
        this.event_list= event_list;
        ApproveURL = activity.getResources().getString(R.string.Base_URL)+"leave_approve.php";
        RejectUrl = activity.getResources().getString(R.string.Base_URL)+"leave_reject.php";

    }

    @Override
    public int getCount() {
        return event_list.size();
    }

    @Override
    public leas_list_model getItem(int position) {
        return event_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)// objects are used further
    {
        leas_list_adapter.ViewHolderDoc vHolder;
        View view_row=null;
        if(convertView==null)
        {
            inflater= ((Activity)activity).getLayoutInflater();
            view_row=inflater.inflate(R.layout.stu_leave_layout,parent,false);
        }
        else
        {
            view_row=convertView;//object of viewholder
        }

        vHolder=new leas_list_adapter.ViewHolderDoc();
        vHolder.lea_id=(TextView)view_row.findViewById(R.id.lea_id);// var of list layout model
        vHolder.name=(TextView)view_row.findViewById(R.id.lea_name);
        vHolder.reg=(TextView)view_row.findViewById(R.id.lea_reg);
        vHolder.date=(TextView)view_row.findViewById(R.id.lea_date);
        vHolder.days=(TextView)view_row.findViewById(R.id.lea_days);
        vHolder.year=(TextView)view_row.findViewById(R.id.lea_year);
        vHolder.res=(TextView)view_row.findViewById(R.id.lea_reason);
        vHolder.approve_btn=(Button)view_row.findViewById(R.id.btn_approve);
        vHolder.reject_btn=(ImageView)view_row.findViewById(R.id.reje);
        final leas_list_model dlist= event_list.get(position);
        vHolder.lea_id.setText(""+dlist.getLea_id());//variables of event model
        vHolder.name.setText(""+dlist.getName());//variables of event model
        vHolder.reg.setText(""+dlist.getReg());//variables of event model
        vHolder.date.setText(""+dlist.getDate());//variables of event model
        vHolder.days.setText(""+dlist.getDays());//variables of event model
        vHolder.year.setText(""+dlist.getYear());//variables of event model
        vHolder.res.setText(""+dlist.getRes());//variables of event model

        shp = activity.getSharedPreferences("login_daa", MODE_PRIVATE);
        str_mentor_cls = shp.getString("mentor_cls", "");

        if (!str_mentor_cls.equals(""+dlist.getYear())){

            vHolder.approve_btn.setVisibility(View.GONE);
            vHolder.reject_btn.setVisibility(View.GONE);
        }

        if(dlist.getResult().equals("Approved"))
        {
            vHolder.approve_btn.setVisibility(View.GONE);
        }

        vHolder.approve_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String leaveID = dlist.getLea_id(); //just read the text & identify the function

                new ApprovalTask().execute(leaveID);
            }
        });
        vHolder.reject_btn.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                String leaveID = dlist.getLea_id(); //just read the text & identify the function

                new RejectTask().execute(leaveID);
            }
        });

        return view_row;
    }
    private class RejectTask extends AsyncTask<String,String, JSONObject>{
        @Override
        protected JSONObject doInBackground(String... strings) {
            JSONParser parser = new JSONParser();
            String lea_id=strings[0];
            Map<String,String> params=new HashMap<>();
            params.put("leaid",lea_id);
            JSONObject jsonob=parser.makeHttpRequest(RejectUrl,"POST",params);
            return jsonob;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            try {
                int success = jsonObject.getInt("success");
                String str_data = jsonObject.getString("message");

                if (success == 1)
                {
                    Toast.makeText(activity,str_data,Toast.LENGTH_LONG).show();
                    ((tea_leaveofstud)activity).finish();
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
    }
    private class ApprovalTask extends AsyncTask<String,String, JSONObject>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            try {

                int success = jsonObject.getInt("success");

                String str_data = jsonObject.getString("message");

                if (success == 1) {

                    Toast.makeText(activity,str_data,Toast.LENGTH_LONG).show();
                    ((tea_leaveofstud)activity).finish();

                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }

        @Override
        protected JSONObject doInBackground(String... strings) {
            JSONParser parser = new JSONParser();
            String lea_id=strings[0];
            Map<String,String> params=new HashMap<>();
            params.put("leaid",lea_id);
            JSONObject jsonob=parser.makeHttpRequest(ApproveURL,"POST",params);
            return jsonob;
        }
    }
    private class ViewHolderDoc
    {
        TextView lea_id,name,reg,res,date,year,days;
        Button approve_btn;
        ImageView reject_btn;
    }
}

