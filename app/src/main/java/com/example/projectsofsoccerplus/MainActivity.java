package com.example.projectsofsoccerplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    AdapterDB myDB;
    Button btn_List;
    Button btn_Date;
    Button btn_Team;
    Button btn_Add;
    Button btn_Update;
    Button btn_Delete;
    EditText delTxt;
    EditText updateold;
    EditText updatenew;
    EditText theID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB= new AdapterDB(this);
        btn_List = (Button) findViewById(R.id.button);

        btn_Date = (Button) findViewById(R.id.button4);
        delTxt=(EditText) findViewById(R.id.editTextTextPersonName8);
        updateold=(EditText) findViewById(R.id.editTextTextPersonName9);
        updatenew=(EditText) findViewById(R.id.editTextTextPersonName10);
        theID=(EditText) findViewById(R.id.editTextTextPersonName11);

        btn_Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewActivity_Date();
            }
        });
        btn_Team = (Button) findViewById(R.id.button5);
        btn_Team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewActivity_Team();
            }
        });
        btn_Add = (Button) findViewById(R.id.button6);
        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewActivity_Add();
            }
        });
        btn_Update = (Button) findViewById(R.id.button9);

        btn_Delete = (Button) findViewById(R.id.button10);

    }



    private void openNewActivity_Add() {
        Intent intent = new Intent(this,AddGames.class);
        startActivity(intent);
    }

    private void openNewActivity_Team() {
        Intent intent = new Intent(this,SearshTeam.class);
        startActivity(intent);
    }

    private void openNewActivity_Date() {
        Intent intent = new Intent(this,SearchByDate.class);
        startActivity(intent);
    }
    public void delete( View view) {
        myDB= new AdapterDB(this);
        String id = delTxt.getText().toString();
        if(id.isEmpty()) {
            ShowMessage.message(getApplicationContext(),"Enter Data");
        }
        else{
            int a= myDB.delete(id);
            if(a<=0)
            {
                ShowMessage.message(getApplicationContext(),"Unsuccessful");
                delTxt.setText("");
            } else {
                ShowMessage.message(this, "DELETED");
                delTxt.setText("");
            }
        }
    }


    public void VIEW(View view) {
        String data = myDB.getData();
        ShowMessage.message(this,data);
    }

    public void update(View view) {
        String u1 = updateold.getText().toString();
        String u2 = updatenew.getText().toString();
        String u3 = theID.getText().toString();

        if(u1.isEmpty() || u2.isEmpty()) {
            ShowMessage.message(getApplicationContext(),"Enter Data");
        }
        else {
            int a= myDB.updateDate( u1, u2,u3);
            if(a<=0) {
                ShowMessage.message(getApplicationContext(),"Unsuccessful");
                updateold.setText(""); updatenew.setText("");
            }
            else {
                ShowMessage.message(getApplicationContext(),"Updated");
                updateold.setText(""); updatenew.setText("");
            }
        }
    }
}