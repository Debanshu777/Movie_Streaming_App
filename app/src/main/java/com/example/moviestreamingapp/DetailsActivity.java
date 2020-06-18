package com.example.moviestreamingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviestreamingapp.Client.RetrofitClient;
import com.example.moviestreamingapp.Client.RetrofitService;
import com.example.moviestreamingapp.adapters.CastAdapter;
import com.example.moviestreamingapp.models.Cast;
import com.example.moviestreamingapp.models.CastResponse;
import com.example.moviestreamingapp.models.Movie;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailsActivity extends AppCompatActivity {
    private ImageView movieThumbnailImg,movieCoverImg;
    private TextView movie_title,movie_description,time_details,adult_details;
    private FloatingActionButton play_btn;
    private RecyclerView movie_cast;
    private CastAdapter castAdapter;
    private MaterialRatingBar ratingBar;
    private Movie movie;
    private List<Cast> castlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        init();
        pagesetup();
    }

    private void pagesetup() {

        movie=getIntent().getParcelableExtra("movie_parcel");
        Glide.with(this).load("https://image.tmdb.org/t/p/w500"+movie.getPoster_path()).into(movieThumbnailImg);
        Glide.with(this).load("https://image.tmdb.org/t/p/w500"+movie.getBackdrop_path()).into(movieCoverImg);
        movie_title.setText(movie.getTitle());
        adult_details.setText(movie.isAdult()?"A":"U/A");
        movie_description.setText(movie.getOverview());
        ratingBar.setIsIndicator(true);
        ratingBar.setRating(movie.getVote_average()/2);
        new Thread(new Runnable() {
            @Override
            public void run() {
                setUpCastList(String.valueOf(movie.getId()));
            }
        }).start();



        movieCoverImg.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));
        play_btn.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));

        play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailsActivity.this,MoviePlayerActivity.class));
            }
        });
    }

    private void setUpCastList(String id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RetrofitService retrofitService = RetrofitClient.getClient().create(RetrofitService.class);
                Call<CastResponse> call;
                call = retrofitService.getCastList(id,BuildConfig.THE_MOVIE_DB_API_KEY);
                call.enqueue(new Callback<CastResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<CastResponse> call, @NonNull Response<CastResponse> response) {
                        if (response.isSuccessful() && response.body().getCast() != null){
                            castlist=response.body().getCast();
                            CastAdapter castAdapter = new CastAdapter(DetailsActivity.this,castlist);
                            movie_cast.setAdapter(castAdapter);
                            movie_cast.setHasFixedSize(true);
                            movie_cast.setLayoutManager(new LinearLayoutManager(DetailsActivity.this, LinearLayoutManager.HORIZONTAL, false));
                            castAdapter.notifyDataSetChanged();


                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<CastResponse> call,@NonNull Throwable t) {

                    }
                });
            }
        }).start();

    }

    void init(){
        play_btn=findViewById(R.id.play_fab);
        movieThumbnailImg=findViewById(R.id.detail_movie_img);
        movieCoverImg=findViewById(R.id.detail_movie_cover);
        movie_description=findViewById(R.id.detail_movie_description);
        movie_title=findViewById(R.id.details_movie_title);
        movie_cast=findViewById(R.id.movie_cast_list);
        ratingBar=findViewById(R.id.rating_bar);
        adult_details=findViewById(R.id.adult_details);
        castlist=new ArrayList<>();
    }
}
