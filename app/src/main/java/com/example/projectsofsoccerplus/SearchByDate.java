package com.example.projectsofsoccerplus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchByDate extends AppCompatActivity {
    Button bt_Search;
    AdapterDB myDB;
    EditText t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_date);
        myDB= new AdapterDB(this);
        bt_Search =(Button) findViewById(R.id.button3);
        t1=(EditText) findViewById(R.id.editTextTextPersonName);

    }
    public void VIEW(String str) {
        String data = myDB.getData(str);
        ShowMessage.message(this,data);
    }
    public void SearchDate(View view) {
        String u1 = t1.getText().toString();
        if(u1.isEmpty()) {
            ShowMessage.message(getApplicationContext(),"Enter Date");
        }
      else {
            myDB= new AdapterDB(this);
            String data = myDB.getData(t1.getText().toString());
            ShowMessage.message(getApplicationContext(),data);
            t1.setText("");
            }
        }
    }

