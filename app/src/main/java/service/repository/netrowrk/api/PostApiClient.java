package service.repository.netrowrk.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import utilities.Constants;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostApiClient {

    public static PostAPIInterface getClient () {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        // create OkHttpClient and register an interceptor
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        //we are providing here normal gson
        Gson gson = new GsonBuilder()
                .create();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_JOSN_PLACEHOLDER_API)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson));
        return builder.build().create(PostAPIInterface.class);
    }
}
