package com.example.moviestreamingapp.utils;

import com.example.moviestreamingapp.R;
import com.example.moviestreamingapp.models.MovieOld;

import java.util.ArrayList;
import java.util.List;

public class DataSource {


    public static List<MovieOld> getPopularMovies(){
        List<MovieOld> movieList=new ArrayList<>();
        movieList.add(new MovieOld("Joker", R.drawable.image1,R.drawable.inter));
        movieList.add(new MovieOld("Agent",R.drawable.image2,R.drawable.inter));
        movieList.add(new MovieOld("Greta",R.drawable.image3));
        movieList.add(new MovieOld("Hollywood",R.drawable.image4));
        movieList.add(new MovieOld("Bloodshot",R.drawable.image5));
        movieList.add(new MovieOld("John Wick 3",R.drawable.image6));
        return movieList;

    }
    public static List<MovieOld> getTrendingMovies(){
        List<MovieOld> movieList=new ArrayList<>();
        movieList.add(new MovieOld("Hollywood",R.drawable.image4,R.drawable.inter));
        movieList.add(new MovieOld("Bloodshot",R.drawable.image5,R.drawable.inter));
        movieList.add(new MovieOld("John Wick 3",R.drawable.image6));
        movieList.add(new MovieOld("Joker", R.drawable.image1,R.drawable.inter));
        movieList.add(new MovieOld("Agent",R.drawable.image2,R.drawable.inter));
        movieList.add(new MovieOld("Greta",R.drawable.image3));
        return movieList;

    }
}
