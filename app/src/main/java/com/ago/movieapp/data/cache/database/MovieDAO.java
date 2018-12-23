package com.ago.movieapp.data.cache.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ago.movieapp.data.model.Movie;
import com.ago.movieapp.data.model.dbEntity.MovieEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;


@Dao
public interface MovieDAO {

    @Query("SELECT * FROM movieentity where type== 1")
    Maybe<List<MovieEntity>> getTopMovies();


    @Query("SELECT * FROM movieentity where type== 0")
    Maybe<List<MovieEntity>> getPlayNowMovies();


    @Query("SELECT * FROM movieentity where favorite== 1")
    Maybe<List<MovieEntity>> getFavoriteMovies();


    @Query("UPDATE movieentity SET favorite = 1 where id =:movieId")
    void setFavorite(int movieId);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(MovieEntity movie);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<MovieEntity> movies);
}
