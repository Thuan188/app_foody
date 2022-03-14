package com.example.gridview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class FragmentAdapter extends FragmentStatePagerAdapter {
    private String listTab[]= {"Đang đến", "Đơn nháp"};
    private DangDenFragment dangDenFragment;
    private DonNhapFragment donNhapFragment;
//    private HistoryFragment historyFragment;

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
        dangDenFragment = new DangDenFragment();
        donNhapFragment = new DonNhapFragment();
//        historyFragment = new HistoryFragment();
    }

    @NonNull
    @Override

    public Fragment getItem(int position) {
        if(position==1){
            return donNhapFragment;
        }
        return dangDenFragment;
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
