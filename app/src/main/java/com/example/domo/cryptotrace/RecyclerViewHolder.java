package com.example.domo.cryptotrace;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Domo on 31.1.2018..
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView name, symbol, rank, price, percentChange1h, percentChange24h,percentChange7d;

    public RecyclerViewHolder(View itemView) {
        super(itemView);

        itemView.setOnClickListener(this);

        name = itemView.findViewById(R.id.name);
        symbol= itemView.findViewById(R.id.symbol);
        rank = itemView.findViewById(R.id.rank);
        price=itemView.findViewById(R.id.price);
        percentChange1h=itemView.findViewById(R.id.percent_change_1h);
        percentChange24h=itemView.findViewById(R.id.percent_change_24h);
        percentChange7d=itemView.findViewById(R.id.percent_change_7d);
    }

    @Override
    public void onClick(View view) {
       // Toast.makeText(, "edddooo", Toast.LENGTH_SHORT).show();
        //Toast.makeText(, "This is neki tekst poruke.", Toast.LENGTH_SHORT).show();
    }
}