package com.demo.russell.bwsecurity;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by russell on 23/05/2016.
 */
public class UserAdapter extends ArrayAdapter<SummaryItem> {
    public UserAdapter(Context context, ArrayList<SummaryItem> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        SummaryItem user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.info_list_item, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.textViewName);
        TextView tvHome = (TextView) convertView.findViewById(R.id.textViewValue);
        // Populate the data into the template view using the data object
        tvName.setText(user.cName);
        tvHome.setText(user.CValue);

        if(user.cName=="--------"){
            tvHome.setTextColor(Color.DKGRAY);
        } else
            tvHome.setTextColor(Color.WHITE);
        //tvHome.setTextColor(Color.RED);
        // Return the completed view to render on screen
        return convertView;
    }
}
