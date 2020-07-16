package NoteAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sayed.learnigretrofitlearning.R;

import java.util.ArrayList;
import java.util.List;

import Model.Note;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteAdapter.NotesViewHolder> {

    private List<Note> noteList ;

    public NoteAdapter(List<Note> noteList) {
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item ,parent , false);
       return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.title.setText(note.getTitle());
        holder.description.setText(note.getText());
        holder.priority.setText(note.getPriority());
    }

    @Override
    public int getItemCount() {
        return noteList.size() ;
    }

    public class  NotesViewHolder extends RecyclerView.ViewHolder {
        public TextView priority ;
        public TextView title ;
        public TextView description ;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
         priority = itemView.findViewById(R.id.text_view_priority);
         title = itemView.findViewById(R.id.text_view_title);
         description = itemView.findViewById(R.id.text_view_description);
        }
    }
}
