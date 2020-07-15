package View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import CallBacks.Presenter;
import Model.Comment;
import Model.Post;
import Presenter.HttpPresenter;
import Repository.JsonApiPlaceHolder;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Timeout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.sayed.learnigretrofitlearning.R;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private HttpLoggingInterceptor httpLoggingInterceptor;
    private OkHttpClient okHttpClient;
    private TextView mTextView;
    private Retrofit retrofit;
    private JsonApiPlaceHolder apiPlaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit1st);
        mTextView = (TextView) findViewById(R.id.text);
        //forRetrofit();
        forOKhttp();
    }

    private void forOKhttp() {
        loadOkhttp();
        makeSyncCallInOkHttp();
       // makeCallAsyncInOkHttp();
    }

    private void forRetrofit() {
        loadContents();
        getPosts();
        getComments();
    }

    private void getComments() {
        apiPlaceHolder.getCommentsFromUser(5)
                .enqueue(new Callback<List<Comment>>() {
                    @Override
                    public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                        if (!response.isSuccessful()) {
                            mTextView.setText(response + " code is " + response.code());
                            return;
                        }

                        for (Comment comment : response.body()) {
                            String content = "";
                            content += "PostId: " + comment.getPostId() + "\n";
                            content += "ID: " + comment.getId() + "\n";
                            content += "Email: " + comment.getEmail() + "\n";
                            content += "Name: " + comment.getName() + "\n";
                            content += "Text: " + comment.getText() + "\n\n";

                            mTextView.append(content);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Comment>> call, Throwable t) {
                        mTextView.append(t.getMessage());
                    }
                });
    }

    private void loadContents() {

        httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        apiPlaceHolder = retrofit.create(JsonApiPlaceHolder.class);
    }

    private void loadOkhttp() {
        okHttpClient = new OkHttpClient();
      //  new OkHttpSync().execute("posts");
    }

    private void makeSyncCallInOkHttp() {
        Request request = new Request.Builder()
                .url(BASE_URL + "posts")
                .build();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            okhttp3.Response response = null;
            try {
                response = okHttpClient.newCall(request).execute();
                if (response.isSuccessful()) {
                    okhttp3.Response finalResponse = response;
                    new Handler(getMainLooper()).post(()->{
                        try {
                            mTextView.setText(finalResponse.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                } else {
                    mTextView.setText("response failed" + response.code());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private class OkHttpSync extends AsyncTask<String, Void , String> {

        @Override
        protected String doInBackground(String... strings) {
            Request request = new Request.Builder()
                    .url(BASE_URL +"posts")
                    .build();
            try {
                okhttp3.Response response = okHttpClient.newCall(request).execute();
                if (response.body().toString() != null){
                    return response.body().toString();
                }else {
                    return String.valueOf(response.code());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mTextView.setText(s);
        }
    }


    private void getPosts() {
        apiPlaceHolder
                .getPosts(1, "id", "desc")
                .enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                        if (!response.isSuccessful()) {
                            mTextView.setText(response + " code is " + response.code());
                            return;
                        }

                        for (Post post : response.body()) {
                            String content = "";
                            content += "ID: " + post.getId() + "\n";
                            content += "User ID: " + post.getUserid() + "\n";
                            content += "Title: " + post.getTitle() + "\n";
                            content += "Text: " + post.getText() + "\n\n";

                            mTextView.append(content);
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Post>> call, Throwable t) {
                        mTextView.append(t.getMessage());
                    }
                });
    }

    private void makeCallAsyncInOkHttp () {
        Request request = new Request.Builder()
                .url(BASE_URL+"posts")
                .build();
        okHttpClient.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NotNull okhttp3.Call call, @NotNull IOException e) {
               mTextView.setText(e.getMessage());
            }

            @Override
            public void onResponse(@NotNull okhttp3.Call call, @NotNull okhttp3.Response response) throws IOException {
               new Handler(getMainLooper()).post(()->{
                   try {
                       mTextView.setText(response.body().string());
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               });
            }
        });
    }
}