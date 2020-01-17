package com.crk.hs.entity;

import java.util.Date;

public class Jsj {
    private String bsm;

    private Date time;

    private String placeb;

    private String placea;

    private String uBsm;

    public String getuBsm() {
        return uBsm;
    }

    public void setuBsm(String uBsm) {
        this.uBsm = uBsm;
    }

    public String getCarno() {
        return carno;
    }

    public void setCarno(String carno) {
        this.carno = carno;
    }

    private String carno; //这里存的是carno

    private Integer totals;

    private String content;

    public String getBsm() {
        return bsm;
    }

    public void setBsm(String bsm) {
        this.bsm = bsm == null ? null : bsm.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getPlaceb() {
        return placeb;
    }

    public void setPlaceb(String placeb) {
        this.placeb = placeb == null ? null : placeb.trim();
    }

    public String getPlacea() {
        return placea;
    }

    public void setPlacea(String placea) {
        this.placea = placea == null ? null : placea.trim();
    }

    public Integer getTotals() {
        return totals;
    }

    public void setTotals(Integer totals) {
        this.totals = totals;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}