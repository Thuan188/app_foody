package com.example.gridview.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.gridview.Fragment.FragmentGoiY;

import java.util.List;


public class AdapterRiceTabLayout extends FragmentStatePagerAdapter {
    private String listTab[] = {"Gần Tôi" , "Bán Chạy", "Đánh Giá", "Giao Nhanh"};
    private List<String> dieukien;

    public AdapterRiceTabLayout(@NonNull FragmentManager fm,  List<String> dieukien) {
        super(fm);
        this.dieukien = dieukien;
    }

    public AdapterRiceTabLayout(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
            return new FragmentGoiY(dieukien.get(position));
    }

    @Override
    public int getCount() {
        return listTab.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTab[position];
    }
}
