package com.ago.movieapp.mvpContract;

import com.ago.movieapp.data.model.Movie;

import java.util.List;

public interface MovieListContract {

    interface IMovieListView {

        void showProgress();
        void hideProgress();
        void showErrorMsg(String msg);

        void onReceiveMovies(List<Movie> list);
    }

    interface IMovieListPresenter {

        void getPlayNowMovies();
        void getTopMovies();
        void getFavoriteMovies();
    }
}
