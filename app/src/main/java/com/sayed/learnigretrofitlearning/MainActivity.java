package com.sayed.learnigretrofitlearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Model.Note;
import NoteAdapter.NoteAdapter;
import ViewModel.NoteViewModel;

public class MainActivity extends AppCompatActivity {
    private NoteViewModel noteViewModel ;
    private RecyclerView recyclerView ;
    private NoteAdapter noteAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadContents();
    }

    private void loadContents() {
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        noteViewModel.getAllnotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                //update the recycler view
                Toast.makeText(MainActivity.this , "OnChanged" , Toast.LENGTH_SHORT).show();
                noteAdapter.notifyDataSetChanged();
            }
        });

        //recycler view
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setItemAnimator( new DefaultItemAnimator());
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        //Adapter
       List<Note> notes = new ArrayList<>();
       noteAdapter = new NoteAdapter(notes);
       recyclerView.setAdapter(noteAdapter);


    }
}