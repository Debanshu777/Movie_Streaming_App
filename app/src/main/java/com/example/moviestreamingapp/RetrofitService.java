package com.example.moviestreamingapp;

import com.example.moviestreamingapp.models.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface RetrofitService  {
    @GET("movie/popular")
    Call<MovieResponse> getPopularList(@Query("api_key")String api_key);
}
