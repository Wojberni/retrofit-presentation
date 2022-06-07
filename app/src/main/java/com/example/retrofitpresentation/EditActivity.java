package com.example.retrofitpresentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.retrofitpresentation.retrofit.MovieData;
import com.example.retrofitpresentation.retrofit.PostMovieDataController;
import com.example.retrofitpresentation.retrofit.PostResponseListener;

public class EditActivity extends AppCompatActivity implements PostResponseListener {

    PostMovieDataController postMovieDataController;
    EditText movieTitle;
    EditText movieGenre;
    EditText movieDirector;
    EditText movieDescription;
    Button editMovieButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_movie);

        MovieData intentMovieData = (MovieData) getIntent().getSerializableExtra("movieData");

        postMovieDataController = new PostMovieDataController(this);
        movieTitle = findViewById(R.id.editMovieTitle);
        movieGenre = findViewById(R.id.editMovieGenre);
        movieDirector = findViewById(R.id.editMovieDirector);
        movieDescription = findViewById(R.id.editMovieDescription);
        editMovieButton = findViewById(R.id.editMovieButton);

        Integer id = intentMovieData.getId();
        movieTitle.setText(intentMovieData.getTitle());
        movieGenre.setText(intentMovieData.getGenre());
        movieDirector.setText(intentMovieData.getDirector());
        movieDescription.setText(intentMovieData.getDescription());
        editMovieButton.setOnClickListener(v -> {
            String title = movieTitle.getText().toString();
            String genre = movieGenre.getText().toString();
            String director = movieDirector.getText().toString();
            String description = movieDescription.getText().toString();
            MovieData movieData = new MovieData(title, genre, director, description);
            postMovieDataController.putMovieData(id, movieData);
        });
    }


    @Override
    public void postResponseSuccess(MovieData movieData) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}