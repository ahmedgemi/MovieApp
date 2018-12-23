package com.ago.movieapp.presenter;

import com.ago.movieapp.data.Constants;
import com.ago.movieapp.data.model.MovieListResponse;
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

    public MoviePresenter(MovieListContract.IMovieListView view) {
        this.view = view;
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

    }


    private void getMovies(Observable<MovieListResponse> call){

        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }


    @Override
    public void onNext(MovieListResponse response) {

        view.hideProgress();
        view.onReceiveMovies(response.getMovieList());
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Disposable d) {

    }

}
