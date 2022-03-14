package com.example.gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AddressAdapter extends ArrayAdapter<Address> {
    private ArrayList<Address> addresses;
    private Context context;
    private int resource;
    public AddressAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Address> objects) {
        super(context, resource, objects);
        this.addresses = objects;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.list_row_select_add,parent,false);
        TextView Addr = convertView.findViewById(R.id.addr_slect);
        TextView NumAdd = convertView.findViewById(R.id.num_add);
        Address address = addresses.get(position);
        Addr.setText(address.getmAdd());
        NumAdd.setText(address.getmNumAdd());

        return convertView;
    }
}
