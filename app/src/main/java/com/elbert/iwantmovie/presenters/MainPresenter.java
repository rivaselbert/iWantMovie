package com.elbert.iwantmovie.presenters;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.elbert.iwantmovie.models.Movie;
import com.elbert.iwantmovie.utils.JsonObjectRequest;
import com.elbert.iwantmovie.views.MainView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainPresenter {

    private Context mContext;
    private MainView mView;

    private RequestQueue mRequestQueue;

    public MainPresenter(Context context, MainView view) {
        this.mContext = context;
        this.mView = view;
        this.mRequestQueue = Volley.newRequestQueue(context);
    }

    // Get list of movies from itunes api
    public void getMovies() {
        Map<String,String> params = new HashMap<>();
        params.put("term", "star");
        params.put("country", "au");
        params.put("media", "movie");

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray results = response.getJSONArray("results");

                            // Check if result is not null
                            if (results != null && results.length() > 0) {
                                for (int i = 0; i < results.length(); i++) {
                                    JSONObject object = null;
                                    try {
                                        object = results.getJSONObject(i);
                                        System.out.println("Data " + object);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    String trackName = null;
                                    try {
                                        trackName = object.getString("trackName");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    String artworkUrl = null;
                                    try {
                                        artworkUrl = object.getString("artworkUrl100");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    double trackPrice = 0;
                                    try {
                                        trackPrice = object.getDouble("trackPrice");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    String genre = null;
                                    try {
                                        genre = object.getString("primaryGenreName");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    String shortDesc = null;
                                    try {
                                        shortDesc = object.getString("shortDescription");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    String longDesc = null;
                                    try {
                                        longDesc = object.getString("longDescription");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    String arworkUrl400 = null;
                                    // Get higher quality image (300x300)
                                    if (artworkUrl != null) {
                                        arworkUrl400 = artworkUrl.replace("100x100", "400x400");
                                    }

                                    Movie movie = new Movie(trackName, arworkUrl400, trackPrice, genre, shortDesc, longDesc);
                                    mView.loadMovie(movie);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               System.out.println(error.getLocalizedMessage());
            }
        });

        mRequestQueue.add(jsonObjectRequest);
    }

}
