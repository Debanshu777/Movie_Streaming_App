package com.example.moviestreamingapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviestreamingapp.R;
import com.example.moviestreamingapp.models.Cast;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PreviewAdapter extends RecyclerView.Adapter<PreviewAdapter.PreviewViewHolder> {

    Context context;
    List<Cast> mData;

    public PreviewAdapter(Context context, List<Cast> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public PreviewAdapter.PreviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_preview,parent,false);
        return new PreviewAdapter.PreviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PreviewAdapter.PreviewViewHolder holder, int position) {
        Glide.with(context).load(mData.get(position).getImg_link()).into(holder.preview_img);
    }

    @Override
    public int getItemCount() {
        return mData.size() ;
    }

    public class PreviewViewHolder extends RecyclerView.ViewHolder{
        CircleImageView preview_img;

        public PreviewViewHolder(@NonNull View itemView) {
            super(itemView);
            preview_img=itemView.findViewById(R.id.image_preview);
        }
    }
}
