package ui.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sayed.learnigretrofitlearning.R;
import com.squareup.picasso.Picasso;

import model.Movie;
import ui.callback.OnMovieItemClickListener;
import utilities.Constants;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    TextView title ;
    TextView userrating ;
    ImageView thumbnail ;
    private Movie movie ;
    private OnMovieItemClickListener onMovieItemClickListener ;

    public MovieViewHolder(@NonNull View itemView , OnMovieItemClickListener onMovieItemClickListener) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        userrating = itemView.findViewById(R.id.userrating);
        thumbnail = itemView.findViewById(R.id.thumbnail);
        this.onMovieItemClickListener = onMovieItemClickListener ;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onMovieItemClickListener != null) {
                    onMovieItemClickListener.movieItemClicked(movie);
                }
            }
        });
    }

    public void bindTo (Movie movie) {
        this.title.setText(movie.getTitle());
        this.userrating.setText(String.format("%1$,.2f" ,movie.getVoteAverage()));
        if (movie.getPosterPath() != null){
            String posterPath = Constants.SMALL_IMAGE_URL_PREFIX+movie.getPosterPath();
            Picasso.get().load(posterPath).into(thumbnail);
        }
        this.movie = movie ;
    }


}
