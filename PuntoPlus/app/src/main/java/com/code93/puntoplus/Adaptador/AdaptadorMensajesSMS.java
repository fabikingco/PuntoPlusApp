package com.code93.puntoplus.Adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.code93.puntoplus.R;
import com.code93.puntoplus.Tools;
import com.code93.puntoplus.model.SMS_RECV;

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
        SMS_RECV sms_recv = arrayList.get(position);
        holder.tvMensaje.setText(arrayList.get(position).getRecv_msg());
        holder.tvHoraFecha.setText("Fecha: " + Tools.getFormatDate(sms_recv.getRecv_fecha()) + " Hora: " + Tools.getFormatTime(sms_recv.getRecv_hora()));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolderSMS extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvMensaje;
        TextView tvHoraFecha;

        public ViewHolderSMS(@NonNull View itemView) {
            super(itemView);
            tvMensaje = itemView.findViewById(R.id.tvMensaje);
            tvHoraFecha = itemView.findViewById(R.id.tvHoraFecha);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
