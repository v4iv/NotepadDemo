package com.v4iv.notepaddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    Button mSaveButton, mDisplayButton;
    EditText mEditText;
    TextView mTextView;

    String data;
    private String file = "mynote";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText) findViewById(R.id.editText);
        mSaveButton = (Button) findViewById(R.id.bSave);
        mDisplayButton = (Button) findViewById(R.id.bDisplay);
        mTextView = (TextView) findViewById(R.id.textView);

        //Save Button
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = mEditText.getText().toString();
                try {
                    FileOutputStream fileOutputStream = openFileOutput(file, MODE_WORLD_READABLE);
                    fileOutputStream.write(data.getBytes());
                    fileOutputStream.close();
                    Toast.makeText(getBaseContext(), "Note Saved", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //Display Button
        mDisplayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fileInputStream = openFileInput(file);
                    int cursor;
                    String temp = "";
                    while ((cursor = fileInputStream.read()) != -1){
                        temp = temp + Character.toString((char)cursor);
                    }
                    mTextView.setText(temp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
