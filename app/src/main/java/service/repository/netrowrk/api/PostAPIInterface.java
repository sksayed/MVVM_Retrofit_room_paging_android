package service.repository.netrowrk.api;


import java.util.List;

import model.Post;
import retrofit2.Call;
import retrofit2.http.GET;

interface PostAPIInterface {

    @GET("posts")
    Call<List<Post>> getAllPosts ();
}
