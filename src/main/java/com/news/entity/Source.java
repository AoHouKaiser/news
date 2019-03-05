package com.news.entity;

public class Source {
    private Integer iid;

    private Integer nid;

    private Integer type;

    private String src;

    private Integer bak1;

    private Integer bak2;

    private String bak3;

    private String bak4;

    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src == null ? null : src.trim();
    }

    public Integer getBak1() {
        return bak1;
    }

    public void setBak1(Integer bak1) {
        this.bak1 = bak1;
    }

    public Integer getBak2() {
        return bak2;
    }

    public void setBak2(Integer bak2) {
        this.bak2 = bak2;
    }

    public String getBak3() {
        return bak3;
    }

    public void setBak3(String bak3) {
        this.bak3 = bak3 == null ? null : bak3.trim();
    }

    public String getBak4() {
        return bak4;
    }

    public void setBak4(String bak4) {
        this.bak4 = bak4 == null ? null : bak4.trim();
    }
}