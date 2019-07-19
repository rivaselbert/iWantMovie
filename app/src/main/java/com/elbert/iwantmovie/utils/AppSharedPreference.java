package com.elbert.iwantmovie.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.elbert.iwantmovie.models.Movie;

public class AppSharedPreference {

    private static final String PREF_NAME = "AppSharedPreference";

    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;
    private Context mContext;

    public AppSharedPreference(Context context) {
        this.mContext = context;
        mPref = context.getSharedPreferences(PREF_NAME, 0);
        mEditor = mPref.edit();
    }

    public void storeLastScreen(String screen) {
        mEditor.putString("last_screen", screen);
        mEditor.commit();
    }

    public void storeMovieDetails(Movie movie) {
        mEditor.putString("track_name", movie.getTrackName());
        mEditor.putString("artwork_url", movie.getArtworkUrl());
        mEditor.putString("genre", movie.getGenre());
        mEditor.putString("price", String.valueOf(movie.getTrackPrice()));
        mEditor.putString("long_desc", movie.getLongDesc());

        mEditor.commit();
    }

    public String getLastScreen() {
        return mPref.getString("last_screen", null);
    }

    public Movie getMovieDetails() {
        String trackName = mPref.getString("track_name", "");
        String artworkUrl = mPref.getString("artwork_url", "");
        String genre = mPref.getString("genre", "");
        String price = mPref.getString("price", "");
        String longDesc = mPref.getString("long_desc", "");

        return new Movie(trackName, artworkUrl, Double.parseDouble(price), genre, longDesc);
    }

}
