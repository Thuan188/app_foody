package com.example.gridview;

public class InforList {
    private byte[] gImage;
    private String gName;

    public byte[] getgImage() {
        return gImage;
    }

    public void setgImage(byte[] gImage) {
        this.gImage = gImage;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public InforList(byte[] gImage, String gName) {
        this.gImage = gImage;
        this.gName = gName;
    }
}
