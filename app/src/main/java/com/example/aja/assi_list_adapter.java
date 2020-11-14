package com.example.aja;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class assi_list_adapter extends BaseAdapter {
    private Context activity;

    private LayoutInflater inflater;

    private final List<assi_list_model> event_list;

    public assi_list_adapter(Context activity, List<assi_list_model> event_list)
    {
        this.activity=activity;
        this.event_list= event_list;
    }

    @Override
    public int getCount() {
        return event_list.size();
    }

    @Override
    public assi_list_model getItem(int position) {
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
            view_row=inflater.inflate(R.layout.assinment_layout,parent,false);
        }
        else
        {
            view_row=convertView;//object of viewholder
        }

        vHolder=new ViewHolderDoc();
        vHolder.assi_id=(TextView)view_row.findViewById(R.id.assi_id);// var of list layout model
        vHolder.name=(TextView)view_row.findViewById(R.id.assi_name);
        vHolder.subj=(TextView)view_row.findViewById(R.id.assi_sub);
        vHolder.des=(TextView)view_row.findViewById(R.id.assi_des);
        vHolder.url=(Button) view_row.findViewById(R.id.assi_file);
        final assi_list_model dlist= event_list.get(position);
        vHolder.assi_id.setText(""+dlist.getAssi_id());//variables of event model  distict
        vHolder.name.setText(""+dlist.getName());//variables of event model
        vHolder.subj.setText(""+dlist.getSubj());//variables of event model
        vHolder.des.setText(""+dlist.getDes());//variables of event model
        vHolder.url.setText("View");//variables of event model

        vHolder.url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=dlist.getUrl();
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse(url));
                activity.startActivity(viewIntent);
            }
        });

        return view_row;
    }
    private class ViewHolderDoc
    {
        TextView assi_id,name,subj,des;
        Button url;
    }
}







