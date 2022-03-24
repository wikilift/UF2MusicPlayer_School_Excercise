package com.wikilift.uf2soundplayer.domain;

import com.wikilift.uf2soundplayer.data.model.Song;

import java.util.ArrayList;

public interface SongRepo {
    ArrayList<Song>getAll();
    void insert(Song t);
    void deleteAll();
    void deleteSingle(Song t);
}
