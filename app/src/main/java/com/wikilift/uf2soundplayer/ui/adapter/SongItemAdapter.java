package com.wikilift.uf2soundplayer.ui.adapter;

import android.content.Context;
import android.os.Build;
import android.util.LayoutDirection;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.wikilift.uf2soundplayer.R;
import com.wikilift.uf2soundplayer.core.Helpers;
import com.wikilift.uf2soundplayer.data.model.Song;
import com.wikilift.uf2soundplayer.databinding.SongItemRowBinding;
import com.wikilift.uf2soundplayer.ui.landingFragmentDirections;

import java.util.ArrayList;

public class SongItemAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private final Context context;
    private  final ArrayList<Song> list;



    public SongItemAdapter(Context context, ArrayList<Song> list) {
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.song_item_row, parent, false), viewType);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).bind(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {

    }


    public  class ViewHolder extends RecyclerView.ViewHolder  {


        private final SongItemRowBinding binding;

        public ViewHolder(final View itemView, int position) {
            super(itemView);

            binding = SongItemRowBinding.bind(itemView);

        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        void bind(int position) {
            binding.songItemRow.setOnClickListener(e->{
                Song travelDto=list.get(position);
                landingFragmentDirections.ActionLandingFragment2ToSongDetailFragment z= landingFragmentDirections.actionLandingFragment2ToSongDetailFragment(travelDto);
                Navigation.findNavController(itemView).navigate(z);

            });
            String id=Integer.toString(list.get(position).getId());
            binding.id.setText(id);
            binding.title.setText(list.get(position).getTitle());

           // binding.id.setText(list.get(position).getId());
           // binding.title.setText(list.get(position).getTitle());
        }


    }



}
