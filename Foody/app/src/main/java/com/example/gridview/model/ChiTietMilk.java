package com.example.gridview.model;

public class ChiTietMilk {
    private int mmilk;
    private int madd;
    private String mtenmilk;
    private String mdanhgiamilk;
    private String mgiamilk;

    public ChiTietMilk(int mmilk, int madd, String mtenmilk, String mdanhgiamilk, String mgiamilk) {
        this.mmilk = mmilk;
        this.madd = madd;
        this.mtenmilk = mtenmilk;
        this.mdanhgiamilk = mdanhgiamilk;
        this.mgiamilk = mgiamilk;
    }

    public int getMmilk() {
        return mmilk;
    }

    public void setMmilk(int mmilk) {
        this.mmilk = mmilk;
    }

    public int getMadd() {
        return madd;
    }

    public void setMadd(int madd) {
        this.madd = madd;
    }

    public String getMtenmilk() {
        return mtenmilk;
    }

    public void setMtenmilk(String mtenmilk) {
        this.mtenmilk = mtenmilk;
    }

    public String getMdanhgiamilk() {
        return mdanhgiamilk;
    }

    public void setMdanhgiamilk(String mdanhgiamilk) {
        this.mdanhgiamilk = mdanhgiamilk;
    }

    public String getMgiamilk() {
        return mgiamilk;
    }

    public void setMgiamilk(String mgiamilk) {
        this.mgiamilk = mgiamilk;
    }
}
