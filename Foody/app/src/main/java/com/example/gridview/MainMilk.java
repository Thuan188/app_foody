package com.example.gridview;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.gridview.Adapter.AdapterBoSuuTapTabLayout;
import com.example.gridview.Adapter.AdapterKhamPhaListView;
import com.example.gridview.model.Shop;
import com.google.android.material.tabs.TabLayout;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class MainMilk extends AppCompatActivity {
    public static String DATABASE_NAME = "AppFoody.db";
    public static SQLiteDatabase database;
    private ViewPager mVp;
    ImageView btback;
    TextView tenloaisp;
    SliderView imageSlider;
    SlideAdb slideAdb;
    // Recycler View
    RecyclerView recyclerView;
    RecylerAdapter Adapter5;
    ArrayList<Recyler> recylerArrayList;
    List<byte[]> img_flip ;
    //khai bao Recycler San pham
    RecyclerView recyclerView_sanpham_1;
    ProductSaleAdapter productSaleProductSaleAdapter;
    ArrayList<ProductSale> productSales;
    // Button
    TextView buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_milk);
        database= database = Database.initDatabase(this,DATABASE_NAME);
//        tenloaisp = findViewById(R.id.textView);
        initView();

        btback = (ImageView) findViewById(R.id.ig_back);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // Recycler quảng cáo 2


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recylerArrayList = new ArrayList<>();
        Cursor boSuuTap = database.rawQuery("Select * from UuDai",null);

        while (boSuuTap.moveToNext())
        {
            Recyler recyler = new Recyler((byte[]) boSuuTap.getBlob(2),boSuuTap.getInt(0),boSuuTap.getString(1));
            recylerArrayList.add(recyler);
        }
        Adapter5 = new RecylerAdapter(recylerArrayList,this);
        recyclerView.setAdapter(Adapter5);
//        //slider
//
//        imageSlider = findViewById(R.id.slider);
//        Cursor cursorposter = database.rawQuery("Select * from PosterNgang",null);
//        img_flip = new ArrayList<>();
//        while (cursorposter.moveToNext())
//        {
//            byte[] hinhAnh = cursorposter.getBlob(2);
//            img_flip.add(hinhAnh);
//
//        }
//        slideAdb = new SlideAdb(img_flip);
//        imageSlider.setSliderAdapter(slideAdb);
//        imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM);
//        imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
//        imageSlider.startAutoCycle();
        //SanPham_1
        recyclerView_sanpham_1 = findViewById(R.id.recycler_sanpham_1);
        recyclerView_sanpham_1.setHasFixedSize(true);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView_sanpham_1.setLayoutManager(layoutManager1);
        productSales = new ArrayList<>();
        Cursor dongGia1k = database.rawQuery("Select idSP,tenSP,anhSP,giaSP,tenShop from SanPham,Shop where SanPham.idUuDai = 5 and SanPham.idShop = Shop.idShop",null);
        while (dongGia1k.moveToNext())
        {
            productSales.add(new ProductSale(dongGia1k.getInt(0),(byte[]) dongGia1k.getBlob(2),dongGia1k.getString(1),dongGia1k.getString(4),1,dongGia1k.getInt(3)));

        }
        productSaleProductSaleAdapter = new ProductSaleAdapter(productSales,this);
        recyclerView_sanpham_1.setAdapter(productSaleProductSaleAdapter);
        //listview
        final ArrayList<Shop> arrayList = new ArrayList<>();
        Cursor cursor = MainActivity.database.rawQuery("SELECT Shop.idShop,tenShop,anhShop,chitietDiaChi,round(avg(SanPham.sao),1) FROM SanPham,Shop,ChiTietShop WHERE Shop.idLoaiSP = 1 AND SanPham.idShop = Shop.idShop AND ChiTietShop.idShop = Shop.idShop AND sao >= 4 GROUP by Shop.idShop ",null);
        while (cursor.moveToNext()){
            arrayList.add(new Shop(cursor.getInt(0), (byte[]) cursor.getBlob(2),cursor.getString(1), cursor.getString(3),cursor.getString(4)));
        }

        ListView lv = (ListView) findViewById(R.id.lvgoiy);
        AdapterKhamPhaListView customAdapter = new AdapterKhamPhaListView(MainMilk.this,R.layout.item_lv,arrayList);

        lv.setAdapter(customAdapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(view.getContext(), ChiTietTraSua.class);
                startActivity(intent);

            }
        });
        // button chon address
        buttonAdd = findViewById(R.id.btn_addr);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMilk.this,ActivitySelectAdd.class);
                startActivity(intent);
            }
        });
        Intent intent2 = getIntent();
        String add_select = intent2.getStringExtra("ADDRESS_SELECTED");
        if (add_select==null)
        {
            buttonAdd.setText("Đà Nẵng");
        }
        else
        {
            buttonAdd.setText(add_select);
        }
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.Vp_trasua);
        Intent intent =getIntent();
        int dk = intent.getIntExtra("LoaiSP",1);
//        tenloaisp.setText(intent.getStringExtra("NameLSP"));
        List<String> dieukienlist = new ArrayList<>();
        dieukienlist.add("WHERE Shop.idLoaiSP ="+dk+" AND SanPham.idShop = Shop.idShop AND ChiTietShop.idShop = Shop.idShop AND sao >= 4 GROUP by Shop.idShop");
        dieukienlist.add("WHERE Shop.idLoaiSP ="+dk+" AND SanPham.idShop = Shop.idShop AND ChiTietShop.idShop = Shop.idShop GROUP by Shop.idShop");
        dieukienlist.add("WHERE Shop.idLoaiSP ="+dk+" AND SanPham.idShop = Shop.idShop AND ChiTietShop.idShop = Shop.idShop AND ThinhHanh = 1 GROUP by Shop.idShop");
        dieukienlist.add("WHERE Shop.idLoaiSP ="+dk+" AND SanPham.idShop = Shop.idShop AND ChiTietShop.idShop = Shop.idShop GROUP by Shop.idShop");

            mVp.setAdapter(new AdapterBoSuuTapTabLayout(getSupportFragmentManager(),dieukienlist));
            TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_trasua);
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
