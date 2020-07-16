package com.sayed.learnigretrofitlearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import database.GroceryAdapter;
import database.GroceryContract;
import database.GroceryDbHelper;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase mDatabase;
    private GroceryAdapter mAdapter;
    private EditText mEditTextName;
    private TextView mTextViewAmount;
    private int mAmount = 0;
    private RecyclerView recyclerview;

    private Button button_add;
    private Button button_increase;
    private Button button_decrease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        loadItems();
        initListeners();
    }

    private void initListeners() {
        this.button_increase.setOnClickListener( l->{
            mAmount++;
            mTextViewAmount.setText(String.valueOf(mAmount));
        });

        this.button_decrease.setOnClickListener(l->{
            if(mAmount > 0) {
                mAmount--;
                mTextViewAmount.setText(String.valueOf(mAmount));
            }
        });

        this.button_add.setOnClickListener(l ->{
            if (mEditTextName.getText().toString().trim().length() == 0 || mAmount == 0) {
                return;
            }

            String name = mEditTextName.getText().toString();
            ContentValues cv = new ContentValues();
            cv.put(GroceryContract.GroceryEntry.COLUMN_NAME, name);
            cv.put(GroceryContract.GroceryEntry.COLUMN_AMOUNT, mAmount);
            mDatabase.insert(GroceryContract.GroceryEntry.TABLE_NAME, null, cv);
            mAdapter.swapCursor(getAllItems());
            mEditTextName.getText().clear();
        });
    }

    private void loadItems() {
        mDatabase = new GroceryDbHelper(this)
                .getWritableDatabase();
        mAdapter = new GroceryAdapter(getAllItems());
        recyclerview.setLayoutManager( new LinearLayoutManager(this));
        recyclerview.setItemAnimator( new DefaultItemAnimator());
        recyclerview.setAdapter(mAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
               ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
           @Override
           public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
               return false;
           }

           @Override
           public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                removeFromDB((long) viewHolder.itemView.getTag());
           }
       }).attachToRecyclerView(this.recyclerview);
    }

    private void removeFromDB(long tag) {
       int result = mDatabase.delete(GroceryContract.GroceryEntry.TABLE_NAME ,
                GroceryContract.GroceryEntry._ID+"="+tag,
                null
                );
        mAdapter.swapCursor(getAllItems());
    }


    private void initView() {
        this.button_add = findViewById(R.id.button_add);
        this.button_increase = findViewById(R.id.button_increase);
        this.button_decrease = findViewById(R.id.button_decrease);
        this.mEditTextName = findViewById(R.id.edittext_name);
        this.mTextViewAmount = findViewById(R.id.textview_amount);
        this.recyclerview = findViewById(R.id.recyclerview);
    }



    private Cursor getAllItems () {
        //here sql database returns a cursor to work with

        return mDatabase.query(GroceryContract.GroceryEntry.TABLE_NAME ,
                null,
                null,
                null,
                null,
                null,
                GroceryContract.GroceryEntry.COLUMN_TIMESTAMP+" "+"DESC"
                );
    }

}