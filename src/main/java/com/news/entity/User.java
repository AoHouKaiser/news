package com.news.entity;

public class User {
    @Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", pwd=" + pwd
				+ ", tel=" + tel + ", email=" + email + ", bak1=" + bak1
				+ ", bak2=" + bak2 + ", bak3=" + bak3 + ", bak4=" + bak4 + "]";
	}

	private Integer uid;

    private String uname;

    private String pwd;

    private Integer tel;

    private String email;

    private Integer bak1;

    private Integer bak2;

    private String bak3;

    private String bak4;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public Integer getTel() {
        return tel;
    }

    public void setTel(Integer tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
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