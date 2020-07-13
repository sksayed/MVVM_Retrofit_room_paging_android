package View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import CallBacks.Presenter;
import Model.Comment;
import Model.Post;
import Presenter.HttpPresenter;
import Repository.JsonApiPlaceHolder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.sayed.learnigretrofitlearning.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private Retrofit retrofit;
    private JsonApiPlaceHolder apiPlaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit1st);
        loadContents();
        getPosts();
        //getComments();
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
        mTextView = (TextView) findViewById(R.id.text);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiPlaceHolder = retrofit.create(JsonApiPlaceHolder.class);
    }


    private void getPosts() {
        apiPlaceHolder
                .getPosts(1 , "id","desc")
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
}