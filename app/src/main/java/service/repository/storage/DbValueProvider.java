package service.repository.storage;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import model.Movie;
import service.repository.storage.paging.DbDataSourceFactory;
import utilities.Constants;

public class DbValueProvider {
    private static final String TAG = DbValueProvider.class.getSimpleName();
    private final LiveData<PagedList<Movie>> moviePageListLiveData ;
    public DbValueProvider(DbDataSourceFactory dbDataSourceFactory, PagedList.BoundaryCallback boundaryCallback) {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(Constants.LOADING_PAGE_SIZE)
                .setMaxSize(50)
                .build();

        LivePagedListBuilder livePagedListBuilder = new LivePagedListBuilder( dbDataSourceFactory , config);
        Executor executor = Executors.newFixedThreadPool(Constants.NUMBER_OF_THREADS);
        moviePageListLiveData = livePagedListBuilder
                .setBoundaryCallback(boundaryCallback)
                .setFetchExecutor(executor)
                .build();
    }

    public LiveData<PagedList<Movie>> getMoviePageListLiveData() {
        return moviePageListLiveData;
    }
}
