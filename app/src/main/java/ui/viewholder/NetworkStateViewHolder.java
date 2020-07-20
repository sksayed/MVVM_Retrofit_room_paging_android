package ui.viewholder;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sayed.learnigretrofitlearning.R;

import model.NetworkState;

public class NetworkStateViewHolder extends RecyclerView.ViewHolder {
    ProgressBar progressBar ;
    TextView msg ;

    public NetworkStateViewHolder(@NonNull View itemView) {
        super(itemView);
        progressBar = itemView.findViewById(R.id.progress_bar);
        msg = itemView.findViewById(R.id.error_msg);
    }

    public void bindTo (NetworkState networkState) {
        if (networkState.getStatus() == NetworkState.Status.RUNNING ) {
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.INVISIBLE);
        }

        if(networkState.getStatus() ==  NetworkState.Status.FAILED) {
            msg.setText(networkState.getMsg());
            msg.setVisibility(View.VISIBLE);
        }else {
            msg.setVisibility(View.GONE);
        }


    }
}
