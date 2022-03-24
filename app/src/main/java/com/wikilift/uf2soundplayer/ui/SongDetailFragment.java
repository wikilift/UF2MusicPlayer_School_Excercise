package com.wikilift.uf2soundplayer.ui;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wikilift.uf2soundplayer.MainActivity;
import com.wikilift.uf2soundplayer.R;
import com.wikilift.uf2soundplayer.core.Helpers;
import com.wikilift.uf2soundplayer.data.model.Song;
import com.wikilift.uf2soundplayer.databinding.FragmentSongDetailBinding;
import com.wikilift.uf2soundplayer.domain.SongRepoImpl;
import com.wikilift.uf2soundplayer.presentation.SongViewModel;
import com.wikilift.uf2soundplayer.presentation.SongViewModelFactory;

import java.io.IOException;


public class SongDetailFragment extends Fragment {


    private FragmentSongDetailBinding binding;
    private SongViewModel viewModel;
    private Song travelSong;
    private MediaPlayer mediaPlayer;
    private boolean playIsPulsed;
    private int lengthSong;


    public SongDetailFragment() {
        super(R.layout.fragment_song_detail);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        travelSong = SongDetailFragmentArgs.fromBundle(getArguments()).getTravelSong();
        mediaPlayer = new MediaPlayer();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        binding = FragmentSongDetailBinding.bind(view);
        viewModel = new ViewModelProvider(
                requireActivity(), new SongViewModelFactory(
                requireActivity().getApplication(),
                new SongRepoImpl(
                        MainActivity.masterDB)
        )
        ).get(SongViewModel.class);
        binding.title.setText(travelSong.getTitle().toString());
        binding.url.setText(travelSong.getUrl().toString());
        binding.coolButton.setOnClickListener(e -> {
            Navigation.findNavController(requireView()).navigate(R.id.action_songDetailFragment_to_landingFragment2);
        });
        binding.pause.setEnabled(false);
        setListeners();


    }

    private void setListeners() {
        binding.delete.setOnClickListener(e -> {
            viewModel.deleteSingle(travelSong);
            Toast.makeText(requireContext(), requireContext().getResources().getString(R.string.deleted), Toast.LENGTH_SHORT).show();
            Navigation.findNavController(requireView()).navigate(R.id.action_songDetailFragment_to_landingFragment2);
        });
        binding.play.setOnClickListener(e -> {
            try {
                binding.progressBar.setVisibility(View.VISIBLE);
                binding.play.setEnabled(false);

                Uri uri = Uri.parse(travelSong.getUrl());


                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setDataSource(requireContext(), uri);
                mediaPlayer.prepareAsync();


            } catch (IOException j) {
                Toast.makeText(requireContext(), requireContext().getResources().getString(R.string.ups), Toast.LENGTH_SHORT).show();
            }
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                @Override
                public void onPrepared(MediaPlayer player) {
                    binding.progressBar.setVisibility(View.GONE);
                    binding.pause.setEnabled(true);
                    binding.play.setEnabled(false);
                    player.start();
                }

            });


        });

        binding.pause.setOnClickListener(e->{
            if(mediaPlayer.isPlaying()){
                mediaPlayer.pause();

                lengthSong=mediaPlayer.getCurrentPosition();

            }else if(!mediaPlayer.isPlaying()){
                mediaPlayer.seekTo(lengthSong);
                mediaPlayer.start();
                binding.pause.setEnabled(true);
            }

        });
        binding.stop.setOnClickListener(e->{
            if(mediaPlayer.isPlaying()){
                mediaPlayer.stop();
                binding.pause.setEnabled(false);

            }
        });


    }
}