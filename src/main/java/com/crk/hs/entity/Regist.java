package com.crk.hs.entity;

import java.util.Date;

public class Regist {
    private String bsm;

    private String mTitle;

    private String uName;

    private String uCardid;

    private String content;

    private Date begintime;

    private Date overtime;

    public String getBsm() {
        return bsm;
    }

    public void setBsm(String bsm) {
        this.bsm = bsm == null ? null : bsm.trim();
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle == null ? null : mTitle.trim();
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName == null ? null : uName.trim();
    }

    public String getuCardid() {
        return uCardid;
    }

    public void setuCardid(String uCardid) {
        this.uCardid = uCardid == null ? null : uCardid.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public Date getOvertime() {
        return overtime;
    }

    public void setOvertime(Date overtime) {
        this.overtime = overtime;
    }
}