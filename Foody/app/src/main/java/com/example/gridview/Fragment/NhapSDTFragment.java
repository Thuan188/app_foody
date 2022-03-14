package com.example.gridview.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.gridview.DangNhapActivity;
import com.example.gridview.MainActivity;
import com.example.gridview.R;


public class NhapSDTFragment extends Fragment {
    Button btn_tieptuc;
    EditText editText;
    TextView boqua_355;
    TextView canhBao_355;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_nhap_s_d_t, container, false);
        btn_tieptuc = view.findViewById(R.id.tieptuc_dangnhap_355);
        editText = view.findViewById(R.id.sdt_nhapvao_355);
        canhBao_355 = view.findViewById(R.id.canhbao_sdt_355);
        canhBao_355.setVisibility(View.GONE);
        boqua_355 = view.findViewById(R.id.boqua_dangnhap_355);
        boqua_355.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);

                startActivity(intent);
            }
        });
        if(MainActivity.sdt!=""){
            editText.setText(MainActivity.sdt);
            MainActivity.sdt="";
        }
        btn_tieptuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().length()<10)
                {
                    canhBao_355.setVisibility(View.VISIBLE);

                }
                else {
                    Cursor cursor = MainActivity.database.rawQuery("select * from TaiKhoan where SDT ='"+editText.getText().toString()+"'",null);
                    MainActivity.sdt = editText.getText().toString();
                    if(cursor.getCount()>0){
                        Intent intent = new Intent(getActivity(),MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        DangNhapActivity.fragmentManager = getActivity().getSupportFragmentManager();
                        DangNhapActivity.fragmentManager.beginTransaction().replace(R.id.dangnhap_fragment, new ThongTinDangNhapFragment()).commit();
                    }
                }

            }
        });
        // Inflate the layout for this fragment
        return view ;
    }
}