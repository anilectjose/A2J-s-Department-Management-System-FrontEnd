package com.example.aja;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class group_list_adapter extends BaseAdapter {
    private Context activity;
    private LayoutInflater inflater;

    private final List<group_list_model> event_list;

    public group_list_adapter(Context activity, List<group_list_model> event_list)
    {
        this.activity=activity;
        this.event_list= event_list;
    }

    @Override
    public int getCount() {
        return event_list.size();
    }

    @Override
    public group_list_model getItem(int position) {
        return event_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)// objects are used further
    {
        ViewHolderDoc vHolder;
        View view_row=null;
        if(convertView==null)
        {
            inflater= ((Activity)activity).getLayoutInflater();
            view_row=inflater.inflate(R.layout.group_list_layout,parent,false);
        }
        else
        {
            view_row=convertView;//object of viewholder
        }

        vHolder=new ViewHolderDoc();
        vHolder.group_id=(TextView)view_row.findViewById(R.id.group_list_id);// var of list layout model
        vHolder.group_name=(TextView)view_row.findViewById(R.id.group_title);
        final group_list_model dlist= event_list.get(position);
        vHolder.group_id.setText(""+dlist.getGroup_id());//variables of event model
        vHolder.group_name.setText(""+dlist.getGroup_name());//variables of event model

        return view_row;
    }
    private class ViewHolderDoc
    {
        TextView group_id,group_name;
    }
}


