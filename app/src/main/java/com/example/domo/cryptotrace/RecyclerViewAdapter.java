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
        holder.name.setText(item.get(position).getName());
        holder.symbol.setText(item.get(position).getSymbol());
        holder.rank.setText(item.get(position).getRank());
        holder.price.setText(item.get(position).getPrice()+"$");
    }

    @Override
    public int getItemCount() {
        return item.size();
    }
}