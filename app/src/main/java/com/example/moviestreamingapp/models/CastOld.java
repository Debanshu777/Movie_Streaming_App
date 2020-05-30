package com.example.moviestreamingapp.models;

public class CastOld {
    String name;
    int img_link;

    public CastOld(String name, int img_link) {
        this.name = name;
        this.img_link = img_link;
    }

    public String getName() {
        return name;
    }

    public int getImg_link() {
        return img_link;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg_link(int img_link) {
        this.img_link = img_link;
    }
}
