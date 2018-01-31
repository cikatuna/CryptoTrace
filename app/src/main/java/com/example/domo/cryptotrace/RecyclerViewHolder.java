package com.example.domo.cryptotrace;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.view.View;

/**
 * Created by Domo on 31.1.2018..
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView name, hobby;

    public RecyclerViewHolder(View itemView) {
        super(itemView);

        itemView.setOnClickListener(this);

        name = itemView.findViewById(R.id.name);
        hobby = itemView.findViewById(R.id.rank);
    }

    @Override
    public void onClick(View view) {
        //Toast.makeText(, "", Toast.LENGTH_SHORT).show();
    }
}