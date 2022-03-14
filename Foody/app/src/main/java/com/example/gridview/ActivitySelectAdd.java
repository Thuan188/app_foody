package com.example.gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ActivitySelectAdd extends AppCompatActivity {
    private ListView listView;
    private ArrayList<Address> addresses;
    TextView tvBack;


    private String[] mAddress = new  String[]{"TP. HCM","Hà Nội","Đà Nẵng", "Cần Thơ", "Hải Phòng", "Huế", "Khánh Hòa", "Đồng Nai","Nghệ An", "Vũng Tàu","Bắc Ninh","Bình Dương","Lâm Đồng","Quảng Nam", "Quảng Ninh","Thái Nguyên"};
    private String[] mNumAdd = new String[]{"49521 địa điểm","24559 địa điểm","6871 địa điểm","2433 địa điểm","2395 địa điểm","1306 địa điểm",
            "1484 địa điểm", "1685 địa điểm", "1436 địa điểm", "1304 địa điểm", "357 địa điểm", "1085 địa điểm", "693 địa điểm", "655 địa điểm", "656 địa điểm", "623 địa điểm",};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_add);
        AnhXa();
        AddressAdapter addressAdapter = new AddressAdapter(this,R.layout.list_row_select_add,addresses);
        listView.setAdapter(addressAdapter);
        tvBack = findViewById(R.id.tv_back);

        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String add_select = addresses.get(position).getmAdd().toString();
                Intent intent = new Intent(ActivitySelectAdd.this,MainActivity.class);
                intent.putExtra("ADDRESS_SELECTED",add_select);
                startActivity(intent);
            }
        });
    }

    private void AnhXa() {
            listView = findViewById(R.id.lv_selec_add);
            addresses = new ArrayList<>();
            for (int i = 0;i < mNumAdd.length;i++)
            {
                Address add =new Address(mAddress[i],mNumAdd[i]);
                addresses.add(add);
            }

    }
}