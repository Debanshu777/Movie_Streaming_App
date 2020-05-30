package com.example.moviestreamingapp.models;

import java.util.List;

public class MovieResponse {
    private int page;
    private int total_result;
    private int total_pages;
    private List<Movie > result;

    public MovieResponse(){

    }

    public MovieResponse(int page, int total_result, int total_pages, List<Movie> result) {
        this.page = page;
        this.total_result = total_result;
        this.total_pages = total_pages;
        this.result = result;
    }

    public int getPage() {
        return page;
    }

    public int getTotal_result() {
        return total_result;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public List<Movie> getResult() {
        return result;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setTotal_result(int total_result) {
        this.total_result = total_result;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public void setResult(List<Movie> result) {
        this.result = result;
    }
}
