package service.repository.netrowrk.paging;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import Model.Post;

public class NetPostsPageKeydDataSource extends PageKeyedDataSource<Integer , Post> {
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Post> callback) {

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Post> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Post> callback) {

    }
}
