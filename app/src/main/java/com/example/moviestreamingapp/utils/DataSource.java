package com.example.moviestreamingapp.utils;

import com.example.moviestreamingapp.R;
import com.example.moviestreamingapp.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class DataSource {


    public static List<Movie> getPopularMovies(){
        List<Movie> movieList=new ArrayList<>();
        movieList.add(new Movie("Joker", R.drawable.image1,R.drawable.inter));
        movieList.add(new Movie("Agent",R.drawable.image2,R.drawable.inter));
        movieList.add(new Movie("Greta",R.drawable.image3));
        movieList.add(new Movie("Hollywood",R.drawable.image4));
        movieList.add(new Movie("Bloodshot",R.drawable.image5));
        movieList.add(new Movie("John Wick 3",R.drawable.image6));
        return movieList;

    }
    public static List<Movie> getTrendingMovies(){
        List<Movie> movieList=new ArrayList<>();
        movieList.add(new Movie("Hollywood",R.drawable.image4,R.drawable.inter));
        movieList.add(new Movie("Bloodshot",R.drawable.image5,R.drawable.inter));
        movieList.add(new Movie("John Wick 3",R.drawable.image6));
        movieList.add(new Movie("Joker", R.drawable.image1,R.drawable.inter));
        movieList.add(new Movie("Agent",R.drawable.image2,R.drawable.inter));
        movieList.add(new Movie("Greta",R.drawable.image3));
        return movieList;

    }
}
