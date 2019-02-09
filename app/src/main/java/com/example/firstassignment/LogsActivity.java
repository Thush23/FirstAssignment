package com.example.firstassignment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.jar.Attributes;


public class LogsActivity extends AppCompatActivity{

    String name;
    String age;
    String email;
    TextView tvemail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_2);

            TextView tvage = findViewById(R.id.tvage);
            TextView tvname = findViewById(R.id.tvname);
            tvemail = findViewById(R.id.tvemail);


            age = getIntent().getExtras().getString("Age");
            tvage.setText(age);



            /*name = intent.getStringExtra("Name");
            if (intent.hasExtra("Age")){
                age = intent.getStringExtra("Age",);
            }
            StringBuilder stringBuilder = new StringBuilder();
            //for (int i = 0; i<log.size();i++){
                stringBuilder.append(tvage1);
            //    stringBuilder.append("\n");
            //}*/
            tvname.setText(readDataFromFile());
            readFromPreferencesFile();
    }

    private void readFromPreferencesFile(){
        SharedPreferences sharedPreferences = getSharedPreferences
                ("MySharedPreferences", Context.MODE_PRIVATE);
        email = sharedPreferences.getString("Email", "");
        tvemail.setText(email); //setting email value in tvEmail
    }
    private String readDataFromFile(){
        File file = new File(getFilesDir(), "log.txt");
        int size = (int)file.length();
        byte[] contents = new byte[size];
        try (FileInputStream fileInputStream = new FileInputStream(file)){
            fileInputStream.read(contents);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(contents);

    }


}