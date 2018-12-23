package com.ago.movieapp.data.cache.database;

import com.ago.movieapp.MovieApp;
import com.ago.movieapp.data.model.Movie;
import com.ago.movieapp.data.model.dbEntity.MovieEntity;
import com.ago.movieapp.data.model.enums.MovieType;
import com.ago.movieapp.mvpContract.MovieListContract;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CachedMovie implements MaybeObserver<List<MovieEntity>>, MovieListContract.IMovieListPresenter {

    private MovieListContract.IMovieListView view;

    public CachedMovie(MovieListContract.IMovieListView view) {
        this.view = view;
    }

    @Override
    public void getPlayNowMovies() {

        Maybe<List<MovieEntity>> call = AppDatabase.getDatabase(MovieApp.getInstance().getApplicationContext())
                .movieDao()
                .getPlayNowMovies();

        getMovies(call);
    }

    @Override
    public void getTopMovies() {

        Maybe<List<MovieEntity>> call = AppDatabase.getDatabase(MovieApp.getInstance().getApplicationContext())
                .movieDao()
                .getTopMovies();

        getMovies(call);
    }

    @Override
    public void getFavoriteMovies() {

        Maybe<List<MovieEntity>> call = AppDatabase.getDatabase(MovieApp.getInstance().getApplicationContext())
                .movieDao()
                .getFavoriteMovies();

        getMovies(call);
    }


    private void getMovies(Maybe<List<MovieEntity>> call){

        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    public void setFavoriteMovie(final int movieId){


        Completable.fromRunnable(new Runnable() {
            @Override
            public void run() {

                MovieDAO dao = AppDatabase.getDatabase(MovieApp.getInstance().getApplicationContext())
                        .movieDao();

                dao.setFavorite(movieId);

            }
        }).subscribeOn(Schedulers.io())
          .subscribe();
    }

    public void insertMovies(final List<Movie> list, final MovieType type){

        Completable.fromRunnable(new Runnable() {
            @Override
            public void run() {

                MovieDAO dao = AppDatabase.getDatabase(MovieApp.getInstance().getApplicationContext())
                        .movieDao();

                for (Movie movie : list){

                    if (type == MovieType.PLAY_NOW)
                        movie.setType(0);
                    else if (type == MovieType.TOP)
                        movie.setType(1);

                    dao.insert( MovieEntity.getEntity(movie) );
                }


            }
        }).subscribeOn(Schedulers.io())
          .subscribe();
    }


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onSuccess(List<MovieEntity> movieEntities) {

        List<Movie> list = new ArrayList<>();
        for (MovieEntity entity : movieEntities)
            list.add( MovieEntity.getMovie(entity) );

        view.hideProgress();
        view.onReceiveMovies(list);
    }


    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }

}
