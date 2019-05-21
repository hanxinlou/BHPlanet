package com.example.a12525.bhplanet;

public class pinglun {
    private String tou;
    private String name;
    private String time;
    private String pneirong;
    private String user_id;
    private String compose_id;
    private int zan;
    private int zan_num;
    private int is_ok;

    public pinglun(String tou, String name, String user_id, String time, String pneirong,
                   int zan, int zan_num, String compose_id, int is_ok){
        this.tou = tou;
        this.name = name;
        this.user_id = user_id;
        this.time = time;
        this.pneirong = pneirong;
        this.zan = zan;
        this.zan_num = zan_num;
        this.compose_id = compose_id;
        this.is_ok = is_ok;
    }

    public String getTou() {
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
    public String getUser_id() { return user_id; }
    public String getCompose_id() { return compose_id; }
    public int getZan() { return zan; }
    public int getZan_num() { return zan_num; }
    public int getIs_ok() { return is_ok; }
}