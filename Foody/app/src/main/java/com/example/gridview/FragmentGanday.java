package com.example.gridview;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gridview.Adapter.AdapterBoSuuTapListView;
import com.example.gridview.model.Shop;

import java.util.ArrayList;

public class FragmentGanday extends Fragment {
    private View mRootView;
    String dieukien;

    public FragmentGanday(String dieukien) {
        this.dieukien = dieukien;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.activity_ganday,container,false);


        final ArrayList<Shop> arrayList = new ArrayList<>();
        Cursor cursor = MainActivity.database.rawQuery("SELECT Shop.idShop,tenShop,anhShop,chitietDiaChi,round(avg(SanPham.sao),1) FROM SanPham,Shop,ChiTietShop "+dieukien,null);
        while (cursor.moveToNext()){
            arrayList.add(new Shop(cursor.getInt(0), (byte[]) cursor.getBlob(2),cursor.getString(1), cursor.getString(3),cursor.getString(4)));
        }

        GridView gr = (GridView) mRootView.findViewById(R.id.lvgoiy);
        final AdapterBoSuuTapListView customAdapter = new AdapterBoSuuTapListView(mRootView.getContext(),R.layout.item_gr_ganday,arrayList);

        gr.setAdapter(customAdapter);

        gr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(view.getContext(), ChiTietTraSua.class);
                startActivity(intent);

            }
        });


        return mRootView;
    }
}
