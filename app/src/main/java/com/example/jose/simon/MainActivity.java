package com.example.jose.simon;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

//Prueba prueba
public class MainActivity extends AppCompatActivity {
    int sonAzul, sonVerde, sonRojo, sonAmarillo, sonError;
    int duracion =500;
    int tirada=0;
    Bundle b;
    SoundPool soundPool;
    boolean tornJugador=false;
    boolean jugando=true;
    ImageView ibAzul,ibRojo,ibAmarillo,ibVerde,ibPlay;
    TextView tvPuntuacio, tvPuntos;
    View.OnClickListener listenerColor;
    ArrayList<Integer> tiradesJugador = new ArrayList<>();
    ArrayList<Integer> tiradesMaquina = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jocsimon);
        prepareBundle();
        initComponents();
        createSoundPool();
        loadSounds();
        prepareListener();

    }

    private void prepareBundle() {
        b=this.getIntent().getExtras();
        if(b!=null){
            b = new Bundle();
        }
    }

    private void prepareListener() {
        listenerColor = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tornJugador == true && jugando == true) {
                    if (v.getId() == R.id.ibAzul) {
                        azul();
                        tiradesJugador.add(0);
                    } else if (v.getId() == R.id.ibRojo) {
                        rojo();
                        tiradesJugador.add(1);
                    } else if (v.getId() == R.id.ibVerde) {
                        verde();
                        tiradesJugador.add(2);
                    } else if (v.getId() == R.id.ibAmarillo) {
                        amarillo();
                        tiradesJugador.add(3);
                    }
                    comprovatirada();
                }else{
                    if (v.getId() == R.id.ibPlay){
                        reprodueixSonsMaquina();
                    }

                }
            }


        };


        ibAzul.setOnClickListener(listenerColor);
        ibRojo.setOnClickListener(listenerColor);
        ibAmarillo.setOnClickListener(listenerColor);
        ibVerde.setOnClickListener(listenerColor);
        ibPlay.setOnClickListener(listenerColor);
    }

    private void loadSounds() {
        sonAzul = soundPool.load(this, R.raw.sounds_01,1);
        sonVerde = soundPool.load(this, R.raw.sounds_02, 1);
        sonRojo = soundPool.load(this, R.raw.sounds_03, 1);
        sonAmarillo = soundPool.load(this, R.raw.sounds_04, 1);
        sonError = soundPool.load(this, R.raw.error, 1);
    }

    private void initComponents() {
        ibAzul = (ImageView) findViewById(R.id.ibAzul);
        ibRojo = (ImageView) findViewById(R.id.ibRojo);
        ibAmarillo = (ImageView) findViewById(R.id.ibAmarillo);
        ibVerde = (ImageView) findViewById(R.id.ibVerde);
        ibPlay = (ImageView) findViewById(R.id.ibPlay);
        tvPuntos = (TextView) findViewById(R.id.tvPuntos);
        tvPuntuacio = (TextView) findViewById(R.id.tvPuntuacio);
    }


    private void createSoundPool() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            createNewSoundPool();
        } else {
            createOldSoundPool();
        }
    }
    @SuppressWarnings("deprecation")
    private void createOldSoundPool() {
        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void createNewSoundPool() {
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();
    }
    public void reprodueixSonsMaquina() {
        tiradesMaquina.add(azar());
        // activarem el torn del jugador quan acabi de sonar la màquina.
        // Per fer això creem un Handler amb delay
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // es a dir, li toca al jugador quan hagi acabat de reproduir la màquina
                tornJugador = true;
                // buidem la llista per a que comenci a llençar el jugador
                tiradesJugador.clear();
            }
        }, tiradesMaquina.size() * duracion * 2);
        // fem que llenci la màquina
        for (int i = 0; i < tiradesMaquina.size(); i++) {
            tornJugador = false;
            final int valorTirada = tiradesMaquina.get(i).intValue();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    switch(valorTirada) {
                        case 0:
                            azul();
                            break;
                        case 1:
                            rojo();
                            break;
                        case 2:
                            verde();
                            break;
                        case 3:
                            amarillo();
                            break;
                    }
                }
            }, duracion * i * 2);
        }
    }
    public void azul() {
        ibAzul.setImageResource(R.drawable.blueimglight);
        if (jugando)
            //sp.play(soundID, leftVolume, rightVolume, priority, loop, rate);
            soundPool.play(sonAzul, 1, 1, 0, 0, 1);
        try {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ibAzul.setImageResource(R.drawable.blueimg);
                }
            }, duracion);
        } catch (Exception e) {
            Log.i("Error azul()",e.toString());
        }
    }
    private void amarillo() {
        ibAmarillo.setImageResource(R.drawable.yellowimglight);
        if (jugando)
            //sp.play(soundID, leftVolume, rightVolume, priority, loop, rate);
            soundPool.play(sonAmarillo, 1, 1, 0, 0, 1);
        try {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ibAmarillo.setImageResource(R.drawable.yellowimg);
                }
            }, duracion);
        } catch (Exception e) {
            Log.i("Error azul()",e.toString());
        }
    }

    private void verde() {
        ibVerde.setImageResource(R.drawable.greenimglight);
        if (jugando)
            //sp.play(soundID, leftVolume, rightVolume, priority, loop, rate);
            soundPool.play(sonVerde, 1, 1, 0, 0, 1);
        try {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ibVerde.setImageResource(R.drawable.greenimg);
                }
            }, duracion);
        } catch (Exception e) {
            Log.i("Error azul()",e.toString());
        }
    }

    private void rojo() {
        ibRojo.setImageResource(R.drawable.redimglight);
        if (jugando)
            //sp.play(soundID, leftVolume, rightVolume, priority, loop, rate);
            soundPool.play(sonRojo, 1, 1, 0, 0, 1);
        try {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ibRojo.setImageResource(R.drawable.redimg);
                }
            }, duracion);
        } catch (Exception e) {
            Log.i("Error azul()",e.toString());
        }
    }
    public int azar() {
        Random rnd = new Random();
        int random = (int) rnd.nextInt(4);
        return random;
    }
    public void comprovatirada() {
        Handler handler = new Handler();
        // si falla la tirada
        if (tiradesJugador.get(tirada).intValue() != tiradesMaquina.get(tirada).intValue()) {
            jugando = false; // para controlar el boton de play
            error();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ibPlay.setVisibility(View.VISIBLE);
                    tvPuntuacio.setVisibility(View.INVISIBLE);
                    tvPuntos.setVisibility(View.INVISIBLE);
                }
            }, 1000);
            tiradesMaquina.clear();
            tiradesJugador.clear();
            tirada = 0;
            // que vuelva a tirar la maquina 1 segundo despues si he completado aciertos
        } else if (tiradesJugador.size() == tiradesMaquina.size() && jugando == true) {
            tornJugador = false; // para controlar los onclick
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    tvPuntos.setText("" + (tiradesMaquina.size() + 1));
                    reprodueixSonsMaquina();
                    tirada = 0;
                }
            }, 1000);
        } else {
            tirada++;
        }
    }

    private void error() {

        soundPool.play(sonError, 1, 1, 0, 0, 1);
        init();
    }

    private void init() {
        tirada =0;
        tornJugador=false;
        jugando=true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, Menu.FIRST,   Menu.NONE, "Joc");
        menu.add(0, Menu.FIRST+1, Menu.NONE, "Configuracio");
        menu.add(0, Menu.FIRST+2, Menu.NONE, "Instruccions");
        menu.add(0, Menu.FIRST+2, Menu.NONE, "Sortir");

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){

            case 1:
                initGame();
                break;
            case 2:
                initConfig();
                break;
            case 3:
                initInstructions();
                break;
            case 4:
                exitApp();
                break;



        }
        return true;

    }

    private void initInstructions() {
        Intent i = new Intent(this, Instruccions.class);
        i.putExtras(b);
        startActivity(i);
    }

    private void initConfig() {
        Intent i = new Intent(this, Instruccions.class);
        i.putExtras(b);
        startActivity(i);
    }

    private void initGame() {
        Intent i = new Intent(this, Instruccions.class);
        i.putExtras(b);
        startActivity(i);
    }

    private void exitApp() {
        Intent i = new Intent(this, Instruccions.class);
        i.putExtras(b);
        startActivity(i);
    }
}
