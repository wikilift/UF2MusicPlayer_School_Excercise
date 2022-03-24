package com.wikilift.uf2soundplayer.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wikilift.uf2soundplayer.MainActivity;
import com.wikilift.uf2soundplayer.R;
import com.wikilift.uf2soundplayer.databinding.FragmentSplashBinding;
import com.wikilift.uf2soundplayer.presentation.SongViewModel;

public class Splash extends Fragment {

    private FragmentSplashBinding binding;
    private SongViewModel viewModel;

    public Splash() {
        super(R.layout.fragment_splash);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding=FragmentSplashBinding.bind(view);
        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                MainActivity.init=false;
                Navigation.findNavController(view).navigate(R.id.action_splash_to_landingFragment2);
            }
        }, 2300);
    }
}