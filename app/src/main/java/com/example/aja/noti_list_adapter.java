package com.example.aja;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class noti_list_adapter extends BaseAdapter {
    private Context activity;
    private LayoutInflater inflater;

    private final List<noti_list_model> event_list;

    public noti_list_adapter(Context activity, List<noti_list_model> event_list)
    {
        this.activity=activity;
        this.event_list= event_list;
    }

    @Override
    public int getCount() {
        return event_list.size();
    }

    @Override
    public noti_list_model getItem(int position) {
        return event_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)// objects are used further
    {
        noti_list_adapter.ViewHolderDoc vHolder;
        View view_row=null;
        if(convertView==null)
        {
            inflater= ((Activity)activity).getLayoutInflater();
            view_row=inflater.inflate(R.layout.noti_layout,parent,false);
        }
        else
        {
            view_row=convertView;//object of viewholder
        }

        vHolder=new noti_list_adapter.ViewHolderDoc();
        vHolder.lea_id=(TextView)view_row.findViewById(R.id.noti_id);// var of list layout model
        vHolder.name=(TextView)view_row.findViewById(R.id.name);
        vHolder.slno=(TextView)view_row.findViewById(R.id.number_id);
        final noti_list_model dlist= event_list.get(position);
        vHolder.lea_id.setText(""+dlist.getNoti_id());//variables of event model
        vHolder.name.setText(""+dlist.getName());//variables of event model
        vHolder.slno.setText(""+dlist.getSlno());

        return view_row;
    }
    private class ViewHolderDoc
    {
        TextView lea_id,name,slno;
    }
}


