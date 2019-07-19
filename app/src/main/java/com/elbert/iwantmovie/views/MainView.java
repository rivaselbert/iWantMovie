package com.elbert.iwantmovie.views;

import com.elbert.iwantmovie.models.Movie;

interface MainInterface {
    void hasError(String error);
    void loadMovie(Movie movie);
}

public abstract class MainView implements MainInterface {
    @Override
    public void hasError(String error) {

    }

    @Override
    public void loadMovie(Movie movie) {

    }
}
