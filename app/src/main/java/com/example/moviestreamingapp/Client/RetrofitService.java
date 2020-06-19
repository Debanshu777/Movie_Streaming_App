package com.example.moviestreamingapp.Client;

import com.example.moviestreamingapp.models.CastResponse;
import com.example.moviestreamingapp.models.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService  {
    @GET("movie/popular")
    Call<MovieResponse> getPopularList(@Query("api_key") String api_key);

    @GET("trending/movie/week")
    Call<MovieResponse> getTrendingList(@Query("api_key") String api_key);

    @GET("trending/movie/day")
    Call<MovieResponse>getSliderList(@Query("api_key") String api_key);

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedList(@Query("api_key") String api_key);

    @GET("movie/upcoming")
    Call<MovieResponse> getUpcomingList(@Query("api_key") String api_key);

    @GET("movie/now_playing")
    Call<MovieResponse> getPreviewList(@Query("api_key") String api_key);

    @GET("movie/{movie_id}/credits")
    Call<CastResponse> getCastList(@Path("movie_id") String movie_id,@Query("api_key") String api_key);
}
