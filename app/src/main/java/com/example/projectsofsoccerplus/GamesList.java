package com.example.projectsofsoccerplus;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class GamesList extends AppCompatActivity {
    AdapterDB myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_list);
       // myDb = new AdapterDB(this);
        //myDb.getData();
    }
}