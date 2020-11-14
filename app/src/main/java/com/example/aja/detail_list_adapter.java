package com.example.aja;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class detail_list_adapter extends BaseAdapter{
    private Context activity;
    private LayoutInflater inflater;
    String id;

    private final List<detail_list_model> event_list;
    ArrayList<detail_list_model> arrayList;

    public detail_list_adapter(Context activity, List<detail_list_model> event_list)
    {
        this.activity=activity;
        this.event_list= event_list;
        this.arrayList = new ArrayList<>();
        this.arrayList.addAll(event_list);
    }
    @Override
    public int getCount() {
        return event_list.size();
    }

    @Override
    public detail_list_model getItem(int position) {
        return event_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)// objects are used further
    {
        detail_list_adapter.ViewHolderDoc vHolder;
        View view_row=null;
        if(convertView==null)
        {
            inflater= ((Activity)activity).getLayoutInflater();
            view_row=inflater.inflate(R.layout.stu_detail_layout,parent,false);
        }
        else
        {
            view_row=convertView;//object of viewholder
        }

        vHolder=new detail_list_adapter.ViewHolderDoc();
        vHolder.detail_id=(TextView)view_row.findViewById(R.id.detail_id);// var of list layout model
        vHolder.name=(TextView)view_row.findViewById(R.id.name);
        vHolder.reg=(TextView)view_row.findViewById(R.id.stu_reg);
        vHolder.mob=(TextView)view_row.findViewById(R.id.stu_mob);
        vHolder.mail=(TextView)view_row.findViewById(R.id.stu_mail);
        vHolder.year=(TextView)view_row.findViewById(R.id.stu_year);
        vHolder.gen=(TextView)view_row.findViewById(R.id.stu_gen);
        final detail_list_model dlist= event_list.get(position);
        vHolder.detail_id.setText(""+dlist.getDetail_id());//variables of event model
        vHolder.name.setText(""+dlist.getName());//variables of event model
        vHolder.reg.setText(""+dlist.getReg());//variables of event model
        vHolder.mob.setText(""+dlist.getMob());//variables of event model
        vHolder.mail.setText(""+dlist.getMail());//variables of event model
        vHolder.year.setText(""+dlist.getYear());//variables of event model
        vHolder.gen.setText(""+dlist.getGen());//variables of event model

        return view_row;

    }

    private class ViewHolderDoc
    {
        TextView detail_id,name,reg,mob,mail,year,gen;
    }
    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        event_list.clear();

        if (charText.length()==0){
            event_list.addAll(arrayList);
        }
        else {
            for (detail_list_model model : arrayList){
                if (model.getName().toLowerCase(Locale.getDefault())
                        .contains(charText)){
                    event_list.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }
}

