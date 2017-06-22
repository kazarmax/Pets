package com.example.android.pets.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.pets.data.PetContract.PetEntry;

public class PetDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "shelter.db";
    private static final int DATABASE_VERSION = 1;

    private static final String LOG_TAG = PetDbHelper.class.getSimpleName();

    public PetDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String COMMA_SEP = ",";
        String SQL_CREATE_PETS_TABLE =
                "CREATE TABLE " + PetEntry.TABLE_NAME + "(" +
                        PetEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                        PetEntry.COLUMN_PET_NAME + " TEXT NOT NULL" + COMMA_SEP +
                        PetEntry.COLUMN_PET_BREED + " TEXT" + COMMA_SEP +
                        PetEntry.COLUMN_PET_GENDER + " INTEGER NOT NULL" + COMMA_SEP +
                        PetEntry.COLUMN_PET_WEIGHT + " INTEGER NOT NULL DEFAULT 0" + ");";
        db.execSQL(SQL_CREATE_PETS_TABLE);
        Log.v(LOG_TAG, "SQL script to create pets table = \n" + SQL_CREATE_PETS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        String SQL_DROP_PETS_TABLE =
                "DROP TABLE IF EXISTS " + PetEntry.TABLE_NAME;
        db.execSQL(SQL_DROP_PETS_TABLE);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}