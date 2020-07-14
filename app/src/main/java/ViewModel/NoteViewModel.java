package ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.sayed.learnigretrofitlearning.CustomApplication;

import java.util.List;

import Model.Note;
import Repository.INoteRepository;
import Repository.NoteRepository;

public class NoteViewModel extends ViewModel implements IViewModel {
    private INoteRepository repository;
    private LiveData<List<Note>> allNotes;

    public NoteViewModel() {
        repository = new NoteRepository(CustomApplication.getInstance());
        allNotes = repository.getAllnotes();
    }

    @Override
    public void insertNote(Note note) {
        repository.insertNote(note);
    }

    @Override
    public void updateNote(Note note) {
        repository.updateNote(note);
    }

    @Override
    public void deleteNote(Note note) {
        repository.deleteNote(note);
    }

    @Override
    public void deleteAllNodes() {
        repository.deleteAllNodes();
    }

    @Override
    public LiveData<List<Note>> getAllnotes() {
        return this.allNotes;
    }
}
