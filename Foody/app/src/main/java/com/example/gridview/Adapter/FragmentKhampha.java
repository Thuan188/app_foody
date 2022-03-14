package com.example.gridview.Adapter;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gridview.ChiTietTraSua;
import com.example.gridview.MainActivity;
import com.example.gridview.R;
import com.example.gridview.model.Shop;

import java.util.ArrayList;

public class FragmentKhampha extends Fragment {
    private View mRootView;
    String dieukien;

    public FragmentKhampha(String dieukien) {
        this.dieukien = dieukien;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.activity_khampha,container,false);


        final ArrayList<Shop> arrayList = new ArrayList<>();
        Cursor cursor = MainActivity.database.rawQuery("SELECT Shop.idShop,tenShop,anhShop,chitietDiaChi,round(avg(SanPham.sao),1) FROM SanPham,Shop,ChiTietShop "+dieukien,null);
        while (cursor.moveToNext()){
            arrayList.add(new Shop(cursor.getInt(0), (byte[]) cursor.getBlob(2),cursor.getString(1), cursor.getString(3),cursor.getString(4)));
        }

        ListView lv = (ListView) mRootView.findViewById(R.id.lvgoiy);
        final AdapterKhamPhaListView customAdapter = new AdapterKhamPhaListView(mRootView.getContext(),R.layout.item_lv_khampha,arrayList);

        lv.setAdapter(customAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(view.getContext(), ChiTietTraSua.class);
                startActivity(intent);

            }
        });


        return mRootView;
    }
}
