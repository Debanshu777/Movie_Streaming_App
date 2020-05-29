package com.example.moviestreamingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.moviestreamingapp.adapters.CastAdapter;
import com.example.moviestreamingapp.adapters.PreviewAdapter;
import com.example.moviestreamingapp.adapters.UpcomingAdapter;
import com.example.moviestreamingapp.models.Cast;
import com.example.moviestreamingapp.models.Movie;
import com.example.moviestreamingapp.adapters.MovieAdapter;
import com.example.moviestreamingapp.models.MovieItemClickListener;
import com.example.moviestreamingapp.models.Slide;
import com.example.moviestreamingapp.adapters.SlidePagerAdapter;
import com.example.moviestreamingapp.utils.DataSource;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements MovieItemClickListener {

    private List<Slide> listSlides;
    private ViewPager sliderpager;
    private TabLayout indicator;
    private RecyclerView movieHoriTrend,movieHoriPop,previews,upcomings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        movieViewFlipper();
        movieHorizontalListPopular();
        movieHorizontalListTrending();
        preview_list();
        upcoming_list();

    }

    private void upcoming_list() {
        List<Cast> upcominglist=new ArrayList<>();
        upcominglist.add(new Cast("name1",R.drawable.image3));
        upcominglist.add(new Cast("name2",R.drawable.image4));
        upcominglist.add(new Cast("name3",R.drawable.slider1));
        upcominglist.add(new Cast("name4",R.drawable.slider2));
        upcominglist.add(new Cast("name5",R.drawable.image5));
        UpcomingAdapter upcomingAdapter=new UpcomingAdapter(this,upcominglist);
        upcomings.setAdapter(upcomingAdapter);
        upcomings.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }

    private void preview_list() {
        List<Cast> castlist=new ArrayList<>();
        castlist.add(new Cast("name1",R.drawable.image3));
        castlist.add(new Cast("name2",R.drawable.image4));
        castlist.add(new Cast("name3",R.drawable.slider1));
        castlist.add(new Cast("name4",R.drawable.slider2));
        castlist.add(new Cast("name5",R.drawable.image5));
        PreviewAdapter previewAdapter=new PreviewAdapter(this,castlist);
        previews.setAdapter(previewAdapter);
        previews.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }

    private void movieHorizontalListPopular() {
        MovieAdapter movieAdapter=new MovieAdapter(this, DataSource.getPopularMovies(),this);
        movieHoriPop.setAdapter(movieAdapter);
        movieHoriPop.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }

    private void movieHorizontalListTrending() {

        MovieAdapter movieAdapter=new MovieAdapter(this, DataSource.getTrendingMovies(),this);
        movieHoriTrend.setAdapter(movieAdapter);
        movieHoriTrend.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }

    private void movieViewFlipper() {
        listSlides=new ArrayList<>();
        listSlides.add(new Slide(R.drawable.image1,"Joker"));
        listSlides.add(new Slide(R.drawable.image5,"Bloodspot"));
        listSlides.add(new Slide(R.drawable.slider1,"Avengers:EndGame"));
        listSlides.add(new Slide(R.drawable.image3,"Greta"));

        SlidePagerAdapter adapter=new SlidePagerAdapter(this,listSlides);
        sliderpager.setAdapter(adapter );
        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(),3000,3000);
        indicator.setupWithViewPager(sliderpager,true);
    }

    private void init() {
        upcomings=findViewById(R.id.upcoming);
        previews=findViewById(R.id.preview);
        sliderpager=findViewById(R.id.slider_pager);
        indicator=findViewById(R.id.indicator);
        movieHoriTrend=findViewById(R.id.Rx_movies);
        movieHoriPop=findViewById(R.id.Rx_movies_popular);
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
