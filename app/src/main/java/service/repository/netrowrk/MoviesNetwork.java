package service.repository.netrowrk;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import Model.Movie;
import Model.NetworkState;
import service.repository.netrowrk.paging.NetMoviePageKeydDataSource;
import service.repository.netrowrk.paging.NetMoviesDataSourceFactory;
import utilities.Constants;

/*
* this class will take values from the Datasource and return
* the live data of Paged list
* */
public class MoviesNetwork {
    public static final String TAG = MoviesNetwork.class.getSimpleName();
    private final LiveData<PagedList<Movie>> moviesPaged ;
    private final LiveData<NetworkState> networkStateLiveData ;

    public MoviesNetwork(NetMoviesDataSourceFactory dataSourceFactory , PagedList.BoundaryCallback boundaryCallback) {
        networkStateLiveData = Transformations.switchMap(dataSourceFactory.getNetworkState() ,
                NetMoviePageKeydDataSource::getNetworkState);
        PagedList.Config pagelistConfig = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setMaxSize(Constants.LOADING_PAGE_SIZE)
                .setPageSize(Constants.LOADING_PAGE_SIZE)
                .build();
        LivePagedListBuilder livePagedListBuilder = new LivePagedListBuilder(dataSourceFactory , pagelistConfig);
        Executor executor = Executors.newFixedThreadPool(Constants.NUMBER_OF_THREADS);
        moviesPaged =livePagedListBuilder
                .setFetchExecutor(executor)
                .setBoundaryCallback(boundaryCallback)
                .build();
    }

    public LiveData<PagedList<Movie>> getMoviesPaged() {
        return moviesPaged;
    }

    public LiveData<NetworkState> getNetworkStateLiveData() {
        return networkStateLiveData;
    }


}
