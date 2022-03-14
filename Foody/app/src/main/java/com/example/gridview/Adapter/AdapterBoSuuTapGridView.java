package com.example.gridview.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gridview.R;

public class AdapterBoSuuTapGridView extends BaseAdapter {
    private Context context;
    private int[] hinh;
    private String[] ten;

    public AdapterBoSuuTapGridView(Context context, int[] hinh, String[] ten) {
        this.context = context;
        this.hinh = hinh;
        this.ten = ten;
    }

    @Override
    public int getCount() {
        return ten.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        ImageView igmHinh;
        TextView textten;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.item_grv,null);
        TextView textView = (TextView)view.findViewById(R.id.txtten);
        ImageView imageView=(ImageView)view.findViewById(R.id.ighinh);
        textView.setText(ten[i]);
        imageView.setImageResource(hinh[i]);
        return view;
    }
}
