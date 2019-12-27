package com.example.puntoplus.Adaptador;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorMensajesSMS extends RecyclerView.Adapter<AdaptadorMensajesSMS.ViewHolderSMS> {



    @NonNull
    @Override
    public ViewHolderSMS onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSMS holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolderSMS extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ViewHolderSMS(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
