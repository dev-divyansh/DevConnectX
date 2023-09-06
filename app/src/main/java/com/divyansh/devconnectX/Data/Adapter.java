package com.divyansh.devconnectX.Data;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.divyansh.devconnectX.R;
import com.divyansh.devconnectX.UI.Modal;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder> {
    Context context;
    List<Modal> data;

    public Adapter(Context context, List<Modal> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.profilecard , parent ,false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.name.setText(data.get(position).getName());
        holder.bio.setText(data.get(position).getBio());
        holder.number.setText(data.get(position).getNumber());
        holder.mail.setText(data.get(position).getMail());
        holder.mail.setOnClickListener(
                v -> {
                    Intent intent = new  Intent(Intent.ACTION_VIEW , Uri.parse("mailto:${data.get(position).getMail()}"));
                    context.startActivity(intent);
                }
        );
        holder.number.setOnClickListener(
                v->{
                    Intent intent = new Intent(Intent.ACTION_VIEW , Uri.parse("tel:${data.get(position).getNumber()}"));
                    context.startActivity(intent);
                }
        );
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView bio;
        TextView number;
        TextView mail;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.card_name);
            this.bio = itemView.findViewById(R.id.card_bio);
            this.number = itemView.findViewById(R.id.card_number);
            this.mail = itemView.findViewById(R.id.card_mail);
        }
    }
}
