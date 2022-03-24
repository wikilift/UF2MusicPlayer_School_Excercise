package com.wikilift.uf2soundplayer.data.bbdd;


import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;


import com.wikilift.uf2soundplayer.core.Helpers;
import com.wikilift.uf2soundplayer.data.model.Song;

import java.util.ArrayList;

public class DatabaseApp extends SQLiteOpenHelper {

    private final String nameOfDb = "songApp";
    private SQLiteDatabase db;

    public DatabaseApp(Context context) {
        super(context, "app_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE songApp (id INTEGER PRIMARY KEY ,title TEXT,url TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE  IF EXISTS songApp");

    }

    public void insert(Song t) {
        db = this.getReadableDatabase();
        db.execSQL("INSERT INTO songApp (title,url) VALUES('" + t.getTitle() + "','" + t.getUrl() + "')");
        db.close();
        Log.d(Helpers.TAG, "Inserted values");
    }

    public int getCount() {
        db = this.getReadableDatabase();
        int count = (int) DatabaseUtils.queryNumEntries(db, "songApp");
        db.close();
        return count;
    }

    public void deleteAll() {
        db = this.getWritableDatabase();
        db.execSQL("DELETE FROM songApp");
        db.close();
    }

    public void deleteSingle(Song t) {
        try {

            db = this.getWritableDatabase();
            db.execSQL("DELETE FROM songApp WHERE id="+t.getId());
        } catch (Exception e) {
            Log.d(Helpers.TAG, "Error during delete " + e);
        } finally {
            db.close();
        }


    }

    public ArrayList<Song> getAll() {
        ArrayList<Song> result = new ArrayList<>();
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + nameOfDb + " ORDER BY ID", null);


        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            try {
                int id = cursor.getInt((int) cursor.getColumnIndex("id"));

                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String url = cursor.getString(cursor.getColumnIndexOrThrow("url"));

                result.add(new Song(id,title, url));
                cursor.moveToNext();

            } catch (Exception e) {
                Log.d(Helpers.TAG, "Error while serializing objects " + e);
            } finally {
                db.close();
            }
        }

        return result;
    }


}

