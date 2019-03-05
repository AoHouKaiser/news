package com.news.entity;

public class Manager {
    @Override
	public String toString() {
		return "Manager [mid=" + mid + ", mname=" + mname + ", pwd=" + pwd
				+ ", level=" + level + "]";
	}

	private Integer mid;

    private String mname;

    private String pwd;

    private Integer level;

    private Integer bak1;

    private Integer bak2;

    private String bak3;

    private String bak4;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname == null ? null : mname.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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