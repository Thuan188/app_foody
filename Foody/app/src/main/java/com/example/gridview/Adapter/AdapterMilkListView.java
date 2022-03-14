package com.example.gridview.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.gridview.R;
import com.example.gridview.model.ChiTietMilk;

import java.util.ArrayList;

public class AdapterMilkListView extends ArrayAdapter<ChiTietMilk> {
    private Context context;
    private int resource;
    private ArrayList<ChiTietMilk> arrList;


    public AdapterMilkListView(@NonNull Context context, int resource, ArrayList<ChiTietMilk> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.arrList=objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_lv_chitietmilk,parent,false);
        ImageView ig_milk = (ImageView) convertView.findViewById(R.id.igmilk);
        ImageView ig_add = (ImageView) convertView.findViewById(R.id.ig_themmilk);
        TextView tv_tenmilk = (TextView) convertView.findViewById(R.id.tv_milk);
        TextView tv_danhgiamilk = (TextView) convertView.findViewById(R.id.tv_danhgiamilk);
        TextView tv_giamilk = (TextView) convertView.findViewById(R.id.tv_giamilk);
        ChiTietMilk chiTietMilk = arrList.get(position);
        ig_milk.setImageResource(chiTietMilk.getMmilk());
        ig_add.setImageResource(chiTietMilk.getMadd());
        tv_danhgiamilk.setText(chiTietMilk.getMdanhgiamilk());
        tv_tenmilk.setText(chiTietMilk.getMtenmilk());
        tv_giamilk.setText(chiTietMilk.getMgiamilk());
        return convertView;
    }
}
