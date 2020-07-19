package service.repository.netrowrk.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import utilities.Constants;

public class MovieApiClient {

    public static MoveDbApiInterface getMovieApiClient ()
    {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        //here we will use custom desereliazition of Gson
        // we remove from the response some wrapper tags from our movies array
        Gson gson = new GsonBuilder().registerTypeAdapter(Constants.MOVIE_ARRAY_LIST_CLASS_TYPE , new MovieJsonDeserializer())
                .create();

        Retrofit.Builder builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .baseUrl(Constants.POPULAR_MOVIES_BASE_URL);

        return builder.build().create(MoveDbApiInterface.class);
    }
}
