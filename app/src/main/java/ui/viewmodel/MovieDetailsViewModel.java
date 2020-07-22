package ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import model.Movie;

public class MovieDetailsViewModel extends ViewModel {
    private final MutableLiveData<Movie> movieMutableLiveData ;

    public MovieDetailsViewModel() {
        this.movieMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<Movie> getMovieMutableLiveData() {
        return movieMutableLiveData;
    }


}
