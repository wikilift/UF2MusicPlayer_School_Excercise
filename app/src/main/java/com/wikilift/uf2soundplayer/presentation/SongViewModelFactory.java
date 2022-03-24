package com.wikilift.uf2soundplayer.presentation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.wikilift.uf2soundplayer.domain.SongRepo;

public class SongViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    private SongRepo taskRepo;

    public SongViewModelFactory(Application mApplication, SongRepo taskRepo) {
        this.mApplication = mApplication;
        this.taskRepo = taskRepo;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> aClass) {
        return (T) (new SongViewModel(mApplication,taskRepo));
    }
}
