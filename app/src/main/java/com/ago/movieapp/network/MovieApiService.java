package com.ago.movieapp.network;

import com.ago.movieapp.data.model.MovieListResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiService {

    @GET("movie/now_playing")
    Observable<MovieListResponse> getPlayNow(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Observable<MovieListResponse> getTopMovies(@Query("api_key") String apiKey);

}
