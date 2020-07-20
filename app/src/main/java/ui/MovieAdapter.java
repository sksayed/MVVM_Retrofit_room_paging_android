package ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.sayed.learnigretrofitlearning.R;

import model.Movie;
import ui.viewholder.MovieViewHolder;

public class MovieAdapter extends PagedListAdapter<Movie, RecyclerView.ViewHolder> {

    public MovieAdapter() {
        super(Movie.movieItemCallback);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MovieViewHolder){
            ((MovieViewHolder)holder).bindTo(getItem(position));
        }
    }
}
