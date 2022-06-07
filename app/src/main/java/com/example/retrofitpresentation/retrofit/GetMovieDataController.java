package com.example.retrofitpresentation.retrofit;

import androidx.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetMovieDataController implements Callback<List<MovieData>> {

    private final MovieDataService movieDataService;
    private final GetResponseListener getResponseListener;

    public GetMovieDataController(GetResponseListener getResponseListener) {
        movieDataService = ServiceGenerator.createService(MovieDataService.class);
        this.getResponseListener = getResponseListener;
    }

    public void getMovieData(){
        Call<List<MovieData>> movieDataCall = movieDataService.getMovies();

        movieDataCall.enqueue(this);
    }

    @Override
    public void onResponse(@NonNull Call<List<MovieData>> call, Response<List<MovieData>> response) {
        if(response.isSuccessful()){
            List<MovieData> movieDataList = response.body();
            getResponseListener.getResponseSuccess(movieDataList);
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(@NonNull Call<List<MovieData>> call, Throwable t) {
        System.out.println(t.getMessage());
    }
}
