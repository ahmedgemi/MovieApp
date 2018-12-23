package com.ago.movieapp.data.cache.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.ago.movieapp.data.model.dbEntity.MovieEntity;

@Database(entities = {MovieEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract MovieDAO movieDao();


    private static volatile AppDatabase INSTANCE;


    // synchronized to avoid bad threading
    static synchronized AppDatabase getDatabase(final Context context) {

        if (INSTANCE == null) {

            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "appDB")
                    .build();
        }

        return INSTANCE;
    }

}