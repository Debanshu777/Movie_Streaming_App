package com.example.moviestreamingapp.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Genre {

    @SerializedName("genres")
    @Expose
    private List<Genre_> genres = null;

    public List<Genre_> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre_> genres) {
        this.genres = genres;
    }

}
