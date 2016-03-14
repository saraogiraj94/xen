package com.example.rajsaraogi.xenisis;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Raj Saraogi on 09-03-2016.
 */
public class MainCardAdapter extends RecyclerView.Adapter<MainCardAdapter.MainCardViewHolder> {
    ArrayList<MainCard> mainCards = new ArrayList<MainCard>();
    Context context;
    MainActivity mainActivity;
    AdapterView.OnItemClickListener mItemClickListener;
    public MainCardAdapter(ArrayList<MainCard> mainCards,MainActivity mainActivity) {
        this.mainCards=mainCards;
        this.mainActivity=mainActivity;
        this.context=mainActivity.getApplicationContext();
    }

    @Override
    public MainCardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_layout, viewGroup, false);
        MainCardViewHolder mainCardViewHolder = new MainCardViewHolder(view);
        return mainCardViewHolder;
    }

    @Override
    public void onBindViewHolder(MainCardViewHolder holder, int i) {
        MainCard mainCard = mainCards.get(i);
        holder.image.setImageResource(mainCard.getImage_id());
        holder.name.setText(mainCard.getName());
    }

    @Override
    public int getItemCount() {
        return mainCards.size();
    }

    public class MainCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        TextView name;
        public MainCardViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.mainImage);
            name = (TextView) itemView.findViewById(R.id.mainText);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = getPosition();
           // Toast.makeText(context, "Clicked " + pos, Toast.LENGTH_SHORT).show();
            mainActivity.call(pos);
        }

    }

}
