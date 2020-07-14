package Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import Model.Note;

/*
to make a database
we need to declare it with @Database
annotation
*  */
@Database(entities = {Note.class} , version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    private static NoteDatabase databaseInstance ;

    public abstract NoteDao noteDao();
    public static synchronized NoteDatabase getInstance(Context context) {
        if (databaseInstance == null) {
            databaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return databaseInstance;
    }

    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new NoteDatabase.PopulateDatabaseAsync(databaseInstance).execute();
        }
    };

    private static class PopulateDatabaseAsync extends AsyncTask<Void , Void , Void> {

        private NoteDao noteDao ;

        public PopulateDatabaseAsync(NoteDatabase noteDatabase) {
            this.noteDao = noteDatabase.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert( new Note("Title1" , "Complete Room Architecture" , 1));
            noteDao.insert( new Note("Title2" , "Complete 4 phases" , 2));
            noteDao.insert( new Note("Title3" , "Complete Java 8 " , 3));
            noteDao.insert( new Note("Title4" , "Complete Job Tasks" , 4));
            return null;
        }
    }
 }
