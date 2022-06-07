package com.example.retrofitpresentation.retrofit;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MovieData implements Serializable {
    @SerializedName("id")
    public Integer id;
    @SerializedName("title")
    public String title;
    @SerializedName("genre")
    public String genre;
    @SerializedName("director")
    public String director;
    @SerializedName("description")
    public String description;


    public MovieData(String title, String genre, String director, String description) {
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getDescription() {
        return description;
    }

    public String getDirector() {
        return director;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
