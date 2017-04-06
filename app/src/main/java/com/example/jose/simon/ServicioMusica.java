package com.example.jose.simon;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by Jose on 06/04/2017.
 */

public class ServicioMusica extends Service {
    MediaPlayer reproductor;

    @Override
    public void onCreate() {
        Toast.makeText(this,"Servicio creado",Toast.LENGTH_SHORT).show();
        reproductor =  MediaPlayer.create(this,R.raw.intro);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"Servicio activado",Toast.LENGTH_SHORT).show();
        reproductor.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this,"Servicio detendio",Toast.LENGTH_SHORT).show();
        reproductor.stop();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
