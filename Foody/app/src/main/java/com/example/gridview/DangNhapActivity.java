package com.example.gridview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;

import com.example.gridview.Fragment.NhapSDTFragment;

public class DangNhapActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.dangnhap_fragment, new NhapSDTFragment()).commit();
        toolbar =findViewById(R.id.toolbar_dangnhap_355);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.sdt==""){
                    finish();
                }
                else {
                    fragmentManager.beginTransaction().replace(R.id.dangnhap_fragment, new NhapSDTFragment()).commit();
                }
            }
        });

    }
}