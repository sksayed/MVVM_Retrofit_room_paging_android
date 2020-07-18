package com.sayed.learnigretrofitlearning;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import Model.Note;
import NoteAdapter.NoteAdapter;
import ViewModel.NoteViewModel;

public class MainActivity extends AppCompatActivity {
    public static final String TITLE_EXTRA = "com.sayed.learnigretrofitlearning.MainActivity.TITLE_EXTRA";
    public static final String NOTE_ID_EXTRA = "com.sayed.learnigretrofitlearning.MainActivity.NOTE_ID_EXTRA";
    public static final String TEXT_EXTRA = "com.sayed.learnigretrofitlearning.MainActivity.TEXT_EXTRA";
    public static final String PRIORITY_EXTRA = "com.sayed.learnigretrofitlearning.MainActivity.PRIORITY_EXTRA";
    public static final int REQUEST_CODE_ADD = 1234 ;
    public static final int REQUEST_CODE_EDIT = 1235 ;
    private NoteViewModel noteViewModel ;
    private RecyclerView recyclerView ;
    private ItemTouchHelper mItemTouchHelper ;
    private NoteAdapter noteAdapter ;
    private FloatingActionButton fab ;
    private List<Note> mNoteList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadContents();
    }

    private void loadContents() {
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(l -> {
            Intent intent = new Intent(this , AddNoteActivity.class);
            startActivityForResult(intent, REQUEST_CODE_ADD);

        });
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        noteViewModel.getAllnotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                //update the recycler view
                Toast.makeText(MainActivity.this , "OnChanged" , Toast.LENGTH_SHORT).show();
                mNoteList  = notes ;
                noteAdapter.setNoteList(notes);
                noteAdapter.notifyDataSetChanged();
            }
        });

        //recycler view
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setItemAnimator( new DefaultItemAnimator());
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        ItemTouchHelper.SimpleCallback simpleCallback =
        new ItemTouchHelper.SimpleCallback(0 , ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT){
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //this was my implementation
               /* int id = (int)viewHolder.itemView.getTag();
               Optional<Note> note = mNoteList.stream()
                        .filter( note1 ->  note1.getId() == id)
                        .findAny();
               if(note.isPresent()) {
                   noteViewModel.deleteNote(note.get());
               }*/
                //this implementation belongs to
                //coding in flow
                noteViewModel.deleteNote(noteAdapter.getNoteAt(viewHolder.getAdapterPosition()));
            }
        };

        //On swipe Left or Right
        //Activate Item Touch Helper
        mItemTouchHelper = new ItemTouchHelper(simpleCallback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
        //Adapter
       List<Note> notes = new ArrayList<>();
       noteAdapter = new NoteAdapter(notes);
       recyclerView.setAdapter(noteAdapter);

       //update note
       noteAdapter.setOnItemClickListener(new NoteAdapter.OnItemClickListener() {
           @Override
           public void onItemClick(Note note) {
               Intent intentForEditNote = new Intent(MainActivity.this, EditNoteActivity.class);
               intentForEditNote.putExtra(TITLE_EXTRA, note.getTitle());
               intentForEditNote.putExtra(NOTE_ID_EXTRA, note.getId());
               intentForEditNote.putExtra(TEXT_EXTRA, note.getText());
               intentForEditNote.putExtra(PRIORITY_EXTRA, note.getPriority());
               startActivityForResult(intentForEditNote , REQUEST_CODE_EDIT);
           }
       });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ADD && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddNoteActivity.TITLE_EXTRA);
            String text = data.getStringExtra(AddNoteActivity.TEXT_EXTRA);
            int priority = data.getIntExtra(AddNoteActivity.PRIORITY_EXTRA , 1);
            noteViewModel.insertNote( new Note(title , text , priority));
            Toast.makeText(this , "Data has been saved" , Toast.LENGTH_SHORT).show();
        }else if (requestCode == REQUEST_CODE_EDIT && resultCode == RESULT_OK){
            String title = data.getStringExtra(EditNoteActivity.TITLE_EXTRA);
            String text = data.getStringExtra(EditNoteActivity.TEXT_EXTRA);
            int priority = data.getIntExtra(EditNoteActivity.PRIORITY_EXTRA , 1);
            int id = data.getIntExtra(EditNoteActivity.NOTE_ID_EXTRA , -1);
            if (id != -1) {
                Note note = new Note(title , text , priority);
                note.setId(id);
                noteViewModel.updateNote(note);
            }
        }
    }
}