package com.news.entity;

import java.util.Date;

public class News {
    @Override
	public String toString() {
		return "News [nid=" + nid + ", author=" + author + ", title=" + title
				+ ", ntext=" + ntext + ", ntime=" + ntime + ", ntid=" + ntid
				+ ", isreview=" + isreview + ", hot=" + hot + "]";
	}

	private Integer nid;

    private String author;

    private String title;

    private String ntext;

    private Date ntime;

    private Integer ntid;

    private Integer isreview;

    private Integer hot;

    private Integer bak1;

    private Integer bak2;

    private String bak3;

    private String bak4;

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getNtext() {
        return ntext;
    }

    public void setNtext(String ntext) {
        this.ntext = ntext == null ? null : ntext.trim();
    }

    public Date getNtime() {
        return ntime;
    }

    public void setNtime(Date ntime) {
        this.ntime = ntime;
    }

    public Integer getNtid() {
        return ntid;
    }

    public void setNtid(Integer ntid) {
        this.ntid = ntid;
    }

    public Integer getIsreview() {
        return isreview;
    }

    public void setIsreview(Integer isreview) {
        this.isreview = isreview;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
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