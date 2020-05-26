package com.example.moviestreamingapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.moviestreamingapp.R;
import com.example.moviestreamingapp.models.Slide;

import java.util.List;

public class SlidePagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<Slide> mList;

    public SlidePagerAdapter(Context mContext, List<Slide> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size() ;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View sliderLayout= inflater.inflate(R.layout.slide_item,null);

        ImageView slideImage =sliderLayout.findViewById(R.id.slide_image);
        TextView sliderText = sliderLayout.findViewById(R.id.slide_title);
        slideImage.setImageResource(mList.get(position).getImage());
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
