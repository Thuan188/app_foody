package com.example.gridview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.gridview.Adapter.Adapter;
import com.example.gridview.Adapter.AdapterBoSuuTapTabLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //khai báo bottom menu
    BottomNavigationView bottomNavigationView;
    //khai báo database
    public static String DATABASE_NAME = "AppFoody.db";
    public static SQLiteDatabase database;
    //khai bao Sliper Image
    SlideAdb slideAdb;
    SliderView imageSlider;
    //khai bao Recycler San pham
    RecyclerView recyclerView_sanpham_1;
    ProductSaleAdapter productSaleProductSaleAdapter;
    ArrayList<ProductSale> productSales;
    //
    public static String sdt="1373951042";
    //san pham 2
    RecyclerView recyclerView_sanpham_2;

    // khai bao FlipperView
    ViewFlipper viewFlipper;
    List<byte[]> img_flip ;

    // Recycler View
    RecyclerView recyclerView;
    RecylerAdapter Adapter5;
    ArrayList<Recyler> recylerArrayList;


    // khai bao GridView
    GridView gridView;
    ArrayList<InforList> arrayList;
    Adapter adapter;



    // khai báo spinner
    Spinner spinner;

    // khai báo Tablayout

    TabLayout tabLayout;
    ViewPager viewPager;

    // Button
    TextView buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database= database = Database.initDatabase(this,DATABASE_NAME);
        //

        // button chon address
        buttonAdd = findViewById(R.id.btn_addr);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ActivitySelectAdd.class);
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

        AnhXa();

        adapter = new Adapter(this,R.layout.gridlayout,arrayList);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i;
                switch (position){
                    case 0:
                        i =new Intent(MainActivity.this,Khampha.class);
                        i.putExtra("LoaiSP",position);
                        i.putExtra("NameLSP",arrayList.get(position).getgName());
                        startActivity(i);
                        break;
                    case 1:
                        i =new Intent(MainActivity.this,MainMilk.class);
                        i.putExtra("LoaiSP",position+1);
                        i.putExtra("NameLSP",arrayList.get(position).getgName());
                        startActivity(i);
                        break;
                    case 2:
                        i =new Intent(MainActivity.this,MainMilk.class);
                        i.putExtra("LoaiSP",position+1);
                        i.putExtra("NameLSP",arrayList.get(position).getgName());
                        startActivity(i);
                        break;
                }


            }
        });


        //bottom menu
        bottomNavigationView = findViewById(R.id.bottom_menu);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()){
                    case R.id.ic_home:
                        break;
                    case R.id.ic_giohang:
                        intent =new Intent(MainActivity.this,MainActivityGioHang.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.silde_out_to_left);
                        break;
                    case R.id.bn_donhang:
                        break;
                    case R.id.bn_thongbao:
                        break;
                    case R.id.ic_me:
                        intent =new Intent(MainActivity.this,MainActivityMe.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.silde_out_to_left);
                        break;
                }
                return false;
            }
        });
        // Recycler quảng cáo 2


//        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        recyclerView.setHasFixedSize(true);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
//        recyclerView.setLayoutManager(layoutManager);
//        recylerArrayList = new ArrayList<>();
//       Cursor boSuuTap = database.rawQuery("Select * from UuDai",null);
//
//        while (boSuuTap.moveToNext())
//        {
//            Recyler recyler = new Recyler((byte[]) boSuuTap.getBlob(2),boSuuTap.getInt(0),boSuuTap.getString(1));
//            recylerArrayList.add(recyler);
//        }
//        Adapter5 = new RecylerAdapter(recylerArrayList,this);
//        recyclerView.setAdapter(Adapter5);


        // Tablayout
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_fragment);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        //SanPham_1
//        recyclerView_sanpham_1 = findViewById(R.id.recycler_sanpham_1);
//        recyclerView_sanpham_1.setHasFixedSize(true);
//        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
//        recyclerView_sanpham_1.setLayoutManager(layoutManager1);
//        productSales = new ArrayList<>();
//        Cursor dongGia1k = database.rawQuery("Select idSP,tenSP,anhSP,giaSP,tenShop from SanPham,Shop where SanPham.idUuDai = 5 and SanPham.idShop = Shop.idShop",null);
//        while (dongGia1k.moveToNext())
//        {
//            productSales.add(new ProductSale(dongGia1k.getInt(0),(byte[]) dongGia1k.getBlob(2),dongGia1k.getString(1),dongGia1k.getString(4),1,dongGia1k.getInt(3)));
//
//        }
//        productSaleProductSaleAdapter = new ProductSaleAdapter(productSales,this);
//        recyclerView_sanpham_1.setAdapter(productSaleProductSaleAdapter);
        //SanPham_2
//        recyclerView_sanpham_2 = findViewById(R.id.recycler_sanpham_2);
//        recyclerView_sanpham_2.setHasFixedSize(true);
//        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
//        recyclerView_sanpham_2.setLayoutManager(layoutManager2);
//        productSales = new ArrayList<>();
//        Cursor dongGia11k = database.rawQuery("Select idSP,tenSP,anhSP,giaSP,tenShop from SanPham,Shop where SanPham.idUuDai = 4 and SanPham.idShop = Shop.idShop",null);
//        while (dongGia11k.moveToNext())
//        {
//            productSales.add(new ProductSale(dongGia11k.getInt(0),(byte[]) dongGia11k.getBlob(2),dongGia11k.getString(1),dongGia11k.getString(4),1,dongGia11k.getInt(3)));
//
//        }
//        productSaleProductSaleAdapter = new ProductSaleAdapter(productSales,this);
//        recyclerView_sanpham_2.setAdapter(productSaleProductSaleAdapter);

        //slider

        imageSlider = findViewById(R.id.slider);
        Cursor cursorposter = database.rawQuery("Select * from PosterNgang",null);
        img_flip = new ArrayList<>();
        while (cursorposter.moveToNext())
        {
            byte[] hinhAnh = cursorposter.getBlob(2);
            img_flip.add(hinhAnh);

        }
        slideAdb = new SlideAdb(img_flip);
        imageSlider.setSliderAdapter(slideAdb);
        imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM);
        imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        imageSlider.startAutoCycle();

    }

    private void AnhXa() {
        gridView = findViewById(R.id.gridView);
        arrayList = new ArrayList<>();
        Cursor cursor = database.rawQuery("Select * from LoaiSanPham",null);
        while (cursor.moveToNext())
        {
            InforList inforList =new InforList((byte[]) cursor.getBlob(2),cursor.getString(1));
            arrayList.add(inforList);
        }
    }
}