package ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sayed.learnigretrofitlearning.R;

import model.Movie;
import ui.MovieAdapter;
import ui.viewmodel.MovieDetailsViewModel;
import ui.viewmodel.MoviesViewModel;
import ui.callback.OnMovieItemClickListener;

public class MovieListFragment extends Fragment implements OnMovieItemClickListener{
    private RecyclerView movie_list_recyclerView ;
    private MoviesViewModel moviesViewModel ;
    private MovieAdapter movieAdapter ;
    private MovieDetailsViewModel detailsViewModel ;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        moviesViewModel = new ViewModelProvider(getActivity()).get(MoviesViewModel.class);
        movieAdapter = new MovieAdapter(this::movieItemClicked);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity() , RecyclerView.VERTICAL , false);
        movie_list_recyclerView.setAdapter(movieAdapter);
        movie_list_recyclerView.setLayoutManager(linearLayoutManager);
        movie_list_recyclerView.setItemAnimator( new DefaultItemAnimator());
        detailsViewModel = new ViewModelProvider(getActivity()).get(MovieDetailsViewModel.class);
        handleObservers();
    }

    private void handleObservers() {
        moviesViewModel.getPagedListLiveData().observe(getViewLifecycleOwner(),
                new Observer<PagedList<Movie>>() {
                    @Override
                    public void onChanged(PagedList<Movie> movies) {
                        movieAdapter.submitList(movies);
                    }
                });

        moviesViewModel.getNetworkStateLiveData().observe(getViewLifecycleOwner() , networkState -> {
            movieAdapter.setNetworkState(networkState);
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_movielist , container ,false );
        initViews(view);
        return view ;
    }

    private void initViews(View view) {
        this.movie_list_recyclerView = view.findViewById(R.id.movie_list_recyclerView);

    }


    @Override
    public void movieItemClicked(Movie movie) {
        detailsViewModel.getMovieMutableLiveData().postValue(movie);
        if(!detailsViewModel.getMovieMutableLiveData().hasActiveObservers()) {
            MovieDetailsFragment movieDetailsFragment = new MovieDetailsFragment();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.FrameContainer , movieDetailsFragment)
                    .addToBackStack(null)
                    .commit();
        }

    }
}
