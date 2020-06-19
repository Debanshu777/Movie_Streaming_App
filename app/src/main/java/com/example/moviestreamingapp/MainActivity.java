package com.example.moviestreamingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.moviestreamingapp.Client.RetrofitClient;
import com.example.moviestreamingapp.Client.RetrofitService;
import com.example.moviestreamingapp.adapters.PreviewAdapter;
import com.example.moviestreamingapp.adapters.UpcomingAdapter;
import com.example.moviestreamingapp.models.Movie;
import com.example.moviestreamingapp.adapters.MovieAdapter;
import com.example.moviestreamingapp.models.MovieItemClickListener;
import com.example.moviestreamingapp.models.MovieResponse;
import com.example.moviestreamingapp.models.Slide;
import com.example.moviestreamingapp.adapters.SlidePagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MovieItemClickListener {

    private List<Movie> listSlides = new ArrayList<>();
    private List<Movie> slides = new ArrayList<>();
    private ViewPager sliderpager;
    private SlidePagerAdapter slideAdapter;
    private TabLayout indicator;
    private List<Movie> upcomingList = new ArrayList<>();
    private List<Movie> previewList = new ArrayList<>();
    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView movieHoriTrend, movieHoriPop, previews, upcomings, toprated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        movieViewFlipper();
        new Thread(new Runnable() {
            @Override
            public void run() {
                movieHorizontalList("trend");
                movieHorizontalList("popular");
                movieHorizontalList("toprated");
                preview_list();
                upcoming_list();
            }
        }).start();
    }

    private void upcoming_list() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RetrofitService retrofitService = RetrofitClient.getClient().create(RetrofitService.class);
                Call<MovieResponse> call;
                call = retrofitService.getUpcomingList(BuildConfig.THE_MOVIE_DB_API_KEY);
                call.enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                        if (response.isSuccessful() && response.body().getResult() != null) {
                            upcomingList = response.body().getResult();
                            UpcomingAdapter upcomingAdapter = new UpcomingAdapter(MainActivity.this, upcomingList, MainActivity.this::onMovieClick);
                            upcomings.setAdapter(upcomingAdapter);
                            upcomings.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {

                    }
                });
            }
        }).start();
    }

    private void preview_list() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RetrofitService retrofitService = RetrofitClient.getClient().create(RetrofitService.class);
                Call<MovieResponse> call;
                call = retrofitService.getPreviewList(BuildConfig.THE_MOVIE_DB_API_KEY);
                call.enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                        if (response.isSuccessful() && response.body().getResult() != null) {
                            previewList = response.body().getResult();
                            PreviewAdapter previewAdapter = new PreviewAdapter(MainActivity.this, previewList);
                            previews.setAdapter(previewAdapter);
                            previews.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));

                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {

                    }
                });
            }
        }).start();

    }

    private void movieHorizontalList(String type) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RetrofitService retrofitService = RetrofitClient.getClient().create(RetrofitService.class);
                Call<MovieResponse> call;
                if (type == "trend") {
                    call = retrofitService.getTrendingList(BuildConfig.THE_MOVIE_DB_API_KEY);
                } else if (type == "toprated") {
                    call = retrofitService.getTopRatedList(BuildConfig.THE_MOVIE_DB_API_KEY);
                } else {
                    call = retrofitService.getPopularList(BuildConfig.THE_MOVIE_DB_API_KEY);
                }
                call.enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                        if (response.isSuccessful() && response.body().getResult() != null) {

                            movieList = response.body().getResult();
                            MovieAdapter movieAdapter = new MovieAdapter(MainActivity.this, movieList, MainActivity.this);
                            if (type.equals("trend")) {
                                movieHoriTrend.setAdapter(movieAdapter);
                                movieHoriTrend.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                            } else if (type.equals("toprated")) {
                                toprated.setAdapter(movieAdapter);
                                toprated.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                            } else {
                                movieHoriPop.setAdapter(movieAdapter);
                                movieHoriPop.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "" + response.errorBody(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<MovieResponse> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }

    private void movieViewFlipper() {
        Timer timer = new Timer();
        RetrofitService retrofitService = RetrofitClient.getClient().create(RetrofitService.class);
        Call<MovieResponse> call;
        call = retrofitService.getSliderList(BuildConfig.THE_MOVIE_DB_API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful() && response.body().getResult() != null) {
                    slides = response.body().getResult();
                    slideAdapter = new SlidePagerAdapter(MainActivity.this, slides);
                    sliderpager.setAdapter(slideAdapter);
                    automateViewPagerSwiping();
                    indicator.setupWithViewPager(sliderpager, true);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }
    private void automateViewPagerSwiping() {
        final long DELAY_MS = 2000;//delay in milliseconds before task is to be executed
        final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            public void run() {
                if (sliderpager.getCurrentItem() == slideAdapter.getCount() - 1) { //adapter is your custom ViewPager's adapter
                    sliderpager.setCurrentItem(0);
                }
                else {
                    sliderpager.setCurrentItem(sliderpager.getCurrentItem() + 1, true);
                }
            }
        };

        Timer timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(update);
            }
        }, DELAY_MS, PERIOD_MS);
    }

    private void init() {
        upcomings = findViewById(R.id.upcoming);
        previews = findViewById(R.id.preview);
        sliderpager = findViewById(R.id.slider_pager);
        indicator = findViewById(R.id.indicator);
        movieHoriTrend = findViewById(R.id.Rx_movies);
        movieHoriPop = findViewById(R.id.Rx_movies_popular);
        toprated = findViewById(R.id.toprated);
    }

    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {
        //here we send movie information to details activity
        //also we'll create the transition animation between two activity

        Intent intent = new Intent(this, DetailsActivity.class);
        //send movie into yo DetailsActivity
        intent.putExtra("movie_parcel", movie);
        //adding animation
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                MainActivity.this, movieImageView, "sharedName");
        startActivity(intent, options.toBundle());
    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public boolean isInternetAvailable() {
        try {
            InetAddress address = InetAddress.getByName("www.google.com");
            return !address.equals("");
        } catch (UnknownHostException e) {
            // Log error
        }
        return false;
    }


}
