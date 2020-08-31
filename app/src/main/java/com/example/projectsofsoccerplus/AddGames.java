package com.example.projectsofsoccerplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddGames extends AppCompatActivity {
    AdapterDB myDb;
    private EditText textForC;
    private EditText textForD;
    private EditText textForA;
    private EditText textForB;
    private Button bt_Add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_games);
        myDb= new AdapterDB(this);
        textForC = (EditText) findViewById(R.id.editTextTextPersonName3);
        textForD = (EditText) findViewById(R.id.editTextTextPersonName4);
        textForA = (EditText) findViewById(R.id.editTextTextPersonName5);
        textForB = (EditText) findViewById(R.id.editTextTextPersonName6);
        bt_Add = (Button) findViewById(R.id.button2);
        bt_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewActivity_Update();
            }
        });
    }


    private void openNewActivity_Update() {
        addNewGame();
    }
    public void addNewGame() {
        String t1 = textForC.getText().toString();
        String t2 = textForD.getText().toString();
        String t3 = textForA.getText().toString();
        String t4 = textForB.getText().toString();
    if(t1.isEmpty() || t2.isEmpty() || t3.isEmpty() || t4.isEmpty()) {
        ShowMessage.message(getApplicationContext(),"Enter the missing values pls");
     }
    else {
        long id = myDb.insertData(t1, t2, t3, t4);
        if (id <= 0) {
                ShowMessage.message(getApplicationContext(), "Insertion Unsuccessful");
        }
            else {
                ShowMessage.message(getApplicationContext(), "Insertion Successful");
            }
        textForC.setText("");
        textForD.setText("");
        textForA.setText("");
        textForB.setText("");
    }
    }
}
