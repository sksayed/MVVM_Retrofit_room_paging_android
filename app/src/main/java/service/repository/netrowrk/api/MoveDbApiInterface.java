package service.repository.netrowrk.api;

import java.util.ArrayList;

import model.Movie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import utilities.Constants;


public interface MoveDbApiInterface {
   //TODO: ASk Fahim bhaiya
    /*
    * why do we need to provide . here*/
    @GET(".")
    Call<ArrayList<Movie>> getAllPopularMovies (@Query(Constants.API_KEY_REQUEST_PARAM) String apiKey,
                                                @Query(Constants.LANGUAGE_REQUEST_PARAM) String language ,
                                                @Query(Constants.PAGE_REQUEST_PARAM) int page);
}
