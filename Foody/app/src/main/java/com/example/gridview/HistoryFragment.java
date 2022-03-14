package com.example.gridview;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.gridview.Adapter.HoaDonGroupAdater;
import com.example.gridview.model.GioHang;
import com.example.gridview.model.HoaDonGroup;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HistoryFragment extends Fragment {
    View view ;
    ExpandableListView expandableListView;
    List<HoaDonGroup> list;
    Map<HoaDonGroup,List<GioHang>> hoaDonGroupListMap;
    HoaDonGroupAdater adater;
    Button dathang;
    LinearLayout chuamh;
    Toolbar toolbar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_history,container,false);
        expandableListView = view.findViewById(R.id.danhsachhd);
        if (MainActivity.sdt==""){
            expandableListView.setVisibility(View.GONE);
            chuamh.setVisibility(View.VISIBLE);
        }
        else {
            Cursor idTK = MainActivity.database.rawQuery("Select * from TaiKhoan where SoDienThoai='"+ MainActivity.sdt+"'",null);
            idTK.moveToFirst();
            Cursor cursor = MainActivity.database.rawQuery("Select * from HoaDon where ID_TK="+idTK.getInt(0),null);
            cursor.moveToFirst();
            if(cursor.getCount()!=0){

                expandableListView.setVisibility(View.VISIBLE);
                chuamh.setVisibility(View.GONE);
                hoaDonGroupListMap = setData();
                list = new ArrayList<>(hoaDonGroupListMap.keySet());
                adater = new HoaDonGroupAdater(list,hoaDonGroupListMap);
                adater.notifyDataSetChanged();
                expandableListView.setAdapter(adater);
            }
            else {
                expandableListView.setVisibility(View.GONE);
                chuamh.setVisibility(View.VISIBLE);
            }
        }
        return view;
    }
    private Map<HoaDonGroup, List<GioHang>> setData(){
        Map<HoaDonGroup,List<GioHang>> listMap = new LinkedHashMap<>();
        Cursor idTK = MainActivity.database.rawQuery("Select * from TaiKhoan where SoDienThoai='"+ MainActivity.sdt+"'",null);
        idTK.moveToFirst();
        Cursor cursor = MainActivity.database.rawQuery("Select * from HoaDon where ID_TK="+idTK.getInt(0),null);
        while (cursor.moveToNext()){
            HoaDonGroup hoaDonGroup = new HoaDonGroup(cursor.getInt(0),cursor.getString(3),cursor.getInt(2));
            Cursor chitietHoaDon = MainActivity.database.rawQuery("Select * from ChiTietHoaDon where ID_HoaDon ="+cursor.getInt(0),null);
            List<GioHang> list = new ArrayList<>();
            while (chitietHoaDon.moveToNext()){
                GioHang product = new GioHang(chitietHoaDon.getInt(3),chitietHoaDon.getInt(1),chitietHoaDon.getString(5),chitietHoaDon.getInt(2),chitietHoaDon.getInt(4));
                list.add(product);
            }
            listMap.put(hoaDonGroup,list);
        }

        return listMap;
    }
}
