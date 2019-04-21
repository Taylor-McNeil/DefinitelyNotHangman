package com.example.definitelynothangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class GameOver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        TextView textView = findViewById(R.id.RealWord);
        Intent intent = getIntent();
        textView.setText(" You should have guessed "+intent.getStringExtra("Word"));
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
