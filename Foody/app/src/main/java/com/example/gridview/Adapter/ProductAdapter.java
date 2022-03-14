package com.example.gridview.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gridview.ChiTietTraSua;
import com.example.gridview.MainActivity;
import com.example.gridview.R;
import com.example.gridview.model.GioHang;
import com.example.gridview.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {
    ArrayList<Product> arrayList;
    int resource;
    Context context;
    public ProductAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Product> objects) {
        super(context, resource, objects);
        this.arrayList = objects;
        this.context = context;
        this.resource =resource;
    }

    int so_luong=0;
    int thanhtongtien;
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource,parent,false);
        ImageView anhsp = convertView.findViewById(R.id.anhsp);
        TextView tensp = convertView.findViewById(R.id.tensp);
        TextView giasp = convertView.findViewById(R.id.giasp);
        Button themon = convertView.findViewById(R.id.themmon);
        final Product product =arrayList.get(position);
        anhsp.setImageBitmap(BitmapFactory.decodeByteArray(product.getmImage(),0,product.getmImage().length));
        tensp.setText(product.getmName());
        giasp.setText(product.getGia()+",000đ");

        themon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                dialog.setContentView(R.layout.dialog_dat_mon);
                Window window =dialog.getWindow();
                if (window == null)
                {
                    return;
                }
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable( new ColorDrawable(Color.TRANSPARENT));
                WindowManager.LayoutParams layoutParams =window.getAttributes();
                layoutParams.gravity = Gravity.CENTER;
                window.setAttributes(layoutParams);
                ImageView imageView = dialog.findViewById(R.id.image_ctsp_355);
                imageView.setImageBitmap(BitmapFactory.decodeByteArray(product.getmImage(),0,product.getmImage().length));
                TextView textView = dialog.findViewById(R.id.name_ctsp_355);
                textView.setText(product.getmName());
                TextView gia = dialog.findViewById(R.id.gia_ctsp_355);
                gia.setText(product.getGia()+",000đ");
                final TextView soluong = dialog.findViewById(R.id.soluong_355);
                final TextView thanhtien =dialog.findViewById(R.id.tongtien);
                Button add = dialog.findViewById(R.id.btn_add_ctsp_355);
                Button minus = dialog.findViewById(R.id.btn_minus_ctsp_355);
                Button themmon = dialog.findViewById(R.id.themmon_355);
                themmon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor cursor = MainActivity.database.rawQuery("Select Idshop from SanPham where IDSP = "+product.getmId(),null);
                        cursor.moveToFirst();
                        if (ChiTietTraSua.gioHangArrayList.size()>0)
                        {
                            int dem=0;
                            for (int i =0;i<ChiTietTraSua.gioHangArrayList.size();i++)
                            {
                                if (ChiTietTraSua.gioHangArrayList.get(i).getmIdSP()==product.getmId())
                                {
                                    ChiTietTraSua.gioHangArrayList.get(i).setSoluong(so_luong);
                                    dem++;
                                }
                            }
                            if (dem==0)
                            {
                                ChiTietTraSua.gioHangArrayList.add(new GioHang(product.getmId(),product.getmImage(), product.getmName(), product.getGia(), cursor.getInt(0),so_luong));

                            }
                        }
                        else {
                            ChiTietTraSua.gioHangArrayList.add(new GioHang(product.getmId(),product.getmImage(), product.getmName(), product.getGia(), cursor.getInt(0),so_luong));
                        }

                        ChiTietTraSua.kiemtraGH();
                        dialog.cancel();
                    }
                });

                soluong.setText("1");
                thanhtien.setText(product.getGia()+",000đ");
                so_luong=Integer.parseInt(soluong.getText().toString()) ;
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        so_luong=so_luong+1;
                        thanhtongtien = product.getGia()*so_luong;
                        soluong.setText(so_luong+"");
                        thanhtien.setText(thanhtongtien+",000đ");
                    }
                });
                minus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        so_luong=so_luong-1;
                        if(so_luong<1)
                        {
                            for (int i = 0;i<ChiTietTraSua.gioHangArrayList.size();i++)
                            {
                                if(ChiTietTraSua.gioHangArrayList.get(i).getmIdSP()==product.getmId())
                                {
                                    ChiTietTraSua.gioHangArrayList.remove(i);
                                }
                            }
                            ChiTietTraSua.kiemtraGH();
                            dialog.cancel();
                        }
                            thanhtongtien = product.getGia()*so_luong;
                            soluong.setText(so_luong+"");
                            thanhtien.setText(thanhtongtien+",000đ");

                    }
                });
                dialog.show();
            }
        });
        return convertView;
    }
}
