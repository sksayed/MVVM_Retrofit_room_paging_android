package ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sayed.learnigretrofitlearning.R;

import ui.Fragment.MovieListFragment;

public class MovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        if (findViewById(R.id.FrameContainer) !=  null) {
            MovieListFragment movieListFragment = new MovieListFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(movieListFragment , null)
                    .commit();
        }
    }
}
