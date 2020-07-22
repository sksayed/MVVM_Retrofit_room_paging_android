package ui.callback;

import model.Movie;
@FunctionalInterface
public  interface OnMovieItemClickListener {

    void movieItemClicked (Movie movie) ;
}
