package com.wikilift.uf2soundplayer.presentation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.wikilift.uf2soundplayer.data.model.Song;
import com.wikilift.uf2soundplayer.domain.SongRepo;

import java.util.ArrayList;

public class SongViewModel extends  ViewModel {
    private final SongRepo songRepo;


    public SongViewModel(Application context, SongRepo repo){
        songRepo = repo;
    }


    public ArrayList<Song> getAll(){return songRepo.getAll();}

    public void insert(Song t){
        songRepo.insert(t);
    }
    public void deleteAll(){
        songRepo.deleteAll();
    }
    public void deleteSingle(Song t){
        songRepo.deleteSingle(t);
    }
}

