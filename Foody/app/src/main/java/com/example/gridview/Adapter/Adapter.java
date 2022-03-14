package com.example.gridview.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gridview.InforList;
import com.example.gridview.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends ArrayAdapter<InforList> {
    private Context context;
    private int resource;
    private ArrayList<InforList> arrInfor;
    public Adapter(@NonNull Context context, int resource, @NonNull ArrayList<InforList> objects) {
        super(context, resource, objects);
        this.arrInfor = objects;
        this.context =context;
        this.resource = resource;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.gridlayout,parent,false);
        ImageView imageView = convertView.findViewById(R.id.imageGrid);
        TextView gv_name = convertView.findViewById(R.id.name);

        InforList inforList = arrInfor.get(position);
        Bitmap bitmap = BitmapFactory.decodeByteArray(inforList.getgImage(),0,inforList.getgImage().length);
        imageView.setImageBitmap(bitmap);
        gv_name.setText(inforList.getgName());

        return convertView;
    }

}
