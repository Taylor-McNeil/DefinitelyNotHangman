package com.example.definitelynothangman;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Words extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        TextView textView = findViewById(R.id.Dictionary);
        String previous = textView.getText().toString();
        textView.setText(previous+"\n");
    }

    /**
     *  This function is grabbing the word entered by the user and writing it to a file.
     * @param v
     * @throws IOException
     */
    public void write2File(View v) throws IOException {
        EditText EditWord = (EditText)findViewById(R.id.Word_Layout);
        String Word = EditWord.getText().toString();

        if(v!=null){
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(new
                File(getFilesDir()+File.separator+"wordsv8.txt"),true));
                String upperWord=Word.toUpperCase();
                writer.append(upperWord+" ");
                writer.close();
                displayWords(Word);

        //This line allows the word to be removed from the screen once the user inputs the word
        EditWord.setText("");
    }

    /**
     * This function is taking thw word the user entered and displaying it on the screen
     * @param Word- a string the user will enter
     */
    public void displayWords(String Word){
        TextView textView = findViewById(R.id.Dictionary);
        String previous = textView.getText().toString();
        textView.setText(previous+Word+"\n");
    }

    public void startGame(View v){
        Intent StartGame = new Intent(this,SGameplay.class);
        startActivity(StartGame);
    }

    /*protected void onPause(){
        super.onPause();
        MainActivity.titlesong.release();
    }*/


}
