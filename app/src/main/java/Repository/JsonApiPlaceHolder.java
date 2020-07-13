package Repository;

import java.util.List;

import Model.Post;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonApiPlaceHolder {
    @GET("posts")
    Call<List<Post>> getPosts ();
}
