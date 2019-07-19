package com.elbert.iwantmovie.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.elbert.iwantmovie.R;
import com.elbert.iwantmovie.activities.MovieDetailsActivity;
import com.elbert.iwantmovie.models.Movie;
import com.elbert.iwantmovie.viewholders.MovieViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Movie> mMovieList = new ArrayList<>();

    public MovieAdapter(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    public void loadMovie(Movie movie) {
        mMovieList.add(movie);
        notifyItemInserted(mMovieList.size());
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.layout_movie_row, viewGroup, false);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        Movie movie = mMovieList.get(i);

        String trackName = movie.getTrackName();
        String artworkUrl = movie.getArtworkUrl();
        String genre = movie.getGenre();
        String price = String.valueOf(movie.getTrackPrice());

        // Assign views with their corresponding data
        Glide.with(mContext).load(artworkUrl).into(movieViewHolder.ivArtwork);
        movieViewHolder.tvTrackName.setText(trackName);
        movieViewHolder.tvGenre.setText(genre);
        movieViewHolder.tvPrice.setText("$" + price);
        movieViewHolder.tvShortDesc.setText(movie.getShortDesc());

        // Direct to movie details activity when movie item is clicked
        movieViewHolder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, MovieDetailsActivity.class);
            intent.putExtra("track_name", trackName);
            intent.putExtra("artwork_url", artworkUrl);
            intent.putExtra("genre", genre);
            intent.putExtra("price", price);
            intent.putExtra("long_desc", movie.getLongDesc());
            intent.putExtra("from_main_activity", true);

            mContext.startActivity(intent);
            ((Activity) mContext).overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        });
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }
}
