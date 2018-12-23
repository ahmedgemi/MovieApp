package com.ago.movieapp.presenter;

import com.ago.movieapp.data.Constants;
import com.ago.movieapp.data.cache.database.CachedMovie;
import com.ago.movieapp.data.model.MovieListResponse;
import com.ago.movieapp.data.model.enums.MovieType;
import com.ago.movieapp.mvpContract.MovieListContract;
import com.ago.movieapp.network.MovieApiService;
import com.ago.movieapp.network.NetworkDispatcher;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MoviePresenter implements Observer<MovieListResponse>, MovieListContract.IMovieListPresenter {

    private MovieListContract.IMovieListView view;
    private MovieType movieType;

    public MoviePresenter(MovieListContract.IMovieListView view, MovieType movieType) {
        this.view = view;
        this.movieType = movieType;
    }

    @Override
    public void getPlayNowMovies() {

        view.showProgress();

        MovieApiService movieApi = NetworkDispatcher.retrofit.create(MovieApiService.class);

        Observable<MovieListResponse> call = movieApi.getPlayNow(Constants.API_KEY);

        getMovies(call);
    }

    @Override
    public void getTopMovies() {

        view.showProgress();

        MovieApiService movieApi = NetworkDispatcher.retrofit.create(MovieApiService.class);

        Observable<MovieListResponse> call = movieApi.getTopMovies(Constants.API_KEY);

        getMovies(call);
    }

    @Override
    public void getFavoriteMovies() {

        getCachedData();

    }


    private void getMovies(Observable<MovieListResponse> call){

        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }


    private void getCachedData(){

        CachedMovie cachedMovie = new CachedMovie(this.view);

        if (movieType == MovieType.TOP){

            cachedMovie.getTopMovies();
        }
        else if (movieType == MovieType.PLAY_NOW){

            cachedMovie.getPlayNowMovies();
        }
        else if (movieType == MovieType.FAVORITE){

            cachedMovie.getFavoriteMovies();
        }
    }


    @Override
    public void onNext(MovieListResponse response) {

        CachedMovie cachedMovie = new CachedMovie(this.view);
        cachedMovie.insertMovies(response.getMovieList(),this.movieType);

        view.hideProgress();
        view.onReceiveMovies(response.getMovieList());
    }

    @Override
    public void onError(Throwable e) {

        view.showErrorMsg("Connection Error");

        getCachedData();

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Disposable d) {

    }

}
