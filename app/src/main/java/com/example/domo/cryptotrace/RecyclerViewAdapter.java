package com.example.domo.cryptotrace;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import java.util.List;

/**
 * Created by Domo on 31.1.2018..
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private List<User> item;


    public RecyclerViewAdapter(List<User> list) {
        this.item = list;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_row, null);

        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(layoutView);

        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.name.setText("Ime: "+item.get(position).getName());
        holder.symbol.setText("Simbol: "+item.get(position).getSymbol());
        holder.rank.setText("Rank:"+item.get(position).getRank());
        holder.price.setText("Trenutna Cijena: "+item.get(position).getPrice()+"$");
        holder.percentChange1h.setText("Izmjena u jednom satu:"+""+item.get(position).getPercentChange1h()+"%");
        holder.percentChange24h.setText("Izmjena u 24h:"+""+item.get(position).getPercentChange24h()+"%");
        holder.percentChange7d.setText("Izmjena u 7 dana:"+""+item.get(position).getPercentChange7d()+"%");
    }

    @Override
    public int getItemCount() {
        return item.size();
    }
}