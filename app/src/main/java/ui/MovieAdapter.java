package ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.sayed.learnigretrofitlearning.R;

import model.Movie;
import model.NetworkState;
import ui.viewholder.MovieViewHolder;
import ui.viewholder.NetworkStateViewHolder;

public class MovieAdapter extends PagedListAdapter<Movie, RecyclerView.ViewHolder> {
    private NetworkState networkState;

    public MovieAdapter() {
        super(Movie.movieItemCallback);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if (viewType == R.layout.movie_item) {
            View view = layoutInflater.inflate(R.layout.movie_item, parent, false);
            MovieViewHolder viewHolder = new MovieViewHolder(view);
            ;
            return viewHolder;
        } else if (viewType == R.layout.network_state_item) {
            View view = layoutInflater.inflate(R.layout.network_state_item, parent, false);
            return new NetworkStateViewHolder(view);
        } else {
            throw new IllegalArgumentException("unknown view type");
        }
    }

      /*
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    */

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MovieViewHolder) {
            switch (getItemViewType(position)) {
                case R.layout.network_state_item:
                    ((NetworkStateViewHolder) holder).bindTo(networkState);
                    break;
                case R.layout.movie_item:
                    ((MovieViewHolder) holder).bindTo(getItem(position));
                    break;
            }
        }
    }

    private boolean hasExtraRow() {
        if (networkState != null && networkState != NetworkState.LOADED) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (hasExtraRow() && position == getItemCount() - 1) {
            return R.layout.network_state_item;
        } else {
            return R.layout.movie_item;
        }
    }

    public void setNetworkState(NetworkState newNetworkState) {
        //save the previous one
        NetworkState previousNetworkState = this.networkState;
        boolean previousRowExtra = hasExtraRow();
        this.networkState = newNetworkState;
        boolean newExtraRow = hasExtraRow();
        if (previousRowExtra != newExtraRow) {
            if (previousRowExtra) {
                notifyItemRemoved(getItemCount());
            } else {
                notifyItemInserted(getItemCount());
            }
        } else if (newExtraRow && previousNetworkState != newNetworkState) {
            notifyItemChanged(getItemCount() - 1);
        }

    }
}
