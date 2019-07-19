package com.elbert.iwantmovie.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.elbert.iwantmovie.R;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    public ImageView ivArtwork;
    public TextView tvTrackName;
    public TextView tvPrice;
    public TextView tvGenre;
    public TextView tvShortDesc;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);

        ivArtwork = itemView.findViewById(R.id.iv_artwork);
        tvTrackName = itemView.findViewById(R.id.tv_track_name);
        tvPrice = itemView.findViewById(R.id.tv_price);
        tvGenre = itemView.findViewById(R.id.tv_genre);
        tvShortDesc = itemView.findViewById(R.id.tv_short_desc);
    }
}
