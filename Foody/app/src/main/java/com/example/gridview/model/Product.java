package com.example.gridview.model;

public class Product {
    private int mId;
    private byte[] mImage ;
    private String mName;
    private int gia;

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public byte[] getmImage() {
        return mImage;
    }

    public void setmImage(byte[] mImage) {
        this.mImage = mImage;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public Product(int mId, byte[] mImage, String mName, int gia) {
        this.mId = mId;
        this.mImage = mImage;
        this.mName = mName;
        this.gia = gia;
    }
}
