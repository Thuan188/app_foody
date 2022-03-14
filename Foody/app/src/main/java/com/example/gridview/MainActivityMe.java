package com.example.gridview;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class MainActivityMe extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    ListView listView, listView1, listView2;
    Context context;
    ArrayList<RowModel> rowData;
    CustomAdapter customAdapter;
    CustomAdapter1 customAdapter1;
    CustomAdapter2 customAdapter2;
    RowModel rowModel;
    LinearLayout relativeLayout, relativeLayout2;

    TextView textView, txtdn;
    LinearLayout Dn;
//    int[] img = {R.drawable.voucher,R.drawable.coins,R.drawable.wallet,R.drawable.person,R.drawable.address,R.drawable.help,R.drawable.shop,R.drawable.gear,R.drawable.information};
//    String[] a = {"Ví Voucher", "Shopee Xu" , "Thanh Toán", "Mời bạn bè" , "Địa chỉ", "Trung tâm Trợ giúp",
//            "Ứng dụng chủ quán", "Cài đặt" , "Về Now"};
    int[] img = {R.drawable.cong_cu1,R.drawable.cong_cu2,R.drawable.cong_cu3,R.drawable.cong_cu4,R.drawable.congcu5,R.drawable.cong_cu6,R.drawable.cong_cu7,R.drawable.cong_cu11,R.drawable.cong_cu8,R.drawable.cong_cu9};
    String[] a = {"Thanh toán", "Lịch Sử" , "Hóa đơn", "Tiền thưởng" , "Ví Voucher", "Ứng dụng cho chủ quán",
            "Mời bạn bè", "Góp ý" , "Chính sách qui định", "Cài đặt ứng dụng"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_me);
        relativeLayout = findViewById(R.id.thongtin_pf);
        relativeLayout2 = findViewById(R.id.list_thongtin);
        Dn = findViewById(R.id.bt_lognout);
        txtdn = findViewById(R.id.item_row);
        textView = findViewById(R.id.tennguoidung);
        context = this;
        if (MainActivity.sdt=="")
        {
            relativeLayout.setVisibility(View.GONE);
            relativeLayout2.setVisibility(View.GONE);
            txtdn.setText("Đăng Nhập");
        }
        else
        {
            Cursor cursor = MainActivity.database.rawQuery("Select * from TaiKhoan where SDT ='"+MainActivity.sdt+"'",null);
            cursor.moveToFirst();
            textView.setText(cursor.getString(0));
        }
        Dn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.sdt=="")
                {
                    Intent intent = new Intent(MainActivityMe.this,DangNhapActivity.class);
                    startActivity(intent);
                }
                else {
                    MainActivity.sdt="";
                    relativeLayout.setVisibility(View.GONE);
                    relativeLayout2.setVisibility(View.GONE);

                    txtdn.setText("Đăng Nhập");
                }
            }
        });
        Cursor cursor = MainActivity.database.rawQuery("Select * from TaiKhoan where SDT ='"+MainActivity.sdt+"'",null);
        cursor.moveToFirst();
        listView = findViewById(R.id.listView);
        rowData = new ArrayList<>();
        addRowData();
        customAdapter = new CustomAdapter(context, rowData);
        listView.setAdapter(customAdapter);

        listView1 = findViewById(R.id.listView1);
        rowData = new ArrayList<>();
        addRowData1();
        customAdapter1 = new CustomAdapter1(context, rowData);
        listView1.setAdapter(customAdapter1);

        listView2 = findViewById(R.id.listView2);
        rowData = new ArrayList<>();
        addRowData2();
        customAdapter2 = new CustomAdapter2(context, rowData);
        listView2.setAdapter(customAdapter2);
        //bottom menu
        bottomNavigationView = findViewById(R.id.bottom_menu);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(4);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()){
                    case R.id.ic_home:
                        intent =new Intent(MainActivityMe.this,MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.silde_out_to_left);
                        break;
                    case R.id.ic_giohang:
                        intent =new Intent(MainActivityMe.this,MainActivityGioHang.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.silde_out_to_left);
                        break;
                    case R.id.bn_donhang:
                        break;
                    case R.id.bn_thongbao:
                        break;
                    case R.id.ic_me:

                        break;
                }
                return false;
            }
        });
    }



    private void addRowData() {
        for (int i = 0 ; i<= 5; i++) {
            rowModel = new RowModel(img[i],a[i]);
            rowData.add(rowModel);
        }

    }
    private void addRowData1() {
        for (int i = 6 ; i<= 7; i++) {
            rowModel = new RowModel(img[i],a[i]);
            rowData.add(rowModel);
        }

    }
    private void addRowData2() {
        for (int i = 8 ; i<= 9; i++) {
            rowModel = new RowModel(img[i],a[i]);
            rowData.add(rowModel);
        }
    }
}