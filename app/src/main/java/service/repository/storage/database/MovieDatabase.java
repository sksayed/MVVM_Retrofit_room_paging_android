package service.repository.storage.database;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import model.Movie;

@Database(entities = {Movie.class} , version = 1)
public  abstract class MovieDatabase  extends RoomDatabase {
    /*
    *Make this class singleTon
    * Initiate it and make it syncronised also
    * */
    public abstract MovieDao movieDao();
    private static  MovieDatabase instance ;

    private static final Object slock = new Object();

    public static MovieDatabase getInstance(Context context) {
        synchronized (slock) {
            if(instance == null) {
                instance = Room.databaseBuilder(context , MovieDatabase.class , "movie_db" )
                        .build();
            }
            return instance ;
        }
    }



}
