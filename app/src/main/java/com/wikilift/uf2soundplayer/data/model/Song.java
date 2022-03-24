package com.wikilift.uf2soundplayer.data.model;

import java.io.Serializable;

public class Song implements Serializable {
    private int id;
    private String url,title;

    public Song(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public Song(int id, String url, String title) {
        this.id = id;
        this.url = url;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
