package com.example.retrofitpresentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.retrofitpresentation.retrofit.MovieData;
import com.example.retrofitpresentation.retrofit.PostMovieDataController;
import com.example.retrofitpresentation.retrofit.PostResponseListener;

public class AddActivity extends AppCompatActivity implements PostResponseListener {

    PostMovieDataController postMovieDataController;
    EditText movieTitle;
    EditText movieGenre;
    EditText movieDirector;
    EditText movieDescription;
    Button addMovieButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_movie);

        postMovieDataController = new PostMovieDataController(this);
        movieTitle = findViewById(R.id.addMovieTitle);
        movieGenre = findViewById(R.id.addMovieGenre);
        movieDirector = findViewById(R.id.addMovieDirector);
        movieDescription = findViewById(R.id.addMovieDescription);
        addMovieButton = findViewById(R.id.addMovieButton);
        addMovieButton.setOnClickListener(v -> {
            String title = movieTitle.getText().toString();
            String genre = movieGenre.getText().toString();
            String director = movieDirector.getText().toString();
            String description = movieDescription.getText().toString();
            MovieData movieData = new MovieData(title, genre, director, description);
            postMovieDataController.postMovieData(movieData);
        });
    }

    @Override
    public void postResponseSuccess(MovieData movieData) {
        Intent intent =  new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}