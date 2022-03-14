package com.example.gridview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class SlideAdb extends SliderViewAdapter<SlideAdb.Holder> {
    List<byte[]> images;

    public SlideAdb(List<byte[]> images) {
        this.images = images;
    }

    @Override
    public SlideAdb.Holder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item,parent,false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(SlideAdb.Holder viewHolder, int position) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(images.get(position),0,images.get(position).length);
        viewHolder.imageView.setImageBitmap(bitmap);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    public class Holder extends SliderViewAdapter.ViewHolder {
        ImageView imageView;


        public Holder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_slider);


        }
    }
}
