package com.example.gridview.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gridview.ChiTietTraSua;
import com.example.gridview.GioHangActivity;
import com.example.gridview.MainActivity;
import com.example.gridview.model.GioHang;
import com.example.gridview.R;

import java.util.ArrayList;

public class GioHangAdapter extends RecyclerView.Adapter<com.example.gridview.Adapter.GioHangAdapter.GioHangViewHolder> {
    private Context context;

    private ArrayList<GioHang> arrayList;
    public void setData(Context context,ArrayList<GioHang> list)
    {
        this.context =context;
        this.arrayList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GioHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(context).inflate(R.layout.row_giohang,parent,false);

        return new GioHangViewHolder(view);
    }
    public  int thanhtongtien;
    int so_luong;
    @Override
    public void onBindViewHolder(@NonNull GioHangViewHolder holder, int position) {
        final GioHang product = arrayList.get(position);
        holder.name_sp_355.setText(product.getmName());
        holder.gia_sp_355.setText(product.getThanhtien() +".000 đ");
        holder.soluong_355.setText(product.getSoluong()+" x ");
        holder.anhsp.setImageBitmap(BitmapFactory.decodeByteArray(product.getmImage(),0,product.getmImage().length));
        holder.name_sp_355.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
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
                gia.setText(product.getGia()+",000 đ");
                final TextView soluong = dialog.findViewById(R.id.soluong_355);
                final TextView thanhtien =dialog.findViewById(R.id.tongtien);
                Button add = dialog.findViewById(R.id.btn_add_ctsp_355);
                Button minus = dialog.findViewById(R.id.btn_minus_ctsp_355);
                Button themmon = dialog.findViewById(R.id.themmon_355);
                themmon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor cursor = MainActivity.database.rawQuery("Select Idshop from SanPham where IDSP = "+product.getmIdSP(),null);
                        cursor.moveToFirst();
                        int dem=0;
                        for (int i =0;i<ChiTietTraSua.gioHangArrayList.size();i++)
                        {
                            if (ChiTietTraSua.gioHangArrayList.get(i).getmIdSP()==product.getmIdSP())
                            {
                                ChiTietTraSua.gioHangArrayList.get(i).setSoluong(so_luong);
                                dem++;
                            }
                        }
                        if (dem==0)
                        {
                            ChiTietTraSua.gioHangArrayList.add(new GioHang(product.getmIdSP(),product.getmImage(), product.getmName(), product.getGia(), cursor.getInt(0),so_luong));

                        }
                        ChiTietTraSua.kiemtraGH();
                        notifyDataSetChanged();
                        GioHangActivity.setTongTien(context);
                        dialog.cancel();
                    }
                });

                soluong.setText("1");
                thanhtien.setText(product.getGia()+",000 đ");
                so_luong=Integer.parseInt(soluong.getText().toString()) ;
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        so_luong=so_luong+1;
                        thanhtongtien = product.getGia()*so_luong;
                        soluong.setText(so_luong+"");
                        thanhtien.setText(thanhtongtien+",000 đ");
                    }
                });
                minus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        so_luong=so_luong-1;
                        if(so_luong<0)
                        {
                            for (int i = 0;i<ChiTietTraSua.gioHangArrayList.size();i++)
                            {
                                if(ChiTietTraSua.gioHangArrayList.get(i).getmIdSP()==product.getmIdSP())
                                {
                                    ChiTietTraSua.gioHangArrayList.remove(i);
                                }
                            }
                            notifyDataSetChanged();
                            GioHangActivity.setTongTien(context);
                            if(ChiTietTraSua.gioHangArrayList.size()==0)
                            {
                                Intent intent = new Intent(context,ChiTietTraSua.class);
                                context.startActivity(intent);
                            }
                            dialog.cancel();
                        }
                        thanhtongtien = product.getGia()*so_luong;
                        soluong.setText(so_luong+"");
                        thanhtien.setText(thanhtongtien+",000 đ");

                    }
                });
                dialog.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public  class GioHangViewHolder extends RecyclerView.ViewHolder {
            private TextView name_sp_355;
        TextView size_sp_355;
        TextView soluong_355;
        TextView gia_sp_355;
        ImageView anhsp;
       public GioHangViewHolder(@NonNull View itemView) {
           super(itemView);
           anhsp = itemView.findViewById(R.id.anhsp_rgh);
           name_sp_355 = itemView.findViewById(R.id.name_sp_giohang_355);
           soluong_355  =itemView.findViewById(R.id.soluong_giohang);
           gia_sp_355 = itemView.findViewById(R.id.tongtien_giohang_355);
       }
   }

}
