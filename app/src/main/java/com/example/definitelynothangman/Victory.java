package com.example.definitelynothangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Victory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victory);
    }
    public void MainMenu(View v){
        Intent exitGame = new Intent(this,MainActivity.class);
        startActivity(exitGame);
    }

    public void PlayAgain(View v){
        Intent playAgain = new Intent(this,SGameplay.class);
        startActivity(playAgain);
    }
    protected void onPause(){
        super.onPause();
        SGameplay.gamesong.release();
    }
}
