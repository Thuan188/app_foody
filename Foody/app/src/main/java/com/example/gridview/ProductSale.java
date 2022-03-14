package com.example.gridview;

public class ProductSale {
    private int ID;
    private byte[] mImage ;
    private String mName;
    private String mStore;
    private int mGiaMoi;
    private int mGiaCu;

    public ProductSale(int ID, byte[] mImage, String mName, String mStore, int mGiaMoi, int mGiaCu) {
        this.ID = ID;
        this.mImage = mImage;
        this.mName = mName;
        this.mStore = mStore;
        this.mGiaMoi = mGiaMoi;
        this.mGiaCu = mGiaCu;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public String getmStore() {
        return mStore;
    }

    public void setmStore(String mStore) {
        this.mStore = mStore;
    }

    public int getmGiaMoi() {
        return mGiaMoi;
    }

    public void setmGiaMoi(int mGiaMoi) {
        this.mGiaMoi = mGiaMoi;
    }

    public int getmGiaCu() {
        return mGiaCu;
    }

    public void setmGiaCu(int mGiaCu) {
        this.mGiaCu = mGiaCu;
    }
}
