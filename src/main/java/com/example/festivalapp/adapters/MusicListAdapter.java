package com.example.festivalapp.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.festivalapp.models.Song;

import java.io.File;
import java.util.List;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder>  {
private List<Song> musicList;
private OnItemClickListener itemClickListener;



    public MusicListAdapter(List<Song> musicList, OnItemClickListener itemClickListener) {
        this.musicList = musicList;
        this.itemClickListener = itemClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(android.R.layout.simple_list_item_1, viewGroup, false);
        return new MusicListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.bind(musicList.get(position),itemClickListener);


    }



    @Override
    public int getItemCount() {
        return musicList.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView musicName;


        public ViewHolder(View itemView) {
            super(itemView);

            musicName = itemView.findViewById(android.R.id.text1);
        }
        public void bind(final Song song, final OnItemClickListener listener){
            musicName.setText(song.getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(song);
                }
            });


        }
    }



    public interface  OnItemClickListener{
        void onItemClick(Song song);
    }

}
