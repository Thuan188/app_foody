package com.example.gridview;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.gridview.Adapter.ProductAdapter;
import com.example.gridview.model.GioHang;
import com.example.gridview.model.Product;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class ChiTietTraSua extends AppCompatActivity {
    ImageView poster;
    TextView namept;
    TextView diachipt;
    ImageView btnback;
    TextView danhgia;
    ListView listView;
    ProductAdapter productAdapter;
    ArrayList<Product> arrayList;
    static RelativeLayout relativeLayout;
    static TextView sluong;
    static TextView gia;
    Button btn_giohang;
    static int intent;
    public static ArrayList<GioHang> gioHangArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_tra_sua);
        poster = findViewById(R.id.image_poster);
        namept = findViewById(R.id.tv_tenquan);
        danhgia = findViewById(R.id.tv_danhgia);
        listView = findViewById(R.id.danhsachsp);
        diachipt = findViewById(R.id.tv_diachi);
        sluong = findViewById(R.id.sl_chitiet);
        gia = findViewById(R.id.gia_chitiet);
        btn_giohang = findViewById(R.id.btn_giohang);
        relativeLayout = findViewById(R.id.giohang);
        intent = getIntent().getIntExtra("IDShop",1);
        kiemtraGH();
        Cursor cursor = MainActivity.database.rawQuery("Select tenShop,anhShop,round(avg(SanPham.sao),1),chitietDiaChi from Shop,SanPham,ChiTietShop where ChiTietShop.idShop = Shop.idShop and SanPham.idShop = Shop.idShop and  Shop.idShop ="+getIntent().getIntExtra("IDShop",1)+" GROUP by Shop.idShop",null);
        cursor.moveToFirst();
        namept.setText(cursor.getString(0));
        Bitmap bitmap = BitmapFactory.decodeByteArray((byte[]) cursor.getBlob(1),0,((byte[]) cursor.getBlob(1)).length);
        danhgia.setText(cursor.getString(2)+ " (999+)");
        poster.setImageBitmap(bitmap);
        diachipt.setText(cursor.getString(3));

        btnback = (ImageView) findViewById(R.id.igchitietmilkback);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        AnhXa();
        productAdapter = new ProductAdapter(this,R.layout.item_product,arrayList);

        listView.setAdapter(productAdapter);
        btn_giohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChiTietTraSua.this,GioHangActivity.class);
                intent.putExtra("IDSHOP",getIntent().getIntExtra("IDShop",0));
                startActivity(intent);
            }
        });
    }

    private void AnhXa() {
        arrayList = new ArrayList<>();
        Cursor cursor = MainActivity.database.rawQuery("Select * from SanPham where idShop ="+getIntent().getIntExtra("IDShop",1) ,null);
        while (cursor.moveToNext()) {
            arrayList.add(new Product(cursor.getInt(0),(byte[]) cursor.getBlob(2),cursor.getString(1),cursor.getInt(3)));
        }
    }
    public  static  void kiemtraGH(){
        if(gioHangArrayList.size()>0)
        {
            int dem=0;
            int sl=0;
            int tt=0;
            for (int i =0;i<gioHangArrayList.size();i++)
            {
                if (gioHangArrayList.get(i).getIDshop()==intent){
                    dem++;
                    sl=sl+gioHangArrayList.get(i).getSoluong();
                    tt=tt+gioHangArrayList.get(i).getThanhtien();
                }
            }
            if(dem==0){
                relativeLayout.setVisibility(View.GONE);
            }
            else {
                gia.setText(tt+",000đ");
                sluong.setText(sl+"");
                relativeLayout.setVisibility(View.VISIBLE);
            }
        }
        else {
            relativeLayout.setVisibility(View.GONE);
        }
    }


}