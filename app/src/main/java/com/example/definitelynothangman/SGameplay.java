package com.example.definitelynothangman;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.solver.widgets.ConstraintHorizontalLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SGameplay extends AppCompatActivity {
    String word2Guess;


    LinearLayout ParentLayout;
    int numberOfIncorrectGuesses=0;
    int numberofCorrectGuesses=0;
    protected static MediaPlayer gamesong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sgameplay);
        ParentLayout = findViewById(R.id.Letters);
        gamesong =MediaPlayer.create(SGameplay.this,R.raw.horsestowater);
        gamesong.start();

        try{
            wordPicker();
        }catch (FileNotFoundException e){
            Log.d("Error","File Not Found");
        }
        dynamicTextViews();

        TextView Iview = findViewById(R.id.IncorrectView);
        String previous = Iview.getText().toString();
        Iview.setText(previous+"\n");
    }

    public void wordPicker() throws FileNotFoundException  {
        BufferedReader reader = new BufferedReader(new FileReader(new
                File(getFilesDir()+File.separator+"wordsv9.txt")));
        String read;
        StringBuilder sb = new StringBuilder();

        try{
            while((read = reader.readLine()) != null){
                sb.append(read);
            }reader.close();

        }catch (IOException e){
            Log.d("Error:","IO Exception");
        }
        String wordLog=sb.toString();


        String [] randomWords = wordLog.split(" ");
        int randomNumber = (int)(Math.random()*randomWords.length);
        word2Guess=randomWords[randomNumber];
    }


    /**
     * This method is retrieving a letter that the user entered from the keyboard
     * @param v Button clicked
     */
    public void readLetter(View v){

        EditText EditLetterUI = (EditText)findViewById(R.id.Letter);
        String letter = EditLetterUI.getText().toString();

        if(v!=null){
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }

        //This line allows the letter to be removed from the screen once the user guesses the letter
        EditLetterUI.setText("");

        if(letter.length()!=1){
            Toast.makeText(this,"Please input a letter",Toast.LENGTH_SHORT).show();
        } else{
            checkLetter(letter);
        }

        Log.d("Issa log","The Letter is "+ letter );
    }


    /**
     * This method is determining if the user entered a correct letter for the word
     * @param letter - This will be passed into this method from the readLetter function
     */
    public void checkLetter(String letter){

        String upperLetter =letter.toUpperCase();
        char letterEntered = upperLetter.charAt(0);
        boolean Correct = false;

        for(int i=0;i<word2Guess.length();i++){
            if(letterEntered==word2Guess.charAt(i)){
                //Log.d("1","If you dont see a match was found. The Error is in show letter");
                showLetter(i,letterEntered);
                Log.d("Issa Tag","A match was found");
                Correct = true;
                numberofCorrectGuesses++;
            } else{
            Log.d("Issa new log","Match not found");

            }
        }

        if(!Correct){
            incorrectLetter();
            displayIncorrectLetter(letterEntered);
        }
        if(numberofCorrectGuesses==word2Guess.length()){
            Intent intent = new Intent(this,Victory.class);
            startActivity(intent);

            //clearScreen();
            //wordPicker();
        }

        if(numberOfIncorrectGuesses==4){

            Intent gameOver = new Intent(this,GameOver.class);
            gameOver.putExtra("Word",word2Guess);
            startActivity(gameOver);
        }

    }

    /**
     * This method is called when a user picks the incorrect letter. If they pick for incorrect letters, GAMEOVER
     */
    public void incorrectLetter(){
        numberOfIncorrectGuesses++;
        ImageView imageView = findViewById(R.id.imageView2);

        switch (numberOfIncorrectGuesses) {
            case 1:
                imageView.setImageResource(R.drawable.m3);
                break;
            case 2:
                imageView.setImageResource(R.drawable.m4);
                break;
            case 3:
                imageView.setImageResource(R.drawable.m5);
                break;
            case 4:
                imageView.setImageResource(R.drawable.m6);
                Intent gameOver = new Intent(this,GameOver.class);
                gameOver.putExtra("Word",word2Guess);
                startActivity(gameOver);
                break;
        }

    }

    /**
     * This function will display a letter on the screen
     * @param position - where the letter will be shown
     * @param letter - the letter to be displayed
     */
        public void showLetter(int position, char letter){
        TextView textView = (TextView) ParentLayout.getChildAt(position);
        textView.setText(Character.toString(letter));

    }

    /**
     * This function dynamically updates the required letter spaces for the word to guess
     */
    public void dynamicTextViews(){

        ParentLayout.setOrientation(LinearLayout.HORIZONTAL);

        for(int i=0;i<word2Guess.length();i++){
            TextView textView = new TextView(this);
            textView.setText("_");
            textView.setPadding(5,0,0,5);
            textView.setTypeface(null, Typeface.BOLD);
            textView.setTextSize(40);
            ParentLayout.addView(textView);

        }
    }

    /**
     * Updates the invalid words guessed by the user
     * @param letter - the incorrect letter guessed by the user
     */
    public void displayIncorrectLetter(char letter){

        TextView textView = findViewById(R.id.IncorrectView);
        String previous = textView.getText().toString();
        textView.setText(previous+Character.toString(letter));
    }

    public void clearScreen(){
        TextView invalidLetters = findViewById(R.id.IncorrectView);
        invalidLetters.setText("Invalid Letters");


        ImageView imageView = findViewById(R.id.imageView2);
        imageView.setImageResource(R.drawable.m2);

        numberofCorrectGuesses=0;
        numberOfIncorrectGuesses=0;



        //TODO Make this dynamic for the next word

        //This piece of code works
        /*for(int i=0;i<word2Guess.length();i++){
            TextView textView = (TextView) ParentLayout.getChildAt(i);
            textView.setText(Character.toString('m'));
        }*/

/*
       ConstraintLayout constraintLayout=findViewById(R.id.MasterLayout);
       constraintLayout.removeView(ParentLayout);
       wordPicker();*/





       /*LinearLayout Letters = findViewById(R.id.Letters);
        constraintLayout.addView(Letters);
       dynamicTextViews();*/
    }

}
