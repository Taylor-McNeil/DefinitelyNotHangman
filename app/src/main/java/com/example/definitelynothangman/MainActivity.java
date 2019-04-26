package com.example.definitelynothangman;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
   // protected static MediaPlayer titlesong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //titlesong = MediaPlayer.create(MainActivity.this,R.raw.zitronsoundwildwest);
        //titlesong.start();
    }

    public void SinglePlayer(View v){

        Intent startGame = new Intent(this,Words.class);
        startActivity(startGame);
    }

    public void Multiplayer(View v){
        Intent startMultiplayerGame = new Intent(this,MSettings.class);
        startActivity(startMultiplayerGame);
    }

    public void Credits(View v){
        Intent startMultiplayerGame = new Intent(this,Credits.class);
        startActivity(startMultiplayerGame);
    }
    /*protected void onPause(){
        super.onPause();
        titlesong.release();
    }*/



}
