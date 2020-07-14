package Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import Model.Note;

/*to be a dao
 * it has to be annotated with
 * Doa
 * */
@Dao
public interface NoteDao {
    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM note_table")
    void deleteAllNode ();

    @Query("SELECT * FROM note_table ORDER BY priority_col DESC")
    LiveData<List<Note>> getAllNotes();
}
