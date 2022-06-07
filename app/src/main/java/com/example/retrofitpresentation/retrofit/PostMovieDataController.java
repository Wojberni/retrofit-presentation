package com.example.retrofitpresentation.retrofit;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostMovieDataController implements Callback<MovieData> {

    private final MovieDataService movieDataService;
    private final PostResponseListener postResponseListener;

    public PostMovieDataController(PostResponseListener postResponseListener) {
        this.movieDataService = ServiceGenerator.createService(MovieDataService.class);
        this.postResponseListener = postResponseListener;
    }

    public void postMovieData(MovieData movieData){
        Call<MovieData> movieDataCall = movieDataService.postMovies(movieData);

        movieDataCall.enqueue(this);
    }

    public void putMovieData(Integer id, MovieData movieData){
        Call<MovieData> movieDataCall = movieDataService.putMovie(id, movieData);

        movieDataCall.enqueue(this);
    }

    public void deleteMovieData(Integer id){
        Call<MovieData> movieDataCall = movieDataService.deleteMovie(id);

        movieDataCall.enqueue(this);
    }


    @Override
    public void onResponse(@NonNull Call<MovieData> call, Response<MovieData> response) {
        if(response.isSuccessful()){
            MovieData movieData = response.body();
            postResponseListener.postResponseSuccess(movieData);
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(@NonNull Call<MovieData> call, Throwable t) {
        System.out.println(t.getMessage());
    }
}
