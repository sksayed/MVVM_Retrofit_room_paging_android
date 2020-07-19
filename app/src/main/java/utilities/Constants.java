package utilities;

import java.lang.reflect.Type;
import java.util.ArrayList;

import Model.Movie;

public class Constants {

    public static final String BASE_URL_JOSN_PLACEHOLDER_API ="https://jsonplaceholder.typicode.com/";
    public static final String POPULAR_MOVIES_BASE_URL = "https://api.themoviedb.org/3/movie/popular/";
    private static final String IMAGE_URL_PREFIX = "https://image.tmdb.org/t/p/";
    public static final String SMALL_IMAGE_URL_PREFIX = IMAGE_URL_PREFIX + "w300";
    public static final String BIG_IMAGE_URL_PREFIX = IMAGE_URL_PREFIX + "w500";
    public static final String API_KEY_REQUEST_PARAM = "api_key";
    public static final String LANGUAGE_REQUEST_PARAM = "language";
    public static final String PAGE_REQUEST_PARAM = "page";
    public static final String API_KEY = "7794fd99bfef29331dbd191e955e5e88";
    public static final String LANGUAGE = "en";
    public static final String MOVIES_ARRAY_DATA_TAG = "results";
    public static final Type MOVIE_ARRAY_LIST_CLASS_TYPE = (new ArrayList<Movie>()).getClass();

}
