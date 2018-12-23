package com.ago.movieapp.data.model.dbEntity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.ago.movieapp.data.model.Movie;

@Entity
public class MovieEntity {

    @PrimaryKey
    public int id;

    public double voteAverage;

    public String title;

    public String posterPath;

    public String backdropPath;

    public String overview;

    public int type; // 0 => PlayNow,  1=> Popular

    public int favorite = 0; // 0= false, 1=> true


    // this method convert Movie Obj to MovieEntity to store in database
    public static MovieEntity getEntity(Movie movie){
        if (movie == null)
            return null;

        MovieEntity entity = new MovieEntity();
        entity.id = movie.getId();
        entity.voteAverage = movie.getVoteAverage();
        entity.title = movie.getTitle();
        entity.posterPath = movie.getPosterPath();
        entity.backdropPath = movie.getBackdropPath();
        entity.overview = movie.getOverview();
        entity.type = movie.getType();
        entity.favorite = movie.getFavorite();

        return entity;
    }

    // this method convert MovieEntity Obj to Movie
    public static Movie getMovie(MovieEntity entity){

        Movie movie = new Movie();

        movie.setId(entity.id);
        movie.setVoteAverage(entity.voteAverage);
        movie.setTitle(entity.title);
        movie.setPosterPath(entity.posterPath);;
        movie.setBackdropPath(entity.backdropPath);
        movie.setOverview(entity.overview);
        movie.setType(entity.type);
        movie.setFavorite(entity.favorite);

        return movie;
    }
}
