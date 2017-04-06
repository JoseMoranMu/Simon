package com.example.jose.simon;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instruccions);
        prepareSurface();
        createButtons();
    }

    private void createButtons() {
        b1 = (Button) findViewById(R.id.button1);
        b2 =  (Button) findViewById(R.id.button2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop();
            }
        });
    }

    private void stop() {
        Intent i = new Intent(this,ServicioMusica.class);
        this.stopService(i);
    }

    private void play() {
        Intent i = new Intent(this,ServicioMusica.class);
        this.startService(i);
    }

    public void prepareSurface(){
        videoViewer = (VideoView) findViewById(R.id.videoView1);
        videoSdcard =  (VideoView) findViewById(R.id.videoView2);
        videoViewerController = new MediaController(this,true);
        videoViewerController.setEnabled(false);
        videoViewer.setMediaController(videoViewerController);
        videoViewer.setVideoURI(Uri.parse(
                "android.resource://com.example.jose.simon/raw/moon"
        ));

        videoViewerController2 = new MediaController(this,true);
        videoViewerController2.setEnabled(false);
        videoSdcard.setMediaController(videoViewerController2);
        videoSdcard.setVideoURI(Uri.parse(
                "android.resource://com.example.jose.simon/raw/moon2"
        ));


    }
}
