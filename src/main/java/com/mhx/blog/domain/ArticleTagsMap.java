package com.mhx.blog.domain;

public class ArticleTagsMap {
    private Integer id;
    private Integer aid;
    private Integer tid;

    public ArticleTagsMap(Integer aid, Integer tid) {
        this.aid = aid;
        this.tid = tid;
    }

    public ArticleTagsMap(Integer id, Integer aid, Integer tid) {
        this.id = id;
        this.aid = aid;
        this.tid = tid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    @Override
    public String toString() {
        return "ArticleTagsMap{" +
                "id=" + id +
                ", aid=" + aid +
                ", tid=" + tid +
                '}';
    }
}
