package com.sayed.learnigretrofitlearning.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.sayed.learnigretrofitlearning.R;

import Switch.ButtonLight;
import Switch.SwitchBoard;

public class MainActivity extends AppCompatActivity {

    //here it has been used as a view
    //inside it contains a button
    ButtonLight button;
    Button singleButtonLight;
    SwitchBoard board;
    Switch switchBoardSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        loadContents();
        initListeners();
    }

    private void initListeners() {
        switchBoardSwitch.setOnCheckedChangeListener((view, isChecked)->{
            if(isChecked){
                board.turnOn();
            }else {
                board.turnOff();
            }
        });
    }

    private void loadContents() {
        board = new SwitchBoard();
        button = new ButtonLight(singleButtonLight);
        board.registerToSwitchBoard(button);
    }

    private void initView() {
        this.singleButtonLight = findViewById(R.id.singleButtonLight);
        this.switchBoardSwitch = findViewById(R.id.switchBoardSwitch);
    }
}