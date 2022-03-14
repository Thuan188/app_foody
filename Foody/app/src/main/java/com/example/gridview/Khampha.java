package com.example.gridview;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.gridview.Adapter.AdapterBoSuuTapTabLayout;
import com.example.gridview.Adapter.AdapterKhamPhaListView;
import com.example.gridview.model.Shop;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Khampha extends AppCompatActivity {
    private ViewPager mVp;
    ImageView btback;
    TextView tenloaisp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_khampha);
        tenloaisp = findViewById(R.id.textView);
//        initView();

        btback = (ImageView) findViewById(R.id.ig_back);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final ArrayList<Shop> arrayList = new ArrayList<>();
        Cursor cursor = MainActivity.database.rawQuery("SELECT Shop.idShop,tenShop,anhShop,chitietDiaChi,round(avg(SanPham.sao),1) FROM SanPham,Shop,ChiTietShop WHERE Shop.idLoaiSP = 1 AND SanPham.idShop = Shop.idShop AND ChiTietShop.idShop = Shop.idShop AND sao >= 4 GROUP by Shop.idShop ",null);
        while (cursor.moveToNext()){
            arrayList.add(new Shop(cursor.getInt(0), (byte[]) cursor.getBlob(2),cursor.getString(1), cursor.getString(3),cursor.getString(4)));
        }

        ListView lv = (ListView) findViewById(R.id.lvgoiy);
        AdapterKhamPhaListView customAdapter = new AdapterKhamPhaListView(Khampha.this,R.layout.item_lv_khampha,arrayList);

        lv.setAdapter(customAdapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(view.getContext(), ChiTietTraSua.class);
                startActivity(intent);

            }
        });
    }

//    private void initView() {
//        mVp = (ViewPager) findViewById(R.id.Vp_trasua);
//        Intent intent =getIntent();
//        int dk = intent.getIntExtra("LoaiSP",1);
//        tenloaisp.setText(intent.getStringExtra("NameLSP"));
//        List<String> dieukienlist = new ArrayList<>();
//        dieukienlist.add("WHERE Shop.idLoaiSP ="+dk+" AND SanPham.idShop = Shop.idShop AND ChiTietShop.idShop = Shop.idShop AND sao >= 4 GROUP by Shop.idShop");
//        dieukienlist.add("WHERE Shop.idLoaiSP ="+dk+" AND SanPham.idShop = Shop.idShop AND ChiTietShop.idShop = Shop.idShop GROUP by Shop.idShop");
//        dieukienlist.add("WHERE Shop.idLoaiSP ="+dk+" AND SanPham.idShop = Shop.idShop AND ChiTietShop.idShop = Shop.idShop AND ThinhHanh = 1 GROUP by Shop.idShop");
//        dieukienlist.add("WHERE Shop.idLoaiSP ="+dk+" AND SanPham.idShop = Shop.idShop AND ChiTietShop.idShop = Shop.idShop GROUP by Shop.idShop");
//        mVp.setAdapter(new AdapterKhamPhaTabLayout(getSupportFragmentManager(),dieukienlist));
//        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_trasua);
//        tabLayout.setupWithViewPager(mVp);
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                mVp.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//    }
}