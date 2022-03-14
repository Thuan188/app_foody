package com.example.gridview.model;

public class GioHang {
    private int mIdSP;
    private byte[] mImage ;
    private String mName;
    private int gia;
    private int IDshop;
    private int soluong;
    private int thanhtien;




    public int getmIdSP() {
        return mIdSP;
    }

    public void setmIdSP(int mIdSP) {
        this.mIdSP = mIdSP;
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

    public int getIDshop() {
        return IDshop;
    }

    public void setIDshop(int IDshop) {
        this.IDshop = IDshop;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getThanhtien() {
        return soluong*gia;
    }

    public void setThanhtien(int thanhtien) {
        this.thanhtien = soluong*gia;
    }

    public GioHang(int mIdSP, byte[] mImage, String mName, int gia, int IDshop, int soluong) {
        this.mIdSP = mIdSP;
        this.mImage = mImage;
        this.mName = mName;
        this.gia = gia;
        this.IDshop = IDshop;
        this.soluong = soluong;
    }
    public GioHang(int mIdSP, int IDshop, String mName, int soluong, int gia) {
        this.mIdSP = mIdSP;

        this.mName = mName;
        this.gia = gia;
        this.IDshop = IDshop;
        this.soluong = soluong;
    }
}
