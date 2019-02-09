package com.example.firstassignment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

import static com.example.firstassignment.R.id.btnsave;

public class MainActivity extends AppCompatActivity{
    //DECLARING OBJECTS//
    EditText etname;
    TextView tvname;
    EditText etage;
    TextView tvage;
    EditText etemail;
    TextView tvemail;
    Button btnsave;
    Button btnreset;
    TextView nameresults;
    TextView ageresults;
    TextView emailresults;
    private List<String> Name = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //FINDING THE OBJECTS//
        btnsave = findViewById(R.id.btnsave);
        tvage = findViewById(R.id.tvage);
        tvemail = findViewById(R.id.tvemail);
        tvname = findViewById(R.id.tvname);
        etname = findViewById(R.id.etname);
        etage = findViewById(R.id.etage);
        etemail = findViewById(R.id.etemail);
        nameresults = findViewById(R.id.tvname);
        ageresults = findViewById(R.id.tvage);
        emailresults = findViewById(R.id.tvemail);

        btnreset = findViewById(R.id.btnreset);
        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            //DECLARING RESET FUNCTION//
            public void onClick(View v) {
                etname.setText("");
                etage.setText("");
                etemail.setText("");
            }
        });
        //ON.CLICK.LISTENER//
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            //DECLARING SHARED PREFERENCES//
            public void onClick(View v) {
                String deets = (etname.getText().toString());
                Name.add(deets);
                if (Name.size() > 0) {
                    writeLogsToFile();
                }
                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPreferences",
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Email", etemail.getText().toString());
                editor.apply();


                //DECLARING INTENT//
                Intent intent = new Intent ( MainActivity.this, LogsActivity.class );
                String age = etage.getText().toString();
                intent.putExtra("Age", age);
                startActivity(intent);
            }
        });
    }
        //WRITE LOGS TO FILE VIA OUTPUT/INPUT STREAM//
        private void writeLogsToFile() {
            File file = new File(getFilesDir(), "log.txt");
            //TRY//
            try (FileOutputStream fileOutputStream = openFileOutput
                    ("log.txt", Context.MODE_PRIVATE)) {
                StringBuilder stringBuilder = new StringBuilder();
                //FOR//
                for (String result : Name) {
                    stringBuilder.append(result);
                    stringBuilder.append("\n");
                }
                fileOutputStream.write(stringBuilder.toString().getBytes());
            //CATCH//
            } catch (IOException ioException) {
                Toast.makeText(this, "File Not Found", Toast.LENGTH_SHORT).show();
                ioException.printStackTrace();
            }
        }
    }