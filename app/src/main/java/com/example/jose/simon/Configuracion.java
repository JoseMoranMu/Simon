package com.example.jose.simon;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

/**
 * Created by Jose on 10/04/2017.
 */

public class Configuracion extends AppCompatActivity {
    Bundle b;
    EditText userName;
    Button guardar;
    Spinner fondoSpinner;
    String fondo="black",user="jugador",musica="";
    RadioGroup music;
    String[] dadesSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuracion);
        b= this.getIntent().getExtras();
        initComponents();
        initSpinner();
        listenetRadio();

    }

    private void listenetRadio() {
        music.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch(checkedId){
                    case R.id.rocky:
                        musica="rocky";
                        break;
                    case R.id.tetris:
                        musica="tetris";
                        break;
                    case R.id.swars:
                        musica="swars";
                        break;
                }
            }

        });
    }

    private void initSpinner() {
        dadesSpinner =  new String[]
                {"Negro","Amarillo","Rojo","Azul","Verde"};

        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item,
                        dadesSpinner);
        fondoSpinner.setAdapter(adaptador);
        fondoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(dadesSpinner[position]){
                    case "Negro":
                        fondo= "black";
                        break;
                    case "Amarillo":
                        fondo= "yellow";
                        break;
                    case "Rojo":
                        fondo= "red";
                        break;
                    case "Azul":
                        fondo= "blue";
                        break;
                    case "Verde":
                        fondo= "green";
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initComponents() {
        userName= (EditText) findViewById(R.id.editText);
        music= (RadioGroup) findViewById(R.id.grupo);

        fondoSpinner = (Spinner) findViewById(R.id.spinner);
        guardar = (Button) findViewById(R.id.guardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initGame();
            }
        });

    }
    private void prepareBundle(){
        user =  userName.getText().toString();
        b.remove("user");
        b.remove("music");
        b.remove("fondo");
        b.putString("user",user);
        b.putString("music",musica);
        b.putString("fondo",fondo);

    }
    private void initGame() {
        prepareBundle();
        Intent i = new Intent(this, MainActivity.class);
        i.putExtras(b);
        startActivity(i);
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
        prepareBundle();
        Intent i = new Intent(this, Instruccions.class);
        i.putExtras(b);
        startActivity(i);
    }


    private void exitApp() {
        System.exit(0);
    }
}
