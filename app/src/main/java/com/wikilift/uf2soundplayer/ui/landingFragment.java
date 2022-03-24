package com.wikilift.uf2soundplayer.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wikilift.uf2soundplayer.MainActivity;
import com.wikilift.uf2soundplayer.R;
import com.wikilift.uf2soundplayer.core.Helpers;
import com.wikilift.uf2soundplayer.data.model.Song;
import com.wikilift.uf2soundplayer.databinding.FragmentLandingBinding;
import com.wikilift.uf2soundplayer.domain.SongRepoImpl;
import com.wikilift.uf2soundplayer.presentation.SongViewModel;
import com.wikilift.uf2soundplayer.presentation.SongViewModelFactory;
import com.wikilift.uf2soundplayer.ui.adapter.SongItemAdapter;

import java.util.ArrayList;


public class landingFragment extends Fragment {

    private FragmentLandingBinding binding;

    private ArrayList<Song> songList=new ArrayList<>();
    private SongItemAdapter adapter;
    private SongViewModel viewModel;

    public landingFragment() {
       super(R.layout.fragment_landing);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding=FragmentLandingBinding.bind(view);
       viewModel= new ViewModelProvider(
                requireActivity(),new SongViewModelFactory(
                requireActivity().getApplication(),
                new SongRepoImpl(
                        MainActivity.masterDB)
        )
        ).get(SongViewModel.class);
        if(MainActivity.init){
            Navigation.findNavController(view).navigate(R.id.action_landingFragment2_to_splash);
        }

        showEmptyList();


    }

    private void showEmptyList() {
        if(viewModel.getAll().size()>0){
            songList= viewModel.getAll();
            setAdapter(songList);
            binding.rvSongs.setVisibility(View.VISIBLE);

        }else{
            binding.play.setOnClickListener(e->{
                Navigation.findNavController(requireView()).navigate(R.id.action_landingFragment2_to_addFragment);
                    });
            binding.landingView.setVisibility(View.VISIBLE);
        }
    }

    private void setAdapter(ArrayList<Song> songList) {
        adapter=new SongItemAdapter(requireContext(),songList);
        binding.rvSongs.setAdapter(adapter);
    }
}