package Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Update;

import java.util.List;

import Database.NoteDao;
import Database.NoteDatabase;
import Model.Note;

public class NoteRepository implements INoteRepository {
    private NoteDao noteDao ;
    private LiveData<List<Note>> allNotes ;

    public NoteRepository(Application application) {
        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        noteDao = noteDatabase.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    @Override
    public void insertNote(Note note) {
        new InserAsyncTask(this.noteDao).execute(note);
    }

    @Override
    public void updateNote(Note note) {
        new UpdateAsyncTask(this.noteDao).execute(note);
    }

    @Override
    public void deleteNote(Note note) {
        new DeleteAsyncTask( this.noteDao).execute(note);
    }

    @Override
    public void deleteAllNodes() {
        new DeleteAllNotesAsyncTask(this.noteDao).execute();
    }

    @Override
    public LiveData<List<Note>> getAllnotes() {
        return noteDao.getAllNotes();
    }

    /*
    * find out the difference between private class and
    * private static class */
    private  class InserAsyncTask extends AsyncTask<Note , Void ,Void> {
        NoteDao noteDao ;

        public InserAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null ;
        }
    }

    private  class UpdateAsyncTask extends AsyncTask<Note , Void ,Void> {
        NoteDao noteDao ;

        public UpdateAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null ;
        }
    }

    private  class DeleteAsyncTask extends AsyncTask<Note , Void ,Void> {
        NoteDao noteDao ;

        public DeleteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null ;
        }
    }

    private  class DeleteAllNotesAsyncTask extends AsyncTask<Void , Void ,Void> {
        NoteDao noteDao ;

        public DeleteAllNotesAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNode();
            return null;
        }
    }


}
