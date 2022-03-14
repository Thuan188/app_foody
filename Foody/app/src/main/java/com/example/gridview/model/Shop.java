package com.example.gridview.model;

public class Shop {
    private int mId;
    private byte[] mImage ;
    private String mName;
    private String mAdd;
    private String mSao;

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

    public String getmAdd() {
        return mAdd;
    }

    public void setmAdd(String mAdd) {
        this.mAdd = mAdd;
    }

    public String getmSao() {
        return mSao;
    }

    public void setmSao(String mSao) {
        this.mSao = mSao;
    }

    public Shop(int mId, byte[] mImage, String mName, String mAdd, String mSao) {
        this.mId = mId;
        this.mImage = mImage;
        this.mName = mName;
        this.mAdd = mAdd;
        this.mSao = mSao;
    }
}
