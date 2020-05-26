package com.example.moviestreamingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

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
        List<Movie> movieList=new ArrayList<>();
        movieList.add(new Movie("Joker",R.drawable.image1));
        movieList.add(new Movie("Agent",R.drawable.image2));
        movieList.add(new Movie("Greta",R.drawable.image3));
        movieList.add(new Movie("Holywood",R.drawable.image4));
        movieList.add(new Movie("Bloodshot",R.drawable.image5));
        movieList.add(new Movie("John Wick 3",R.drawable.image6));

        MovieAdapter movieAdapter=new MovieAdapter(this,movieList);
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
