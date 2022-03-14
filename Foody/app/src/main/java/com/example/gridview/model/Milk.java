package com.example.gridview.model;

public class Milk {
    private byte[] mAnh;
    private int mcheck;
    private String mtenquan;
    private String mdiachi;
    private String mdanhgia;
    private String mkhac;
    private int ID;

    public byte[] getmAnh() {
        return mAnh;
    }

    public void setmAnh(byte[] mAnh) {
        this.mAnh = mAnh;
    }

    public int getMcheck() {
        return mcheck;
    }

    public void setMcheck(int mcheck) {
        this.mcheck = mcheck;
    }

    public String getMtenquan() {
        return mtenquan;
    }

    public void setMtenquan(String mtenquan) {
        this.mtenquan = mtenquan;
    }

    public String getMdiachi() {
        return mdiachi;
    }

    public void setMdiachi(String mdiachi) {
        this.mdiachi = mdiachi;
    }

    public String getMdanhgia() {
        return mdanhgia;
    }

    public void setMdanhgia(String mdanhgia) {
        this.mdanhgia = mdanhgia;
    }

    public String getMkhac() {
        return mkhac;
    }

    public void setMkhac(String mkhac) {
        this.mkhac = mkhac;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Milk(byte[] mAnh, int mcheck, String mtenquan, String mdiachi, String mdanhgia, String mkhac, int ID) {
        this.mAnh = mAnh;
        this.mcheck = mcheck;
        this.mtenquan = mtenquan;
        this.mdiachi = mdiachi;
        this.mdanhgia = mdanhgia;
        this.mkhac = mkhac;
        this.ID = ID;
    }
}
