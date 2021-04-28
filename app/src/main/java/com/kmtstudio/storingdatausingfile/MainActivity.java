package com.kmtstudio.storingdatausingfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        editText = findViewById(R.id.editTxtID);
        saveBtn = findViewById(R.id.saveBtnID);


        saveBtn.setOnClickListener(v -> {

            String text = editText.getText().toString();

            writeFromFile(text);

        });


        readFromFile();
    }

    public void writeFromFile(String text) {

        try {
            FileOutputStream outputStream = openFileOutput("diary.txt", Context.MODE_PRIVATE);
            outputStream.write(text.getBytes());
            outputStream.close();

            Toast.makeText(getApplicationContext(),"data saved successfully",Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void readFromFile() {

        try {
            FileInputStream inputStream = openFileInput("diary.txt");
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(streamReader);
            String lines;

            StringBuffer buffer = new StringBuffer();

            while ((lines=reader.readLine())!=null) {

                buffer.append(lines).append("\n");
            }

            editText.setText(buffer.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}