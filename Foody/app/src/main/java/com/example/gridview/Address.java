package com.example.gridview;

public class Address {
    private String mAdd;
    private String mNumAdd;

    public Address(String mAdd, String mNumAdd) {
        this.mAdd = mAdd;
        this.mNumAdd = mNumAdd;
    }

    public String getmAdd() {
        return mAdd;
    }

    public void setmAdd(String mAdd) {
        this.mAdd = mAdd;
    }

    public String getmNumAdd() {
        return mNumAdd;
    }

    public void setmNumAdd(String mNumAdd) {
        this.mNumAdd = mNumAdd;
    }
}
