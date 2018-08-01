package com.example.admin.customwebview;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyListAdapter extends ArrayAdapter<String>{

    private final Activity context;
    private final String[] names;
    private final Integer[] icons;

    public MyListAdapter(Activity context, String[] names, Integer[] icons) {
        super(context, R.layout.item_web, names);
        // TODO Auto-generated constructor stub
        this.context=context;
        this.names=names;
        this.icons=icons;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.item_web, null,true);

        TextView nameText = (TextView) rowView.findViewById(R.id.name1);
        ImageView iconView = (ImageView) rowView.findViewById(R.id.icon1);

        nameText.setText(names[position]);
        iconView.setImageResource(icons[position]);

        return rowView;
    }

}
