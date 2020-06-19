package com.example.moviestreamingapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.moviestreamingapp.R;
import com.example.moviestreamingapp.models.Movie;
import com.example.moviestreamingapp.models.Slide;

import java.util.List;

public class SlidePagerAdapter extends PagerAdapter {

    private Context context;
    private List<Movie> mList;

    public SlidePagerAdapter(Context context, List<Movie> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return 5 ;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View sliderLayout= inflater.inflate(R.layout.slide_item,null);
        //Movie model=mList.get(position);
        ImageView slideImage =sliderLayout.findViewById(R.id.slide_image);
        TextView sliderText = sliderLayout.findViewById(R.id.slide_title);
        Glide.with(context).load("https://image.tmdb.org/t/p/w500"+mList.get(position).getPoster_path()).transition(DrawableTransitionOptions.withCrossFade())
                .into(slideImage);

        //slideImage.setImageResource(mList.get(position).getImage());
        sliderText.setText(mList.get(position).getTitle());
        container.addView(sliderLayout);
        return  sliderLayout;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
