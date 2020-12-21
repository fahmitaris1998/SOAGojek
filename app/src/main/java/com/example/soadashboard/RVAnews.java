package com.example.soadashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class RVAnews extends RecyclerView.Adapter<RVAnews.ViewHolder> {

    List<news_data> listdata = Collections.emptyList();
    Context mcontex;


    public RVAnews(List<news_data> listdata, Context mcontex) {
        this.listdata = listdata;
        this.mcontex = mcontex;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView1,textView2;
        CardView cardView;

        public ViewHolder(View view){
            super(view);
            imageView = (ImageView) view.findViewById(R.id.image_news);
            textView1 = (TextView) view.findViewById(R.id.judul);
            textView2 = (TextView) view.findViewById(R.id.konten);
            cardView = (CardView) view.findViewById(R.id.card_news);
        }

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        news_data data = listdata.get(position);
        holder.imageView.setImageResource(data.getImage());
        holder.textView1.setText(data.getJudul());
        holder.textView2.setText(data.getKonten());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
