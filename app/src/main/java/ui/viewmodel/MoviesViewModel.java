package ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import model.Movie;
import model.NetworkState;
import service.MovieRepository;

public class MoviesViewModel extends AndroidViewModel {
    private MovieRepository movieRepository ;
    public MoviesViewModel(@NonNull Application application) {
        super(application);
        movieRepository =  MovieRepository.getInstance(application);
    }

    public LiveData<PagedList<Movie>> getPagedListLiveData ( ) {
       return movieRepository.getMediatorLiveData();
    }

    public LiveData<NetworkState> getNetworkStateLiveData () {
        return movieRepository.getNetworkStateLiveData();
    }

}
