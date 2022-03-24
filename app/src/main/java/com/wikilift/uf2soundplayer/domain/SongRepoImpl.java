package com.wikilift.uf2soundplayer.domain;

import com.wikilift.uf2soundplayer.data.bbdd.DatabaseApp;
import com.wikilift.uf2soundplayer.data.model.Song;

import java.util.ArrayList;

public class SongRepoImpl implements SongRepo{

    public DatabaseApp masterDB;

    public SongRepoImpl(DatabaseApp masterDB) {
        this.masterDB = masterDB;
    }

    @Override
    public ArrayList<Song> getAll() { return masterDB.getAll(); }

    @Override
    public void insert(Song t) { masterDB.insert(t);}

    @Override
    public void deleteAll() { masterDB.deleteAll(); }

    @Override
    public void deleteSingle(Song t) {masterDB.deleteSingle(t); }
}
