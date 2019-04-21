package com.example.definitelynothangman;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CreateServer extends AppCompatActivity {

    private Socket socket;
    private ServerSocket server;
    private DataInputStream streamIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_server);
       // new makeAServer().execute();

    }

    public class makeAServer extends AsyncTask<Void, Void, Void> {
        TextView content = findViewById(R.id.TextFromClient);



        @Override
        protected Void doInBackground(Void... voids) {
            try {
                server = new ServerSocket(6000);
                socket = server.accept();
                streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                while (true) {
                    String line = streamIn.readUTF();
                    content.setText(line);
                }
            } catch (IOException ioe) {
                Log.d("Message 10:", "IOException");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }


}




