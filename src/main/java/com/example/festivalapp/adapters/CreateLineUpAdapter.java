package com.example.festivalapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.festivalapp.R;
import com.example.festivalapp.models.CreateModel;

import java.util.ArrayList;
import java.util.List;

public class CreateLineUpAdapter extends RecyclerView.Adapter<CreateLineUpAdapter.ViewHolder> {
    List<CreateModel> mCreateList= new ArrayList<>();

    public CreateLineUpAdapter(List<CreateModel> mCreateList) {
        this.mCreateList = mCreateList;
    }

    @NonNull
    @Override
    public CreateLineUpAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.content_createlineup, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CreateLineUpAdapter.ViewHolder viewHolder, int i) {
        final CreateModel createModel=mCreateList.get(i);
        viewHolder.artistOne.setText(createModel.getArtistOne());
        viewHolder.artistTwo.setText(createModel.getArtistTwo());
        viewHolder.artistThree.setText(createModel.getArtistThree());
        viewHolder.artistFour.setText(createModel.getArtistFour());

    }

    @Override
    public int getItemCount() {
        return mCreateList.size();
    }

    public void swapList (List<CreateModel> newList) {
        mCreateList = newList;
        if (newList != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView artistOne;
        TextView artistTwo;
        TextView artistThree;
        TextView artistFour;
        CardView cardViewCreate;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            artistOne=itemView.findViewById(R.id.tvArtistOne);
            artistTwo=itemView.findViewById(R.id.tvArtistTwo);
            artistThree=itemView.findViewById(R.id.tvArtistThree);
            artistFour=itemView.findViewById(R.id.tvArtistFour);
            cardViewCreate= itemView.findViewById(R.id.CreateLineupCard);


        }
    }
}
