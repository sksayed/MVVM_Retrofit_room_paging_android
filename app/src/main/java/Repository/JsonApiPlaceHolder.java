package Repository;

import java.util.List;

import Model.Comment;
import Model.Post;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonApiPlaceHolder {
    @GET("posts")
    Call<List<Post>> getPosts ();
    /*
    here retro fit will map the id
    passed in to the id of the
    annotation
    * */
    @GET("posts/{id}/comments")
    Call<List<Comment>> getCommentsFromUser (@Path("id") int userId);
}
