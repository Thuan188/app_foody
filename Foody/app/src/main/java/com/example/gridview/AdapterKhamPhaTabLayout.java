package com.example.gridview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.example.gridview.Adapter.FragmentKhampha;
import com.example.gridview.Fragment.FragmentGoiY;

import java.util.List;

public class AdapterKhamPhaTabLayout extends FragmentStatePagerAdapter {
    private String listTab[] = {""};
    private List<String> dieukien;

    public AdapterKhamPhaTabLayout(@NonNull FragmentManager fm, List<String> dieukien) {
        super(fm);
        this.dieukien = dieukien;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new FragmentKhampha(dieukien.get(position));
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTab[position];
    }
}
