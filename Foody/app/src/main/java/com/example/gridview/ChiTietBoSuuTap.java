package com.example.gridview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.gridview.Adapter.AdapterBoSuuTapTabLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ChiTietBoSuuTap extends AppCompatActivity {
    ImageView btnback;
    private ViewPager mVp;
    private ImageView imageView;
    private TextView name;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_bo_suu_tap);
        initView();
        imageView = findViewById(R.id.image_qc);
        name = findViewById(R.id.name_sale);
        byte[] hinhAnh = getIntent().getByteArrayExtra("Image");
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
        imageView.setImageBitmap(bitmap);
        name.setText(getIntent().getStringExtra("Name"));
        textView = findViewById(R.id.textView_title);
        textView.setText(getIntent().getStringExtra("Name"));
        btnback = (ImageView) findViewById(R.id.igback);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.Vp_bosuatap);
        List<String> dieukienlist = new ArrayList<>();
        dieukienlist.add("WHERE idUuDai="+getIntent().getIntExtra("IDLOAIUD",0)+" AND SanPham.idShop = Shop.idShop AND ChiTietShop.idShop = Shop.idShop AND sao >= 4 GROUP by Shop.idShop");
        dieukienlist.add("WHERE idUuDai="+getIntent().getIntExtra("IDLOAIUD",0)+" AND SanPham.idShop = Shop.idShop AND ChiTietShop.idShop = Shop.idShop  GROUP by Shop.idShop");
        dieukienlist.add("WHERE idUuDai="+getIntent().getIntExtra("IDLOAIUD",0)+" AND SanPham.idShop = Shop.idShop AND ChiTietShop.idShop = Shop.idShop AND thinhHanh=1 GROUP by Shop.idShop");
        dieukienlist.add("WHERE idUuDai="+getIntent().getIntExtra("IDLOAIUD",0)+" AND SanPham.idShop = Shop.idShop AND ChiTietShop.idShop = Shop.idShop GROUP by Shop.idShop");
        mVp.setAdapter(new AdapterBoSuuTapTabLayout(getSupportFragmentManager(),dieukienlist));
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_bosuatap);
        tabLayout.setupWithViewPager(mVp);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mVp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}