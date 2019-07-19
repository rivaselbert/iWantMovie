package com.elbert.iwantmovie.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.elbert.iwantmovie.R;
import com.elbert.iwantmovie.models.Movie;
import com.elbert.iwantmovie.utils.AppSharedPreference;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.elbert.iwantmovie.R2.string.longDesc;

public class MovieDetailsActivity extends AppCompatActivity {

    private static final String SCREEN_NAME = "MovieDetailsActivity";

    @BindView(R.id.iv_artwork) ImageView ivArtwork;
    @BindView(R.id.tv_track_name) TextView tvTrackName;
    @BindView(R.id.tv_genre) TextView tvGenre;
    @BindView(R.id.tv_price) TextView tvPrice;
    @BindView(R.id.tv_long_desc) TextView tvLongDesc;

    private AppSharedPreference mSharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);
        mSharedPref = new AppSharedPreference(this);

        setMovieDetails();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = getIntent();

        boolean fromMainActivity = intent.getBooleanExtra("from_main_activity", false);
        if (!fromMainActivity) {
            startActivity(new Intent(this, MainActivity.class));
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
            finish();
        }

        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    private void setMovieDetails() {
        Intent intent = getIntent();

        String trackName = "";
        String artworkUrl = "";
        String genre = "";
        String price = "";
        String longDesc = "";

        if (intent.hasExtra("track_name")) {
            trackName = intent.getStringExtra("track_name");
            artworkUrl = intent.getStringExtra("artwork_url");
            genre = intent.getStringExtra("genre");
            price = intent.getStringExtra("price");
            longDesc = intent.getStringExtra("long_desc");
        } else {
            Movie movie = mSharedPref.getMovieDetails();

            if (!movie.getTrackName().equals("")) {
                trackName = movie.getTrackName();
                artworkUrl = movie.getArtworkUrl();
                genre = movie.getGenre();
                price = String.valueOf(movie.getTrackPrice());
                longDesc = movie.getLongDesc();
            }
        }

        Glide.with(this).load(artworkUrl).into(ivArtwork);
        tvTrackName.setText(trackName);
        tvGenre.setText(genre);
        tvPrice.setText("$" + price);
        tvLongDesc.setText(longDesc);

        Movie movie = new Movie(trackName, artworkUrl, Double.parseDouble(price), genre, longDesc);

        // Save screen data to shared preference
        mSharedPref.storeLastScreen(SCREEN_NAME);
        mSharedPref.storeMovieDetails(movie);
    }
}
