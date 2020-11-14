package com.example.aja;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class mark_list_adapter extends BaseAdapter {
    private Context activity;
    private LayoutInflater inflater;
    String id;

    private final List<mark_list_model> event_list;

    public mark_list_adapter(Context activity, List<mark_list_model> event_list)
    {
        this.activity=activity;
        this.event_list= event_list;
    }

    @Override
    public int getCount() {
        return event_list.size();
    }

    @Override
    public mark_list_model getItem(int position) {
        return event_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)// objects are used further
    {
        mark_list_adapter.ViewHolderDoc vHolder;
        View view_row=null;
        if(convertView==null)
        {
            inflater= ((Activity)activity).getLayoutInflater();
            view_row=inflater.inflate(R.layout.mark_layout,parent,false);
        }
        else
        {
            view_row=convertView;//object of viewholder
        }

        vHolder=new mark_list_adapter.ViewHolderDoc();
        vHolder.stu_id=(TextView)view_row.findViewById(R.id.mark_id);// var of list layout model
        vHolder.name=(TextView)view_row.findViewById(R.id.mark_name);
        vHolder.roleid=(TextView)view_row.findViewById(R.id.mark);
        final mark_list_model dlist= event_list.get(position);
        vHolder.stu_id.setText(""+dlist.getStu_id());//variables of event model
        vHolder.name.setText(""+dlist.getName());//variables of event model
        vHolder.roleid.setText(""+dlist.getRoleid());//variables of event model

        return view_row;
    }
    private class ViewHolderDoc
    {
        TextView stu_id,name,roleid;
    }
}

