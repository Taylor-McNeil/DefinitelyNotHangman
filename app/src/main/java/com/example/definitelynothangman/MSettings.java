package com.example.definitelynothangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msettings);
    }

    public void createAGame(View v){
        Intent startServer = new Intent(this,CreateServer.class);
        startActivity(startServer);
    }
    public void joinAGame(View v){
        Intent joinServer = new Intent(this,JoinGame.class);
        startActivity(joinServer);
    }
}
