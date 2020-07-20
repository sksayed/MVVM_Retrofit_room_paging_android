package service.repository.netrowrk.paging;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import model.Movie;
import model.NetworkState;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.subjects.ReplaySubject;
import service.repository.netrowrk.api.MoveDbApiInterface;
import service.repository.netrowrk.api.MovieApiClient;
import utilities.Constants;

public class NetMoviePageKeydDataSource extends PageKeyedDataSource<String, Movie> {
    public static final String TAG = NetMoviePageKeydDataSource.class.getSimpleName();
    private final MoveDbApiInterface moveDbApiInterface;
    private final MutableLiveData<NetworkState> networkState;
    private final ReplaySubject<Movie> movieObservable;

    public MutableLiveData getNetworkState() {
        return networkState;
    }

    public ReplaySubject<Movie> getMovieObservable() {
        return movieObservable;
    }

    public NetMoviePageKeydDataSource() {
        this.moveDbApiInterface = MovieApiClient.getMovieApiClient();
        this.networkState = new MutableLiveData();
        this.movieObservable = ReplaySubject.create();
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<String> params, @NonNull LoadInitialCallback<String, Movie> callback) {
        Log.i(TAG, "Loading Initial Rang, Count " + params.requestedLoadSize);
        networkState.postValue(NetworkState.LOADING);
        Call<ArrayList<Movie>> call = moveDbApiInterface
                .getAllPopularMovies(Constants.API_KEY, Constants.LANGUAGE, 1);
        call.enqueue(new Callback<ArrayList<Movie>>() {
            @Override
            public void onResponse(Call<ArrayList<Movie>> call, Response<ArrayList<Movie>> response) {
                if (response.isSuccessful()) {
                    callback.onResult(response.body(), Integer.toString(1), Integer.toString(2));
                    networkState.postValue(NetworkState.LOADED);
                    response.body().forEach(movieObservable::onNext);
                } else {
                    Log.e(TAG, "Response is not successful" + response.message());
                    networkState.postValue(new NetworkState(NetworkState.Status.FAILED, "response is not succesfull"));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Movie>> call, Throwable t) {
                String errorMessage;
                if (t.getMessage() == null) {
                    errorMessage = "unknown error";
                } else {
                    errorMessage = t.getMessage();
                }
                networkState.postValue(new NetworkState(NetworkState.Status.FAILED, errorMessage));
                callback.onResult(new ArrayList<>(), Integer.toString(1), Integer.toString(2));
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, Movie> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, Movie> callback) {
        Log.i(TAG, "loading page " + params.key);
        networkState.postValue(NetworkState.LOADING);
        //why did they do that
        //find it out
        final AtomicInteger page = new AtomicInteger(0);
        try {
            page.set(Integer.parseInt(params.key));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Call<ArrayList<Movie>> getListOfMoviesAccoringToPage = moveDbApiInterface
                .getAllPopularMovies(Constants.API_KEY,
                        Constants.LANGUAGE, page.get());
        getListOfMoviesAccoringToPage.enqueue(new Callback<ArrayList<Movie>>() {
            @Override
            public void onResponse(Call<ArrayList<Movie>> call, Response<ArrayList<Movie>> response) {
                if (response.isSuccessful()) {
                    callback.onResult(response.body(), Integer.toString(page.get() + 1));
                    networkState.postValue(NetworkState.LOADED);
                    response.body().forEach(movieObservable::onNext);
                } else {
                    Log.e(TAG, "Api response failed" + response.message());
                    networkState.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Movie>> call, Throwable t) {
                String errorMessage;
                if (t.getMessage() == null) {
                    errorMessage = "unknown error";
                } else {
                    errorMessage = t.getMessage();
                }
                networkState.postValue(new NetworkState(NetworkState.Status.FAILED, errorMessage));
                callback.onResult(new ArrayList<>(), Integer.toString(page.get()));

            }
        });

    }
}
