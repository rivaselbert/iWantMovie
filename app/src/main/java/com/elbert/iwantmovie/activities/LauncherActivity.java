package com.elbert.iwantmovie.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.elbert.iwantmovie.R;
import com.elbert.iwantmovie.utils.AppSharedPreference;

public class LauncherActivity extends AppCompatActivity {

    private AppSharedPreference mSharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        mSharedPref = new AppSharedPreference(this);

        launchToScreen();
    }

    // present to the last saved screen
    private void launchToScreen() {
        String lastScreen = mSharedPref.getLastScreen();

        new Handler().postDelayed(() -> {
            if (lastScreen != null && lastScreen.equals("MovieDetailsActivity")) {
                startActivity(new Intent(LauncherActivity.this, MovieDetailsActivity.class));
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                finish();
            } else {
                startActivity(new Intent(LauncherActivity.this, MainActivity.class));
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                finish();
            }
        }, 2000);
    }
}
