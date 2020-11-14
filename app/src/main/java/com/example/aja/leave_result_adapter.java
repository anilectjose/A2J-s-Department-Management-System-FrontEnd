package com.example.aja;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class leave_result_adapter extends BaseAdapter {
    private Context activity;
    private LayoutInflater inflater;

    private final List<leave_result_list> event_list;

    public leave_result_adapter(Context activity, List<leave_result_list> event_list) {
        this.activity = activity;
        this.event_list = event_list;

    }

    @Override
    public int getCount() {
        return event_list.size();
    }

    @Override
    public leave_result_list getItem(int position) {
        return event_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)// objects are used further
    {
        leave_result_adapter.ViewHolderDoc vHolder;
        View view_row = null;
        if (convertView == null) {
            inflater = ((Activity) activity).getLayoutInflater();
            view_row = inflater.inflate(R.layout.leave_result_layout, parent, false);
        } else {
            view_row = convertView;//object of viewholder
        }

        vHolder = new leave_result_adapter.ViewHolderDoc();
        vHolder.lea_id = (TextView) view_row.findViewById(R.id.lea_id);// var of list layout model
        vHolder.name = (TextView) view_row.findViewById(R.id.lea_name);
        vHolder.reg = (TextView) view_row.findViewById(R.id.lea_reg);
        vHolder.date = (TextView) view_row.findViewById(R.id.lea_date);
        vHolder.days = (TextView) view_row.findViewById(R.id.lea_days);
        vHolder.year = (TextView) view_row.findViewById(R.id.lea_year);
        vHolder.res = (TextView) view_row.findViewById(R.id.lea_reason);
        vHolder.result = (TextView) view_row.findViewById(R.id.result);
        /*vHolder.rslt = (TextView) view_row.findViewById(R.id.result);*/
        final leave_result_list dlist = event_list.get(position);
        vHolder.lea_id.setText("" + dlist.getLea_id());//variables of event model
        vHolder.name.setText("" + dlist.getName());//variables of event model
        vHolder.reg.setText("" + dlist.getReg());//variables of event model
        vHolder.date.setText("" + dlist.getDate());//variables of event model
        vHolder.days.setText("" + dlist.getDays());//variables of event model
        vHolder.year.setText("" + dlist.getYear());//variables of event model
        vHolder.res.setText("" + dlist.getRes());//variables of event model
        vHolder.result.setText("" + dlist.getResult());

      /*  vHolder.rslt.setText("" + dlist.getRslt());//variables of event model

        if (vHolder.rslt.equals("Approved"))
        {
            if (vHolder.result.equals(""))
            {
                vHolder.result.setText("Pending by HOD");
            }
            else
            {
                vHolder.result.setText("" + dlist.getResult());
            }
        }
        else if (vHolder.rslt.equals("Rejected"))
        {
            vHolder.result.setText("Rejected");
        }
        else
        {
            vHolder.result.setText("Pending by Mentor");
        }*/
        return view_row;
    }

    private class ViewHolderDoc {
        TextView lea_id, name, reg, res, date, year, days,result,rslt;
    }
}

