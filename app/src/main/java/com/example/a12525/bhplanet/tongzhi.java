package com.example.a12525.bhplanet;

public class tongzhi {
    private int tongtou;
    private String tongid;
    private String tongday;
    private String tongtime;
    private String tongneirong;

    public tongzhi(int tongtou, String tongid,String tongday,String tongtime,String tongneirong) {
        this.tongtou = tongtou;
        this.tongid = tongid;
        this.tongday = tongday;
        this.tongtime = tongtime;
        this.tongneirong = tongneirong;
    }

    public int getTongtou() {
        return tongtou;
    }

    public String getTongid() {
        return tongid;
    }

    public String getTongday() {
        return tongday;
    }

    public String getTongtime() {
        return tongtime;
    }

    public String getTongneirong() {
        return tongneirong;
    }
}