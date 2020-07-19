package service.repository.netrowrk.paging;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import Model.Movie;
import rx.subjects.ReplaySubject;

public class NetMoviesDataSourceFactory extends DataSource.Factory {
    public static final String TAG = NetMoviesDataSourceFactory.class.getSimpleName();
    private final MutableLiveData<NetMoviePageKeydDataSource> networkState ;
    private final NetMoviePageKeydDataSource moviePageKeydDataSource ;

    public NetMoviesDataSourceFactory() {
        networkState = new MutableLiveData<>();
        moviePageKeydDataSource = new NetMoviePageKeydDataSource();
    }

    public MutableLiveData<NetMoviePageKeydDataSource> getNetworkState() {
        return networkState;
    }

    public NetMoviePageKeydDataSource getMoviePageKeydDataSource() {
        return moviePageKeydDataSource;
    }

    @NonNull
    @Override
    public DataSource create() {
        networkState.postValue(moviePageKeydDataSource);
        return moviePageKeydDataSource ;
    }

    public ReplaySubject<Movie> getMovies() {
        return moviePageKeydDataSource.getMovieObservable();
    }


}
