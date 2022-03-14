package com.example.gridview;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivityGioHang extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_giohang);

        initView();
        bottomNavigationView = findViewById(R.id.bottom_menu);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()){
                    case R.id.ic_home:
                        intent =new Intent(MainActivityGioHang.this,MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.silde_out_to_left);
                        break;
                    case R.id.ic_giohang:
                        break;
                    case R.id.bn_donhang:
                        break;
                    case R.id.bn_thongbao:
                        break;
                    case R.id.ic_me:
                        intent =new Intent(MainActivityGioHang.this,MainActivityMe.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.silde_out_to_left);
                        break;
                }
                return false;
            }
        });
    }

    private void initView() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.vp_child);
        FragmentAdapter myAdapter = new FragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myAdapter);
        TabLayout tablayout =(TabLayout) findViewById(R.id.tabLayout_child);
        tablayout.setupWithViewPager(viewPager);
    }


}