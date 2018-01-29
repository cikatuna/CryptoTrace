package com.example.domo.cryptotrace;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by csa on 01-May-17.
 */

public class Recycleradapter extends RecyclerView.Adapter<Recycleradapter.MyHolder>{

    List<Product> list;
    //ImageLoader imageLoader; /**--!> ,ImageLoader imageLoader*/
    public Recycleradapter(List<Product> list) {
        this.list = list;
     //   this.imageLoader=imageLoader;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list,parent,false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Product product = list.get(position);
        holder.name.setText("Name:"+product.getName());
        holder.rank.setText("Rank:"+product.getRank());
        holder.symbol.setText("Symbol"+product.getSymbol());
       // String image1 = product.getImageurl();
       // imageLoader.displayImage(image1, holder.image);
        holder.price.setText("USD Price: "+product.getPriceUsd());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView name,rank,price,symbol;
        //ImageView image;

        public MyHolder(View itemView) {
            super(itemView);
            //image= (ImageView) itemView.findViewById(R.id.productimage);
            name = (TextView) itemView.findViewById(R.id.name);
            rank = (TextView) itemView.findViewById(R.id.color);
            price= (TextView) itemView.findViewById(R.id.price);
            symbol=(TextView) itemView.findViewById(R.id.symbol);


        }


    }

}