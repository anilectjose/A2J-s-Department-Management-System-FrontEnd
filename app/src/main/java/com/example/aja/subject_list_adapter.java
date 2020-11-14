package com.example.aja;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class subject_list_adapter extends BaseAdapter {
    private Context activity;
    public static ArrayList<String> arr_marks;
    private LayoutInflater inflater;
    SharedPreferences shp;
    String str_role;

    private final List<subject_list_model> event_list;

    public subject_list_adapter(Context activity, List<subject_list_model> event_list)
    {
        this.activity=activity;
        this.event_list= event_list;
        arr_marks = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return event_list.size();
    }

    @Override
    public subject_list_model getItem(int position) {
        return event_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)// objects are used further
    {
        subject_list_adapter.ViewHolderDoc vHolder;
        View view_row=null;
        if(convertView==null)
        {
            inflater= ((Activity)activity).getLayoutInflater();
            view_row=inflater.inflate(R.layout.mark_sub_list,parent,false);
        }
        else
        {
            view_row=convertView;//object of viewholder
        }

        vHolder=new ViewHolderDoc();
        vHolder.sub_id=(TextView)view_row.findViewById(R.id.subj_id);// var of list layout model
        vHolder.name=(TextView)view_row.findViewById(R.id.subj);
        vHolder.mark=(EditText)view_row.findViewById(R.id.add_mark);
        final subject_list_model dlist= event_list.get(position);
        vHolder.sub_id.setText(""+dlist.getSub_id());//variables of event model
        vHolder.name.setText(""+dlist.getSubject());//variables of event model
        vHolder.mark.setText(""+dlist.getMark());//variables of event model

        shp = activity.getSharedPreferences("login_daa", MODE_PRIVATE);
        str_role = shp.getString("user_role", "");
        if (str_role.equals("student")){
            vHolder.mark.setFocusable(false);
        }


        /* String strMarks = vHolder.mark.getText().toString();*/

       /* arr_marks.set(position,"10");*/

        return view_row;
    }
    private class ViewHolderDoc
    {
        TextView sub_id,name,mark;
    }
}
