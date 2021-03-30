package com.mhx.blog.domain;

import lombok.Data;

@Data
public class Root {
    private Integer id;
    private String name;
    private String username;
    private String password;
    private String message;
    private String img;

    public Root() {
    }

    public Root(Integer id, String img) {
        this.id = id;
        this.img = img;
    }

    public Root(String name, String username, String password, String message, String img) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.message = message;
        this.img = img;
    }

    public Root(Integer id, String name, String username, String password, String message, String img) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.message = message;
        this.img = img;
    }
}
