package com.example.jose.simon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Jose on 10/04/2017.
 */

public class Configuracion extends AppCompatActivity {
    Bundle b;
   // EditText userName;
   // RadioButton
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jocsimon);
        b= this.getIntent().getExtras();

    }
}
