package service;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.paging.PagedList;

import model.NetworkState;
import service.repository.netrowrk.MoviesNetwork;
import service.repository.netrowrk.paging.NetMoviesDataSourceFactory;

/*
 * here i will  implement the
 * Inintial part of Movie Repository
 * which is value will come from WeB Api*/
public class MovieRepository {
    /*
     * this class wiil be singleton
     * it will hold both the data sources such as WebApi , Database Datasource
     * it will return MediatorLiveData as it will observe only the on change
     * */

    public static final String TAG = MovieRepository.class.getSimpleName();
    private final MoviesNetwork moviesNetwork;
    private final MediatorLiveData mediatorLiveData;
    private static MovieRepository instance;

    public MovieRepository(Context context) {
        //TODO: change the getters of MovieNetwork
        moviesNetwork = new MoviesNetwork(new NetMoviesDataSourceFactory(), boundaryCallback);
        mediatorLiveData = new MediatorLiveData();
        mediatorLiveData.addSource(moviesNetwork.getMoviesPaged(), value -> {
            mediatorLiveData.setValue(value);
            Log.d(TAG, value.toString());
        });
    }

    public static MovieRepository getInstance(Context context) {
        if (instance == null) {
            instance = new MovieRepository(context);
        }
        return instance;
    }

    //TODO: fix the Boundary call back
    private final PagedList.BoundaryCallback boundaryCallback = new PagedList.BoundaryCallback() {
        @Override
        public void onZeroItemsLoaded() {
            super.onZeroItemsLoaded();
           /* mediatorLiveData.addSource(moviesNetwork.getMoviesPaged(), value -> {
                mediatorLiveData.setValue(value);
                Log.d(TAG, value.toString());
            });*/
        }

        @Override
        public void onItemAtFrontLoaded(@NonNull Object itemAtFront) {
            super.onItemAtFrontLoaded(itemAtFront);
        }

        @Override
        public void onItemAtEndLoaded(@NonNull Object itemAtEnd) {
            super.onItemAtEndLoaded(itemAtEnd);
        }
    };

    public LiveData getMediatorLiveData() {
        return mediatorLiveData;
    }

    public LiveData<NetworkState> getNetworkStateLiveData() {
        return this.moviesNetwork.getNetworkStateLiveData();
    }


}
