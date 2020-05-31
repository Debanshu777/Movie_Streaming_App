package com.example.moviestreamingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.moviestreamingapp.Client.RetrofitClient;
import com.example.moviestreamingapp.adapters.PreviewAdapter;
import com.example.moviestreamingapp.adapters.UpcomingAdapter;
import com.example.moviestreamingapp.models.CastOld;
import com.example.moviestreamingapp.models.Movie;
import com.example.moviestreamingapp.adapters.MovieAdapter;
import com.example.moviestreamingapp.models.MovieItemClickListener;
import com.example.moviestreamingapp.models.MovieResponse;
import com.example.moviestreamingapp.models.Slide;
import com.example.moviestreamingapp.adapters.SlidePagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MovieItemClickListener {

    private List<Slide> listSlides;
    private ViewPager sliderpager;
    private TabLayout indicator;
    private List<Movie> popularList = new ArrayList<>();
    private List<Movie> movieList=new ArrayList<>();
    private RecyclerView movieHoriTrend, movieHoriPop, previews, upcomings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        movieViewFlipper();
        movieHorizontalList("trend");
        movieHorizontalList("popular");
        preview_list();
        upcoming_list();

    }

    private void upcoming_list() {
        List<CastOld> upcominglist = new ArrayList<>();
        upcominglist.add(new CastOld("name1", R.drawable.image3));
        upcominglist.add(new CastOld("name2", R.drawable.image4));
        upcominglist.add(new CastOld("name3", R.drawable.slider1));
        upcominglist.add(new CastOld("name4", R.drawable.slider2));
        upcominglist.add(new CastOld("name5", R.drawable.image5));
        UpcomingAdapter upcomingAdapter = new UpcomingAdapter(this, upcominglist);
        upcomings.setAdapter(upcomingAdapter);
        upcomings.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void preview_list() {
        List<CastOld> castlist = new ArrayList<>();
        castlist.add(new CastOld("name1", R.drawable.image3));
        castlist.add(new CastOld("name2", R.drawable.image4));
        castlist.add(new CastOld("name3", R.drawable.slider1));
        castlist.add(new CastOld("name4", R.drawable.slider2));
        castlist.add(new CastOld("name5", R.drawable.image5));
        PreviewAdapter previewAdapter = new PreviewAdapter(this, castlist);
        previews.setAdapter(previewAdapter);
        previews.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void movieHorizontalList(String type) {
        RetrofitService retrofitService = RetrofitClient.getClient().create(RetrofitService.class);
        Call<MovieResponse> call;
        if(type =="trend") {
            call = retrofitService.getTrendingList(BuildConfig.THE_MOVIE_DB_API_KEY);
        }
        else{
            call = retrofitService.getPopularList(BuildConfig.THE_MOVIE_DB_API_KEY);
        }
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                if (response.isSuccessful() && response.body().getResult() != null) {

                    movieList = response.body().getResult();
                    MovieAdapter movieAdapter = new MovieAdapter(MainActivity.this, movieList,MainActivity.this::onMovieClick);
                    if(type.equals("trend")) {
                        movieHoriTrend.setAdapter(movieAdapter);
                        movieHoriTrend.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                    }
                    else{
                        movieHoriPop.setAdapter(movieAdapter);
                        movieHoriPop.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, ""+response.errorBody(), Toast.LENGTH_SHORT).show();
                }

                // Toast.makeText(MainActivity.this, ""+popularList.size(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void movieViewFlipper() {
        listSlides = new ArrayList<>();
        listSlides.add(new Slide(R.drawable.image1, "Joker"));
        listSlides.add(new Slide(R.drawable.image5, "Bloodspot"));
        listSlides.add(new Slide(R.drawable.slider1, "Avengers:EndGame"));
        listSlides.add(new Slide(R.drawable.image3, "Greta"));

        SlidePagerAdapter adapter = new SlidePagerAdapter(this, listSlides);
        sliderpager.setAdapter(adapter);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 3000, 3000);
        indicator.setupWithViewPager(sliderpager, true);
    }

    private void init() {
        upcomings = findViewById(R.id.upcoming);
        previews = findViewById(R.id.preview);
        sliderpager = findViewById(R.id.slider_pager);
        indicator = findViewById(R.id.indicator);
        movieHoriTrend = findViewById(R.id.Rx_movies);
        movieHoriPop = findViewById(R.id.Rx_movies_popular);
    }

    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {
        //here we send movie information to details activity
        //also we'll create the transition animation between two activity

        Intent intent = new Intent(this, DetailsActivity.class);
        //send movie into yo DetailsActivity
        intent.putExtra("title", movie.getTitle());
        intent.putExtra("imgUrl", movie.getPoster_path());
        intent.putExtra("imgCover", movie.getBackdrop_path());
        //adding animation
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                MainActivity.this, movieImageView, "sharedName");
        startActivity(intent, options.toBundle());
    }


    class SliderTimer extends TimerTask {

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (sliderpager.getCurrentItem() < listSlides.size() - 1) {
                        sliderpager.setCurrentItem(sliderpager.getCurrentItem() + 1);
                    } else
                        sliderpager.setCurrentItem(0);
                }
            });
        }
    }
}
