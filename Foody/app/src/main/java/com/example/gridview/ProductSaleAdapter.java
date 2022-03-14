package com.example.gridview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductSaleAdapter extends RecyclerView.Adapter<ProductSaleAdapter.ViewHolder> {
    ArrayList<ProductSale> productSales;
    Context context;
    public ProductSaleAdapter(ArrayList<ProductSale> recylerArrayAdapter,Context context) {
        this.productSales = recylerArrayAdapter;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductSaleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.recycler_sanpham,parent,false);
        return new ProductSaleAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductSaleAdapter.ViewHolder holder, int position) {
        holder.txtName.setText(productSales.get(position).getmName());
        holder.txtAddr.setText(productSales.get(position).getmStore());
        holder.txtGiaMoi.setText(productSales.get(position).getmGiaMoi()+",000đ");
        holder.txtGiaCu.setText(productSales.get(position).getmGiaCu()+",000đ");
        Bitmap bitmap = BitmapFactory.decodeByteArray(productSales.get(position).getmImage(),0,productSales.get(position).getmImage().length);
        holder.imgView.setImageBitmap(bitmap);
        holder.txtGiaCu.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public int getItemCount() {
        return productSales.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtAddr;
        TextView txtGiaCu;
        TextView txtGiaMoi;
        TextView txtName;
        ImageView imgView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.name_sanpham);
            imgView = (ImageView) itemView.findViewById(R.id.img_sanpham_1);
            txtAddr = (TextView) itemView.findViewById(R.id.addr_sanpham);
            txtGiaCu = (TextView) itemView.findViewById(R.id.gia_cu);
            txtGiaMoi = (TextView) itemView.findViewById(R.id.gia_moi);
        }
    }
}
