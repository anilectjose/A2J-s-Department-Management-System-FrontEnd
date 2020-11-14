package com.example.aja;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class attend_list_adapter extends BaseAdapter {

    private Context activity;
    private LayoutInflater inflater;
    String id;

    private final List<attend_list_model> event_list;

    public attend_list_adapter(Context activity, List<attend_list_model> event_list)
    {
        this.activity=activity;
        this.event_list= event_list;
    }

    @Override
    public int getCount() {
        return event_list.size();
    }

    @Override
    public attend_list_model getItem(int position) {
        return event_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)// objects are used further
    {
        attend_list_adapter.ViewHolderDoc vHolder;
        View view_row=null;
        if(convertView==null)
        {
            inflater= ((Activity)activity).getLayoutInflater();
            view_row=inflater.inflate(R.layout.att_name_layout,parent,false);
        }
        else
        {
            view_row=convertView;//object of viewholder
        }

        vHolder=new attend_list_adapter.ViewHolderDoc();
        vHolder.att_id=(TextView)view_row.findViewById(R.id.att_id);// var of list layout model
        vHolder.name=(TextView)view_row.findViewById(R.id.att_name);
        final attend_list_model dlist= event_list.get(position);
        vHolder.att_id.setText(""+dlist.getAtt_id());//variables of event model
        vHolder.name.setText(""+dlist.getName());//variables of event model

        return view_row;
    }
    private class ViewHolderDoc
    {
        TextView att_id,name;
    }
}

