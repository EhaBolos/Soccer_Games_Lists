package com.example.projectsofsoccerplus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearshTeam extends AppCompatActivity {
    Button bt_Search;
    AdapterDB myDB;
    EditText t8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searsh_team);
        myDB= new AdapterDB(this);
        bt_Search =(Button) findViewById(R.id.button7);
        t8=(EditText) findViewById(R.id.editTextTextPersonName2);
    }
    public void VIEW(String str) {
        String data = myDB.getDataForTeam(str);
        ShowMessage.message(this,data);
    }
    public void SearchTeam(View view) {
        String u1 = t8.getText().toString();
        if(u1.isEmpty()) {
            ShowMessage.message(getApplicationContext(),"Enter Team");
        }
        else {
            myDB= new AdapterDB(this);
            String data = myDB.getDataForTeam(t8.getText().toString());
            ShowMessage.message(getApplicationContext(),data);
            t8.setText("");
        }
    }
}
