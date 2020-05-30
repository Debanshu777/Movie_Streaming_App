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
import com.example.moviestreamingapp.models.Movie;
import com.example.moviestreamingapp.models.MovieOld;
import com.example.moviestreamingapp.models.MovieItemClickListener;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    Context context;
    List<Movie> mData;
    MovieItemClickListener movieItemClickListener;

    public MovieAdapter(Context context, List<Movie> mData) {
        this.context = context;
        this.mData = mData;
        this.movieItemClickListener=movieItemClickListener;
    }
//, MovieItemClickListener movieItemClickListener
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_movies,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Movie model=mData.get(position);

        holder.title.setText(mData.get(position).getTitle());
        Glide.with(context).load("https://image.tmdb.org/t/p/w500"+model.getPoster_path()).transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.imgMovie);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private ImageView imgMovie,favourite_btn;
        int i=1;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.item_movie_title);
            imgMovie=itemView.findViewById(R.id.item_movie_img);
            favourite_btn=itemView.findViewById(R.id.favourite);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //movieItemClickListener.onMovieClick(mData.get(getAdapterPosition()),imgMovie);
                }
            });
            favourite_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(i!=0){
                        i=0;
                        favourite_btn.setImageResource(R.drawable.ic_favourite_selected);
                    }
                    else{
                        i=1;
                        favourite_btn.setImageResource(R.drawable.ic_favourite_unselected);
                    }
                }
            });
        }
    }
}
