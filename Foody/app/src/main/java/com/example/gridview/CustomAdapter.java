package com.example.gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<RowModel> rowData;
    LayoutInflater layoutInflater;
    RowModel rowModel;

    public CustomAdapter(Context context, ArrayList<RowModel> rowData){
        this.context = context;
        this.rowData = rowData;
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return rowData.size();
    }

    @Override
    public Object getItem(int position) {
        return rowData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return rowData.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View rowView = view;
        if(rowView == null){
            rowView = layoutInflater.inflate(R.layout.activity_row,null, true);
        }
        TextView itemRow = rowView.findViewById(R.id.item_row);
        CircleImageView imageView = rowView.findViewById(R.id.icon);

        rowModel = rowData.get(position);
        itemRow.setText(rowModel.getName());
        imageView.setImageResource(rowModel.getId());

        return rowView;
    }
}
