package com.example.retrofitpresentation.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MovieDataService {
    @GET("/movies")
    Call<List<MovieData>> getMovies();

    @POST("/movies")
    Call<MovieData> postMovies(@Body MovieData movieData);

    @PUT("/movies/{id}")
    Call<MovieData> putMovie(@Path("id") Integer id, @Body MovieData movieData);

    @DELETE("/movies/{id}")
    Call<MovieData> deleteMovie(@Path("id") Integer id);
}
