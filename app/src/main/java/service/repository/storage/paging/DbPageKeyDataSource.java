package service.repository.storage.paging;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import java.util.List;

import model.Movie;
import service.repository.storage.database.MovieDao;

public class DbPageKeyDataSource extends PageKeyedDataSource<String , Movie> {
    private static final String TAG = DbPageKeyDataSource.class.getSimpleName();
    private final MovieDao movieDao ;

    public DbPageKeyDataSource(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<String> params, @NonNull LoadInitialCallback<String, Movie> callback) {
        Log.d(TAG, "Loading Initial Rang, Count " + params.requestedLoadSize);
        List<Movie> movieList = movieDao.getMovies();
        if (movieList.size() > 0 ){
        callback.onResult(movieList , "0" , "1");
        }
    }

    @Override
    public void loadBefore(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, Movie> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, Movie> callback) {

    }
}
