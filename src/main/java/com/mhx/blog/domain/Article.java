package com.mhx.blog.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Article {
    private Integer id;
    private String title;
    private String content;
    private String description;
    private String author;
    private String rusername;
    private String img;
    private String copyright;
    private Integer traffic;
    private Integer cid;
    private Date time;
    private Tags tags;
    private Category category;

    public Article(Integer id, String title, String content, String description, String author, String rusername, String img, String copyright, Integer traffic, Integer cid,  Tags tags, Category category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.description = description;
        this.author = author;
        this.rusername = rusername;
        this.img = img;
        this.copyright = copyright;
        this.traffic = traffic;
        this.cid = cid;
        this.tags = tags;
        this.category = category;
    }

    public Article() {
    }

    public Article(Integer id) {
        this.id = id;
    }

    public Article(Integer id, Integer traffic) {
        this.id = id;
        this.traffic = traffic;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getRusername() {
        return rusername;
    }

    public void setRusername(String rusername) {
        this.rusername = rusername;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Integer getTraffic() {
        return traffic;
    }

    public void setTraffic(Integer traffic) {
        this.traffic = traffic;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Tags getTags() {
        return tags;
    }

    public void setTags(Tags tags) {
        this.tags = tags;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", rusername='" + rusername + '\'' +
                ", img='" + img + '\'' +
                ", copyright='" + copyright + '\'' +
                ", traffic=" + traffic +
                ", cid=" + cid +
                ", time=" + time +
                ", tags=" + tags +
                ", category=" + category +
                '}';
    }
}
