package com.example.moviestreamingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.moviestreamingapp.models.Movie;
import com.example.moviestreamingapp.adapters.MovieAdapter;
import com.example.moviestreamingapp.models.MovieItemClickListener;
import com.example.moviestreamingapp.models.Slide;
import com.example.moviestreamingapp.adapters.SlidePagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements MovieItemClickListener {

    private List<Slide> listSlides;
    private ViewPager sliderpager;
    private TabLayout indicator;
    private RecyclerView movieHori;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        movieViewFlipper();
        movieHorizontalList();

    }

    private void movieHorizontalList() {
        List<Movie> movieList=new ArrayList<>();
        movieList.add(new Movie("Joker",R.drawable.image1,R.drawable.inter));
        movieList.add(new Movie("Agent",R.drawable.image2,R.drawable.inter));
        movieList.add(new Movie("Greta",R.drawable.image3));
        movieList.add(new Movie("Hollywood",R.drawable.image4));
        movieList.add(new Movie("Bloodshot",R.drawable.image5));
        movieList.add(new Movie("John Wick 3",R.drawable.image6));

        MovieAdapter movieAdapter=new MovieAdapter(this,movieList,this);
        movieHori.setAdapter(movieAdapter);
        movieHori.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }

    private void movieViewFlipper() {
        listSlides=new ArrayList<>();
        listSlides.add(new Slide(R.drawable.image1,"Slider Title \nsome text here"));
        listSlides.add(new Slide(R.drawable.image5,"Slider Title \nsome text here"));
        listSlides.add(new Slide(R.drawable.slider1,"Slider Title \nsome text here"));
        listSlides.add(new Slide(R.drawable.image3,"Slider Title \nsome text here"));

        SlidePagerAdapter adapter=new SlidePagerAdapter(this,listSlides);
        sliderpager.setAdapter(adapter );
        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(),3000,3000);
        indicator.setupWithViewPager(sliderpager,true);
    }

    private void init() {
        sliderpager=findViewById(R.id.slider_pager);
        indicator=findViewById(R.id.indicator);
        movieHori=findViewById(R.id.Rx_movies);
    }

    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {
        //here we send movie information to details activity
        //also we'll create the transition animation between two activity

        Intent intent =new Intent(this,DetailsActivity.class);
        //send movie into yo DetailsActivity
        intent.putExtra("title",movie.getTitle());
        intent.putExtra("imgUrl",movie.getThumbnail());
        intent.putExtra("imgCover",movie.getCoverPhoto());
        //adding animation
        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(
                MainActivity.this,movieImageView,"sharedName");
        startActivity(intent,options.toBundle());
    }


    class SliderTimer extends TimerTask{

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(sliderpager.getCurrentItem()<listSlides.size()-1){
                        sliderpager.setCurrentItem(sliderpager.getCurrentItem()+1);
                    }
                    else
                        sliderpager.setCurrentItem(0);
                }
            });
        }
    }
}