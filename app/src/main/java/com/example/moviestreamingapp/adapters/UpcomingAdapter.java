package com.example.moviestreamingapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviestreamingapp.R;
import com.example.moviestreamingapp.models.CastOld;

import java.util.List;

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.UpcomingViewHolder> {

    Context context;
    List<CastOld> mData;

    public UpcomingAdapter(Context context, List<CastOld> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public UpcomingAdapter.UpcomingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_upcoming,parent,false);
        return new UpcomingAdapter.UpcomingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingAdapter.UpcomingViewHolder holder, int position) {
       Glide.with(context).load(mData.get(position).getImg_link()).into(holder.upcoming_img);
    }

    @Override
    public int getItemCount() {
        return mData.size() ;
    }

    public class UpcomingViewHolder extends RecyclerView.ViewHolder{
        ImageView upcoming_img;

        public UpcomingViewHolder(@NonNull View itemView) {
            super(itemView);
            upcoming_img=itemView.findViewById(R.id.image_upcoming);
        }
    }
}

