package com.example.jose.simon;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jose on 07/04/2017.
 */

public class BBDD extends SQLiteOpenHelper {

    String create = "CREATE TABLE Puntuacion (Nombre TEXT , Puntuacion INTEGER)";
    String delete = "DROP TABLE IF EXISTS Puntuacion";
    public BBDD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(this.create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(this.delete);
        db.execSQL(this.create);
    }
}
