package com.example.moviestreamingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class DetailsActivity extends AppCompatActivity {
    private ImageView movieThumbnailImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        //get data
        String movieTitle=getIntent().getExtras().getString("title");
        int imageResourceId=getIntent().getExtras().getInt("imgUrl");
        movieThumbnailImg=findViewById(R.id.detail_movie_img);
        movieThumbnailImg.setImageResource(imageResourceId);
    }
}
