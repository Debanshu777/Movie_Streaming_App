package com.example.moviestreamingapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.moviestreamingapp.R;
import com.example.moviestreamingapp.models.CastOld;
import com.example.moviestreamingapp.models.Movie;
import com.example.moviestreamingapp.models.MovieItemClickListener;

import java.util.List;

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.UpcomingViewHolder> {

    Context context;
    List<Movie> mData;
    MovieItemClickListener movieItemClickListener;

    public UpcomingAdapter(Context context, List<Movie> mData,MovieItemClickListener movieItemClickListener) {
        this.context = context;
        this.mData = mData;
        this.movieItemClickListener=movieItemClickListener;
    }

    @NonNull
    @Override
    public UpcomingAdapter.UpcomingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_upcoming,parent,false);
        return new UpcomingAdapter.UpcomingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingAdapter.UpcomingViewHolder holder, int position) {
        Movie model=mData.get(position);

        Glide.with(context).load("https://image.tmdb.org/t/p/w500"+model.getPoster_path()).transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.upcoming_img);
        holder.upcoming_title.setText(model.getTitle());
    }

    @Override
    public int getItemCount() {
        return mData.size() ;
    }

    public class UpcomingViewHolder extends RecyclerView.ViewHolder{
        ImageView upcoming_img;
        TextView upcoming_title;
            public UpcomingViewHolder(@NonNull View itemView) {
            super(itemView);
            upcoming_img=itemView.findViewById(R.id.image_upcoming);
            upcoming_title=itemView.findViewById(R.id.upcoming_title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    movieItemClickListener.onMovieClick(mData.get(getAdapterPosition()),upcoming_img);
                }
            });
        }
    }
}

