package ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.sayed.learnigretrofitlearning.R;
import com.sayed.learnigretrofitlearning.databinding.*;
import com.squareup.picasso.Picasso;

import ui.viewmodel.MovieDetailsViewModel;

import static utilities.Constants.BIG_IMAGE_URL_PREFIX;

public class MovieDetailsFragment extends Fragment {
    private MovieDetailsViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        FragmentMovieDetailsBinding viewDataBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_movie_details, container, false);
        View view = viewDataBinding.getRoot();

        viewModel = new ViewModelProvider(getActivity()).get(MovieDetailsViewModel.class);
        viewModel.getMovieMutableLiveData().observe(getViewLifecycleOwner() , viewDataBinding::setMovie);
        return view;
    }

    @BindingAdapter("android:src")
    public static void setImageUrl(ImageView view, String url) {
        if(url != null) {
            Picasso.get().load(BIG_IMAGE_URL_PREFIX + url).into(view);
        }
    }
}
