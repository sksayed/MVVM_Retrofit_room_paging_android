package Repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import Model.Note;

public interface INoteRepository {
    void insertNote (Note note);

    void updateNote(Note note);

    void deleteNote(Note note);

    void deleteAllNodes();

    LiveData<List<Note>> getAllnotes();
}
