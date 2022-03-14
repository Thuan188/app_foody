package com.example.gridview.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.gridview.R;
import com.example.gridview.model.GioHang;
import com.example.gridview.model.HoaDonGroup;
import com.example.gridview.model.Product;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class HoaDonGroupAdater extends BaseExpandableListAdapter {
    private List<HoaDonGroup> hoaDonGroupList;
    private Map<HoaDonGroup, List<GioHang>> hoaDonGroupListMap;

    public HoaDonGroupAdater(List<HoaDonGroup> hoaDonGroupList, Map<HoaDonGroup, List<GioHang>> hoaDonGroupListMap) {
        this.hoaDonGroupList = hoaDonGroupList;
        this.hoaDonGroupListMap = hoaDonGroupListMap;
    }



    @Override
    public int getGroupCount() {
        return hoaDonGroupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return hoaDonGroupListMap.get(hoaDonGroupList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return hoaDonGroupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return hoaDonGroupListMap.get(hoaDonGroupList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        HoaDonGroup hoaDonGroup = hoaDonGroupList.get(groupPosition);
        return hoaDonGroup.getMaHoaDon_355();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        GioHang product = hoaDonGroupListMap.get(hoaDonGroupList.get(groupPosition)).get(childPosition);
        return product.getmIdSP();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView  = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_hoadon,parent,false);
        TextView maHoaDon = convertView.findViewById(R.id.mahoadon);
        TextView ngayMua= convertView.findViewById(R.id.ngaymua);
        TextView tongTien = convertView.findViewById(R.id.tongtien_hoadon);
        HoaDonGroup hoaDonGroup = hoaDonGroupList.get(groupPosition);
        maHoaDon.setText("Mã Đơn Hàng: "+hoaDonGroup.getMaHoaDon_355());
        ngayMua.setText(""+hoaDonGroup.getNgayMua_355());
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String str1 = currencyVN.format(hoaDonGroup.getTongTien_355()*1000);
        tongTien.setText(str1);
        notifyDataSetChanged();
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView  = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_giohang,parent,false);
        GioHang product= hoaDonGroupListMap.get(hoaDonGroupList.get(groupPosition)).get(childPosition);
        TextView name_sp_355 = convertView.findViewById(R.id.name_sp_giohang_355);
        TextView soluong_355 =convertView.findViewById(R.id.soluong_giohang);
        TextView gia_sp_355 = convertView.findViewById(R.id.tongtien_giohang_355);

        name_sp_355.setText(product.getmIdSP());
        gia_sp_355.setText(product.getGia() +".000 đ");
        soluong_355.setText(product.getSoluong()+"");
        notifyDataSetChanged();
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
