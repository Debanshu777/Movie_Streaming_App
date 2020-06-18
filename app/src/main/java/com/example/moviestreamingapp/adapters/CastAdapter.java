package com.example.moviestreamingapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.moviestreamingapp.R;
import com.example.moviestreamingapp.models.Cast;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastViewHolder> {

    Context context;
    List<Cast> mData;

    public CastAdapter(Context context, List<Cast> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_cast,parent,false);
        return new CastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CastViewHolder holder, int position) {
        Cast model=mData.get(position);
        holder.cast_name.setText(model.getName());
        if(model.getProfilePath()!=null) {
            Glide.with(context).load("https://image.tmdb.org/t/p/w500" + model.getProfilePath()).apply(new RequestOptions()
                    .centerInside()
                    .format(DecodeFormat.PREFER_RGB_565)).transition(DrawableTransitionOptions.withCrossFade())
                    .into(holder.cast_img);
        }
    }

    @Override
    public int getItemCount() {
        if(mData.size()<=10){
         return mData.size();
        }
        else
            return 10;
    }

    public class CastViewHolder extends RecyclerView.ViewHolder{
        CircleImageView cast_img;
        TextView cast_name;
        public CastViewHolder(@NonNull View itemView) {
            super(itemView);
            cast_img=itemView.findViewById(R.id.image_cast);
            cast_name=itemView.findViewById(R.id.cast_name);
        }
    }
}
