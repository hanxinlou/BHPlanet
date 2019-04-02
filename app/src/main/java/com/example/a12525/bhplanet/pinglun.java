package com.example.a12525.bhplanet;

public class pinglun {
    private int tou;
    private String name;
    private String time;
    private String pneirong;
    private int zan;
    private String zann;
    private String huifu;
    private String chakan;

    public pinglun(int tou,String name,String time,String pneirong,int zan,String zann,String huifu,String chakan){
        this.tou=tou;
        this.name=name;
        this.time=time;
        this.pneirong=pneirong;
        this.zan=zan;
        this.zann=zann;
        this.huifu=huifu;
        this.chakan=chakan;
    }

    public int getTou() {
        return tou;
    }

    public String getName() {
        return name;
    }
    public String getTime() {
        return time;
    }
    public String getPneirong() {
        return pneirong;
    }
    public int getZan() {
        return zan;
    }

    public String getZann() {
        return zann;
    }

    public String getHuifu() {
        return huifu;
    }

    public String getChakan() {
        return chakan;
    }
}