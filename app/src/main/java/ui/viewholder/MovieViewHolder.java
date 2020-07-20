package ui.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sayed.learnigretrofitlearning.R;
import com.squareup.picasso.Picasso;

import model.Movie;
import utilities.Constants;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    TextView title ;
    TextView userrating ;
    ImageView thumbnail ;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        userrating = itemView.findViewById(R.id.userrating);
        thumbnail = itemView.findViewById(R.id.thumbnail);
    }

    public void bindTo (Movie movie) {
        this.title.setText(movie.getmTitle());
        this.userrating.setText(String.format("%1$,.2f" ,movie.getmVoteAverage()));
        if (movie.getmPosterPath() != null){
            String posterPath = Constants.SMALL_IMAGE_URL_PREFIX+movie.getmPosterPath();
            Picasso.get().load(posterPath).into(thumbnail);
        }

    }


}
