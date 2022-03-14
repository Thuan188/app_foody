package com.example.gridview.Fragment;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.gridview.MainActivity;
import com.example.gridview.R;


public class ThongTinDangNhapFragment extends Fragment {

    Button button;
    EditText ho_355;
    EditText ten_355;
    EditText ngaySinh_355;
    EditText diachi_355;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_tin_dang_nhap, container, false);
        button = view.findViewById(R.id.hoantat_355);
        ho_355 = view.findViewById(R.id.ho_355);
        ten_355 =view.findViewById(R.id.ten_355);
        ngaySinh_355 = view.findViewById(R.id.ngaysinh_355);
        diachi_355 = view.findViewById(R.id.diachi_355);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ContentValues contentValues_355 =new ContentValues();
                contentValues_355.put("SDT", MainActivity.sdt);
                contentValues_355.put("HoVaTen",ho_355.getText().toString()+" "+ten_355.getText().toString());
                contentValues_355.put("TinhThanh",diachi_355.getText().toString());
                MainActivity.database.insert("TaiKhoan",null,contentValues_355);
                Intent intent = new Intent(getActivity(), MainActivity.class);

                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}