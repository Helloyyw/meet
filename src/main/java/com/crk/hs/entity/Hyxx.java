package com.crk.hs.entity;

import java.util.Date;

public class Hyxx {
    private String bsm;

    private String name;

    private Integer totals;

    private String mroomBsm;

    private Date startTime;
    private Date overTime;
    private String uBsm;

    private String content;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getOverTime() {
        return overTime;
    }

    public void setOverTime(Date overTime) {
        this.overTime = overTime;
    }

    public String getBsm() {
        return bsm;
    }

    public void setBsm(String bsm) {
        this.bsm = bsm == null ? null : bsm.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getTotals() {
        return totals;
    }

    public void setTotals(Integer totals) {
        this.totals = totals;
    }

    public String getMroomBsm() {
        return mroomBsm;
    }

    public void setMroomBsm(String mroomBsm) {
        this.mroomBsm = mroomBsm == null ? null : mroomBsm.trim();
    }

    public String getuBsm() {
        return uBsm;
    }

    public void setuBsm(String uBsm) {
        this.uBsm = uBsm == null ? null : uBsm.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}