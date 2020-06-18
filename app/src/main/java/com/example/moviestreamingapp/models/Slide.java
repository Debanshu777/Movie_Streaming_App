package com.example.moviestreamingapp.models;

public class Slide {
    private int Image;
    private String Title;
    private Movie movie;

    public Slide(Movie movie) {
        this.movie=movie;
    }

    public int getImage() {
        return Image;
    }

    public String getTitle() {
        return Title;
    }

    public void setImage(int image) {
        Image = image;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
