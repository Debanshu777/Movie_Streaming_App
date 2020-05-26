package com.example.moviestreamingapp;

import androidx.appcompat.app.AppCompatActivity;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sliderpager=findViewById(R.id.slider_pager);
        indicator=findViewById(R.id.indicator);

        listSlides=new ArrayList<>();
        listSlides.add(new Slide(R.drawable.slider1,"Slider Title \nsome text here"));
        listSlides.add(new Slide(R.drawable.slider2,"Slider Title \nsome text here"));
        listSlides.add(new Slide(R.drawable.slider1,"Slider Title \nsome text here"));
        listSlides.add(new Slide(R.drawable.slider2,"Slider Title \nsome text here"));

        SlidePagerAdapter adapter=new SlidePagerAdapter(this,listSlides);
        sliderpager.setAdapter(adapter );
        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(),3000,3000);
        indicator.setupWithViewPager(sliderpager,true);
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
