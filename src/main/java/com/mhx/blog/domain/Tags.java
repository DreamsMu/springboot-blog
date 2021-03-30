package com.mhx.blog.domain;

public class Tags {
    private Integer id;
    private String img;
    private String name;
    private Integer tidCount;

    public Tags(String name) {
        this.name = name;
    }

    public Tags(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Tags(Integer id, String img, String name) {
        this.id = id;
        this.img = img;
        this.name = name;
    }

    public Tags() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTidCount() {
        return tidCount;
    }

    public void setTidCount(Integer tidCount) {
        this.tidCount = tidCount;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Tags{" +
                "id=" + id +
                ", img='" + img + '\'' +
                ", name='" + name + '\'' +
                ", tidCount=" + tidCount +
                '}';
    }
}
