package com.example.jose.simon;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by Jose on 06/04/2017.
 */

public class ServicioMusica extends Service {
    MediaPlayer reproductor;

    @Override
    public void onCreate() {


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"Servicio activado",Toast.LENGTH_SHORT).show();
        Bundle b = intent.getExtras();
        if(b!=null){
        switch(b.getString("music")){
            case "rocky":
                reproductor =MediaPlayer.create(this, R.raw.rocky);
                reproductor.start();
                break;
            case "swars":
                reproductor =MediaPlayer.create(this, R.raw.starwars);
                reproductor.start();
                break;
            case "tetris":
                reproductor =MediaPlayer.create(this, R.raw.tetris);
                reproductor.start();
                break;

        }}

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        reproductor.stop();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
