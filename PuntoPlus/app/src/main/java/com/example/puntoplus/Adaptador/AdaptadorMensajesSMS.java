package com.example.puntoplus.Adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.puntoplus.R;
import com.example.puntoplus.model.SMS_RECV;

import java.util.ArrayList;

public class AdaptadorMensajesSMS extends RecyclerView.Adapter<AdaptadorMensajesSMS.ViewHolderSMS> {

    ArrayList<SMS_RECV> arrayList;

    public AdaptadorMensajesSMS(ArrayList<SMS_RECV> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolderSMS onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sms_, parent, false);
        return new ViewHolderSMS(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSMS holder, int position) {
        holder.tvMensaje.setText(arrayList.get(position).getRecv_msg());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolderSMS extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvMensaje;

        public ViewHolderSMS(@NonNull View itemView) {
            super(itemView);
            tvMensaje = itemView.findViewById(R.id.tvMensaje);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
