package com.example.moviestreamingapp;

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
import com.example.moviestreamingapp.adapters.CastAdapter;
import com.example.moviestreamingapp.models.CastOld;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    private ImageView movieThumbnailImg,movieCoverImg;
    private TextView movie_title,movie_description;
    private FloatingActionButton play_btn;
    private RecyclerView movie_cast;
    private CastAdapter castAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        init();
        setUpCastList();


    }

    private void setUpCastList() {
        List<CastOld> castlist=new ArrayList<>();
        castlist.add(new CastOld("name1",R.drawable.cast1));
        castlist.add(new CastOld("name2",R.drawable.cast2));
        castlist.add(new CastOld("name3",R.drawable.cast3));
        castlist.add(new CastOld("name4",R.drawable.cast4));
        castAdapter=new CastAdapter(this,castlist);
        movie_cast.setAdapter(castAdapter);
        movie_cast.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }

    void init(){
        play_btn=findViewById(R.id.play_fab);
        movieThumbnailImg=findViewById(R.id.detail_movie_img);
        movieCoverImg=findViewById(R.id.detail_movie_cover);
        movie_description=findViewById(R.id.detail_movie_description);
        movie_title=findViewById(R.id.details_movie_title);
        movie_cast=findViewById(R.id.movie_cast_list);


        String movieTitle=getIntent().getExtras().getString("title");
        int imageResourceId=getIntent().getExtras().getInt("imgUrl");
        int imageCover=getIntent().getExtras().getInt("imgCover");

        Glide.with(this).load(imageResourceId).into(movieThumbnailImg);
        movieThumbnailImg.setImageResource(imageResourceId);
        Glide.with(this).load(imageCover).into(movieCoverImg);
        movie_title.setText(movieTitle);


        movieCoverImg.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));
        play_btn.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));

        play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailsActivity.this,MoviePlayerActivity.class));
            }
        });


    }
}
