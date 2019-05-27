package com.example.a12525.bhplanet;

public class zdongtai {
    private String tou1;
    private String dongname1;
    private String dongdate1;
    private String dongneirong1;
    private String zuozhetou;
    private String zuozheid;
    private String  dongimage1;
    private String zuopinming;
    private int zhuan1;
    private Number nzhuan1;
    private int ping1;
    private Number nping1;
    private int zan1;
    private Number nzan1;
    private String link_id;
//    private int is_ok;
//    private String user_id;
//    private String compose_id;

    public zdongtai(String tou1, String dongname1, String dongdate1, String dongneirong1,String zuozhetou,
                    String zuozheid, String dongimage1, String zuopinming, int zhuan1, Number nzhuan1,
                    int ping1, Number nping1, int zan1, Number nzan1,String link_id) {
        this.tou1 = tou1;
        this.dongname1 = dongname1;
        this.dongdate1 = dongdate1;
        this.dongneirong1 = dongneirong1;
        this.zuozhetou = zuozhetou;
        this.zuozheid = zuozheid;
        this.dongimage1 = dongimage1;
        this.zuopinming = zuopinming;
        this.zhuan1 = zhuan1;
        this.nzhuan1 = nzhuan1;
        this.ping1 = ping1;
        this.nping1 = nping1;
        this.zan1 = zan1;
        this.nzan1 = nzan1;
        this.link_id=link_id;
//        this.is_ok = is_ok;
//        this.user_id=user_id;
//        this.compose_id=compose_id;

    }

    public String getTou1() {
        return tou1;
    }

    public String getDongname1() {
        return dongname1;
    }

    public String getDongdate1() {
        return dongdate1;
    }

    public String getDongneirong1() {
        return dongneirong1;
    }

    public String getZuozhetou() {
        return zuozhetou;
    }

    public String getZuozheid() {
        return zuozheid;
    }

    public String getDongimage1() {
        return dongimage1;
    }

    public String getZuopinming() {
        return zuopinming;
    }

    public int getZhuan1() {
        return zhuan1;
    }

    public int getPing1() {
        return ping1;
    }

    public int getZan1() {
        return zan1;
    }

    public Number getNzhuan1() {
        return nzhuan1;
    }

    public Number getNping1() {
        return nping1;
    }

    public Number getNzan1() {
        return nzan1;
    }

    public String getLink_id() {
        return link_id;
    }
//    public int getIs_ok() {
//        return is_ok;
//    }
//
//    public String getCompose_id() {
//        return compose_id;
//    }
//
//    public String getUser_id() {
//        return user_id;
//    }
}
