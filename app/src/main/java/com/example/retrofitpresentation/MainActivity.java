package com.example.retrofitpresentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitpresentation.retrofit.MovieData;
import com.example.retrofitpresentation.retrofit.GetMovieDataController;
import com.example.retrofitpresentation.retrofit.PostMovieDataController;
import com.example.retrofitpresentation.retrofit.GetResponseListener;
import com.example.retrofitpresentation.retrofit.PostResponseListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements MovieAdapter.MovieAdapterClickListener, GetResponseListener, PostResponseListener {

    List<MovieData> movieDataList = new ArrayList<>();
    RecyclerView.Adapter<MovieAdapter.ViewHolder> movieAdapter;
    GetMovieDataController getMovieDataController;
    PostMovieDataController postMovieDataController;

    Context context;
    Button addMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();

        getMovieDataController = new GetMovieDataController(this);
        postMovieDataController = new PostMovieDataController(this);
        getMovieDataController.getMovieData();

        addMovie = findViewById(R.id.mainAddMovieButton);
        addMovie.setOnClickListener(v -> {
            Intent intent =  new Intent(context, AddActivity.class);
            startActivity(intent);
        });

        movieAdapter = new MovieAdapter(movieDataList, this);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager lm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lm);

        recyclerView.setAdapter(movieAdapter);

    }

    @Override
    public void onClick(View view, int position) {
        switch (view.getId()){
            case R.id.editButton:
                Intent intent =  new Intent(context, EditActivity.class);
                MovieData movieData = movieDataList.get(position);
                intent.putExtra("movieData", movieData);
                startActivity(intent);
                break;
            case R.id.deleteButton:
                postMovieDataController.deleteMovieData(movieDataList.get(position).id);
                break;
            default:
                break;
        }
    }

    @Override
    public void getResponseSuccess(List<MovieData> movieDataList) {
        this.movieDataList.clear();
        this.movieDataList.addAll(movieDataList);
        movieAdapter.notifyDataSetChanged();
    }

    @Override
    public void postResponseSuccess(MovieData movieData) {
        getMovieDataController.getMovieData();
    }
}