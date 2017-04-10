package com.example.jose.simon;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by Jose on 06/04/2017.
 */

public class Instruccions extends AppCompatActivity {
    VideoView videoViewer;
    VideoView videoSdcard;
    MediaController videoViewerController, videoViewerController2;
    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instruccions);
        b=getIntent().getExtras();
        prepareSurface();
    }

    public void prepareSurface(){
        videoViewer = (VideoView) findViewById(R.id.videoView1);
        videoViewerController = new MediaController(this,true);
        videoViewerController.setEnabled(false);
        videoViewer.setMediaController(videoViewerController);
        videoViewer.setVideoURI(Uri.parse(
                "android.resource://com.example.jose.simon/raw/tutorialsimon"
        ));



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
                break;
            case 4:
                exitApp();
                break;



        }
        return true;

    }

    private void initGame() {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtras(b);
        startActivity(i);
    }


    private void initConfig() {
        Intent i = new Intent(this, Configuracion.class);
        i.putExtras(b);
        startActivity(i);
    }


    private void exitApp() {
        System.exit(0);
    }
}
