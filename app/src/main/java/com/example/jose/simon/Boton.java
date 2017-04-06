package com.example.jose.simon;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.SoundPool;

/**
 * Created by Jose on 30/03/2017.
 */

public class Boton extends Thread {
    int id;
    SoundPool sonido;
    Drawable encendido;
    Drawable apagado;
    Context contexto;

    public Boton(int id, SoundPool sonido, Drawable encendido, Drawable apagado, Context contexto){
        this.id=id;
        this.sonido=sonido;
        this.encendido=encendido;
        this.apagado=apagado;
        this.contexto=contexto;

    }


}
