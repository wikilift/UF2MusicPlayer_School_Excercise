package com.wikilift.uf2soundplayer.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wikilift.uf2soundplayer.MainActivity;
import com.wikilift.uf2soundplayer.R;
import com.wikilift.uf2soundplayer.data.model.Song;
import com.wikilift.uf2soundplayer.databinding.FragmentAddBinding;
import com.wikilift.uf2soundplayer.domain.SongRepoImpl;
import com.wikilift.uf2soundplayer.presentation.SongViewModel;
import com.wikilift.uf2soundplayer.presentation.SongViewModelFactory;

import java.util.Objects;


public class addFragment extends Fragment {

    private FragmentAddBinding binding;
    private SongViewModel viewModel;
    public addFragment(){
        super(R.layout.fragment_add);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding=FragmentAddBinding.bind(view);
        viewModel= new ViewModelProvider(
                requireActivity(),new SongViewModelFactory(
                requireActivity().getApplication(),
                new SongRepoImpl(
                        MainActivity.masterDB)
        )
        ).get(SongViewModel.class);
        binding.btnAdd.setOnClickListener(e->{
            if(!Objects.requireNonNull(binding.txtTitle.getText()).toString().isEmpty()&& !Objects.requireNonNull(binding.txtUrl.getText()).toString().isEmpty()){
                viewModel.insert(new Song(binding.txtTitle.getText().toString(),binding.txtUrl.getText().toString()));
                Toast.makeText(requireContext(),this.getResources().getString(R.string.inserted),Toast.LENGTH_SHORT).show();
                Navigation.findNavController(requireView()).navigate(R.id.action_addFragment_to_landingFragment2);
            }else{
                if(binding.txtTitle.getText().toString().isEmpty()){
                    binding.txtTitle.requestFocus();
                    binding.txtTitle.setError(this.getResources().getString(R.string.titleError));
                }else{
                    binding.txtUrl.requestFocus();
                    binding.txtUrl.setError(this.getResources().getString(R.string.urlError));
                }
            }
        });
    }
}
