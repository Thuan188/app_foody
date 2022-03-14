package com.example.gridview;

public class Recyler  {
    private byte[] pic;
    private int id;
    private String rc_name;

    public Recyler(byte[] pic, int id, String rc_name) {
        this.pic = pic;
        this.id = id;
        this.rc_name = rc_name;
    }


    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRc_name() {
        return rc_name;
    }

    public void setRc_name(String rc_name) {
        this.rc_name = rc_name;
    }
}
