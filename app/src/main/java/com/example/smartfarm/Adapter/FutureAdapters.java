package com.example.smartfarm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartfarm.Domain.FutureDemain;
import com.example.smartfarm.Domain.Hourly;
import com.example.smartfarm.R;

import java.util.ArrayList;

public class FutureAdapters extends RecyclerView.Adapter<FutureAdapters.viewHolder> {
    ArrayList<FutureDemain>items;
    Context context;

    public FutureAdapters(ArrayList<FutureDemain> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public FutureAdapters.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_future,parent,false);
        context=parent.getContext();
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.dayTxt.setText(items.get(position).getDay());
        holder.statusTxt.setText(items.get(position).getStatus());
        holder.highTxt.setText(items.get(position).getHighTemp()+"°");
        holder.lowtxt.setText(items.get(position).getLowTemp()+"°");




        int drawbleResourceId=holder.itemView.getResources().
                getIdentifier(items.get(position).getPicPath(),"drawble",holder.itemView.getContext().getPackageName());
        Glide.with(context)
                .load(drawbleResourceId)
                .into(holder.pic);




    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView dayTxt,statusTxt,lowtxt,highTxt;
        ImageView pic;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            dayTxt=itemView.findViewById(R.id.dayText);
            statusTxt=itemView.findViewById(R.id.statusTxt);
            lowtxt=itemView.findViewById(R.id.low);
            highTxt=itemView.findViewById(R.id.high);
            pic=itemView.findViewById(R.id.pic2);
        }


    }
}
