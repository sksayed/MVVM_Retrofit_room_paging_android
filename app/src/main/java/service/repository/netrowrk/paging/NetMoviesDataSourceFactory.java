package service.repository.netrowrk.paging;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import model.Movie;
import rx.subjects.ReplaySubject;

public class NetMoviesDataSourceFactory extends DataSource.Factory {
    public static final String TAG = NetMoviesDataSourceFactory.class.getSimpleName();
    private final MutableLiveData<NetMoviePageKeydDataSource> netMoviesKedataSrcMutableLiveData;
    private final NetMoviePageKeydDataSource moviePageKeydDataSource ;

    public NetMoviesDataSourceFactory() {
        netMoviesKedataSrcMutableLiveData = new MutableLiveData<>();
        moviePageKeydDataSource = new NetMoviePageKeydDataSource();
    }

    public MutableLiveData<NetMoviePageKeydDataSource> getNetMoviesKedataSrcMutableLiveData() {
        return netMoviesKedataSrcMutableLiveData;
    }

    public NetMoviePageKeydDataSource getMoviePageKeydDataSource() {
        return moviePageKeydDataSource;
    }

    @NonNull
    @Override
    public DataSource create() {
        netMoviesKedataSrcMutableLiveData.postValue(moviePageKeydDataSource);
        return moviePageKeydDataSource ;
    }

    public ReplaySubject<Movie> getMovies() {
        return moviePageKeydDataSource.getMovieObservable();
    }


}
