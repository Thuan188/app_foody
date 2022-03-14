package com.example.gridview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gridview.Adapter.GioHangAdapter;
import com.example.gridview.model.GioHang;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class GioHangActivity extends AppCompatActivity {
    static RecyclerView recyclerView;
    static TextView thanhTien;
    static TextView value_TongTien;
    static TextView value_TongTien_2,value_TongTien_3;
    EditText hovaten_355;
    EditText sdt_355;
    TextView diachi_355;
    TextView back_btn;
    RelativeLayout dathang_355;
    static int tongTien=0;
    static int idshop;
    public static GioHangAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        thanhTien = findViewById(R.id.thanhtien_giohang_355);
        value_TongTien = findViewById(R.id.value_tongtien);
        back_btn = findViewById(R.id.icon_back);
        dathang_355 =findViewById(R.id.btn_dathang_355);
        value_TongTien_2 = findViewById(R.id.tongtien_02_355);
        value_TongTien_3 = findViewById(R.id.tongtien);
        recyclerView =findViewById(R.id.rcv_chitietdonhang_355);
        hovaten_355 = findViewById(R.id.hovaten_355);
        sdt_355 = findViewById(R.id.sdt_355);
        diachi_355 = findViewById(R.id.address_355);
        idshop = getIntent().getIntExtra("IDSHOP",0);
        if(MainActivity.sdt ==""){
            value_TongTien_2.setVisibility(View.GONE);
            hovaten_355.setText("");
            sdt_355.setText("");
            diachi_355.setText("Bạn chưa chọn địa chỉ");
        }
        else {
            Cursor infor = MainActivity.database.rawQuery("Select * from TaiKhoan Where SDT = '"+MainActivity.sdt+"'",null);
            infor.moveToFirst();
            hovaten_355.setText("   "+infor.getString(1));
            sdt_355.setText("   "+infor.getString(0));
            diachi_355.setText(infor.getString(4)+", "+infor.getString(3)+", "+infor.getString(2));
            value_TongTien_2.setVisibility(View.VISIBLE);
        }
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChiTietTraSua.kiemtraGH();
                finish();

            }
        });
        MainActivity.database = Database.initDatabase(this,MainActivity.DATABASE_NAME);
        setTongTien(this);
        dathang_355.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.sdt ==""){
                    Intent intent = new Intent(GioHangActivity.this,DangNhapActivity.class);
                    startActivity(intent);
                }
                else {
                    ContentValues contentValues;
                    contentValues =new ContentValues();
                    contentValues.put("SDT",MainActivity.sdt);
                    contentValues.put("TongTien",tongTien);

                    contentValues.put("IDShop",getIntent().getIntExtra("IDSHOP",0));
                    Date c = Calendar.getInstance().getTime();
                    System.out.println("Current time => " + c);

                    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    String formattedDate = df.format(c);
                    contentValues.put("NgayMua",formattedDate);
                    MainActivity.database.insert("HoaDon",null,contentValues);
                    Cursor cursor = MainActivity.database.rawQuery("Select * from HoaDon where SDT="+ MainActivity.sdt +" and NgayMua='"+formattedDate+"'",null);
                    cursor.moveToFirst();
                    for(int i =0;i<ChiTietTraSua.gioHangArrayList.size();i++)
                    {
                        if (ChiTietTraSua.gioHangArrayList.get(i).getIDshop()==getIntent().getIntExtra("IDSHOP",0))
                        {
                            contentValues =new ContentValues();
                            contentValues.put("IDHoaDon",cursor.getInt(0));
                            contentValues.put("IDSP",ChiTietTraSua.gioHangArrayList.get(i).getmIdSP());
                            contentValues.put("ThanhTien",ChiTietTraSua.gioHangArrayList.get(i).getThanhtien());
                            contentValues.put("TenSP",ChiTietTraSua.gioHangArrayList.get(i).getmName());
                            contentValues.put("SoLuong",ChiTietTraSua.gioHangArrayList.get(i).getSoluong());
                            MainActivity.database.insert("ChiTietHoaDon",null,contentValues);
                            ChiTietTraSua.gioHangArrayList.remove(i);
                        }

                    }
                    ChiTietTraSua.kiemtraGH();
                    Toast.makeText(GioHangActivity.this,"Đặt hàng thành công! Mã đơn hàng : "+cursor.getInt(0),Toast.LENGTH_SHORT).show();
                    finish();

                }
            }
        });
    }
    public static void setTongTien(Context context){
        tongTien=0;
        adapter = new GioHangAdapter();
        ArrayList<GioHang> arrayList = new ArrayList<>();
        for(int i =0;i<ChiTietTraSua.gioHangArrayList.size();i++)
        {
            GioHang gioHang = ChiTietTraSua.gioHangArrayList.get(i);
            if (ChiTietTraSua.gioHangArrayList.get(i).getIDshop()==idshop)
            {
                arrayList.add(gioHang);
            }

        }
        adapter.setData(context,arrayList);
        LinearLayoutManager linearLayoutManager = new  LinearLayoutManager(context,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        for(int i =0;i<ChiTietTraSua.gioHangArrayList.size();i++)
        {
            if (ChiTietTraSua.gioHangArrayList.get(i).getIDshop()==idshop)
                tongTien = tongTien + ChiTietTraSua.gioHangArrayList.get(i).getThanhtien();
        }
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String str1 = currencyVN.format(tongTien*1000);
        thanhTien.setText(str1);
        value_TongTien.setText(str1);
        value_TongTien_3.setText(str1);
    }
}