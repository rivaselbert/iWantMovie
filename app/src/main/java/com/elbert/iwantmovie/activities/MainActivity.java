package com.elbert.iwantmovie.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.elbert.iwantmovie.R;
import com.elbert.iwantmovie.adapters.MovieAdapter;
import com.elbert.iwantmovie.models.Movie;
import com.elbert.iwantmovie.presenters.MainPresenter;
import com.elbert.iwantmovie.utils.AppSharedPreference;
import com.elbert.iwantmovie.views.MainView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String SCREEN_NAME = "MainActivity";

    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    private MovieAdapter mAdapter;
    private MainPresenter mPresenter;
    private AppSharedPreference mSharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initRecyclerview();
        initPresenter();

        mSharedPref = new AppSharedPreference(this);

        persistData();
    }

    // Initialize recyclerview and adapter
    private void initRecyclerview() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MovieAdapter(this);
        recyclerView.setAdapter(mAdapter);
    }

    private void initPresenter() {
        mPresenter = new MainPresenter(this, new MainView() {
            @Override
            public void hasError(String error) {
                super.hasError(error);
                Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void loadMovie(Movie movie) {
                super.loadMovie(movie);

                // pass movie data to adapter
                mAdapter.loadMovie(movie);
            }
        });

        // Get movies
        mPresenter.getMovies();
    }

    // Save screen data to shared preference
    private void persistData() {
        mSharedPref.storeLastScreen(SCREEN_NAME);
    }
}
