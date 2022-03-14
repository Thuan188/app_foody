package com.example.gridview.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.gridview.ChiTietTraSua;
import com.example.gridview.R;
import com.example.gridview.model.Shop;

import java.util.ArrayList;

public class AdapterKhamPhaListView extends ArrayAdapter<Shop> {
    private Context context;
    private int resource;
    private ArrayList<Shop> arrList;


    public AdapterKhamPhaListView(@NonNull Context context, int resource, @NonNull ArrayList<Shop> objects) {
        super(context, resource, objects);
        this.context= context;
        this.resource = resource;
        this.arrList =objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource,parent,false);
        ImageView ig_sp = (ImageView) convertView.findViewById(R.id.igsp);
        TextView tv_tenquan = (TextView) convertView.findViewById(R.id.tv_tenquan);
        TextView tv_diachi = (TextView) convertView.findViewById(R.id.tv_diachi);
        TextView tv_danhgia = (TextView) convertView.findViewById(R.id.tv_danhgia);
        LinearLayout dongsp = convertView.findViewById(R.id.dongsp);
        final Shop product = arrList.get(position);
        dongsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ChiTietTraSua.class);
                intent.putExtra("IDShop",product.getmId());
                getContext().startActivity(intent);
            }
        });

        Bitmap bitmap = BitmapFactory.decodeByteArray(product.getmImage(),0,product.getmImage().length);
        ig_sp.setImageBitmap(bitmap);
        tv_tenquan.setText(product.getmName());
        tv_diachi.setText(product.getmAdd());
        tv_danhgia.setText(product.getmSao());
        return convertView;
    }
}
