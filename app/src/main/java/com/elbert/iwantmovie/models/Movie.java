package com.elbert.iwantmovie.models;

public class Movie {

    String trackName;
    String artworkUrl;
    double trackPrice;
    String genre;
    String shortDesc;
    String longDesc;

    public Movie() {
    }

    public Movie(String trackName, String artworkUrl, double trackPrice, String genre, String shortDesc, String longDesc) {
        this.trackName = trackName;
        this.artworkUrl = artworkUrl;
        this.trackPrice = trackPrice;
        this.genre = genre;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
    }

    public Movie(String trackName, String artworkUrl, double trackPrice, String genre, String longDesc) {
        this.trackName = trackName;
        this.artworkUrl = artworkUrl;
        this.trackPrice = trackPrice;
        this.genre = genre;
        this.longDesc = longDesc;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getArtworkUrl() {
        return artworkUrl;
    }

    public void setArtworkUrl(String artworkUrl) {
        this.artworkUrl = artworkUrl;
    }

    public double getTrackPrice() {
        return trackPrice;
    }

    public void setTrackPrice(double trackPrice) {
        this.trackPrice = trackPrice;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }
}
