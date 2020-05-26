package com.example.moviestreamingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetailsActivity extends AppCompatActivity {
    private ImageView movieThumbnailImg,movieCoverImg;
    private TextView movie_title,movie_description;
    private FloatingActionButton play_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        init();


    }
    void init(){
        play_btn=findViewById(R.id.play_fab);
        String movieTitle=getIntent().getExtras().getString("title");
        int imageResourceId=getIntent().getExtras().getInt("imgUrl");
        int imageCover=getIntent().getExtras().getInt("imgCover");
        movieThumbnailImg=findViewById(R.id.detail_movie_img);
        Glide.with(this).load(imageResourceId).into(movieThumbnailImg);

        movieThumbnailImg.setImageResource(imageResourceId);
        movieCoverImg=findViewById(R.id.detail_movie_cover);
        Glide.with(this).load(imageCover).into(movieCoverImg);
        movie_title=findViewById(R.id.details_movie_title);
        movie_title.setText(movieTitle);
        //getSupportActionBar().setTitle(movieTitle);
        movie_description=findViewById(R.id.detail_movie_description);
        //set up animation
        movieCoverImg.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));
        play_btn.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));


    }
}
