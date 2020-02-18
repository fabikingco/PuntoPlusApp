package com.code93.puntoplus.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.code93.puntoplus.R;
import com.code93.puntoplus.Tools;
import com.code93.puntoplus.model.SMS_RECV;
import com.code93.puntoplus.model.Transacciones.Transaccion;

import java.util.ArrayList;

public class AdaptadorTransacciones extends RecyclerView.Adapter<AdaptadorTransacciones.ViewHolderSMS> {

    ArrayList<Transaccion> arrayList;
    Context context;

    public AdaptadorTransacciones(ArrayList<Transaccion> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderSMS onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sms_, parent, false);
        return new ViewHolderSMS(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSMS holder, int position) {
        Transaccion transaccion = arrayList.get(position);
        holder.tvContrapartida.setText(transaccion.getTipo());
        holder.tvMensaje.setText(transaccion.getName1() + " " + transaccion.getContrapartida1());
        holder.tvHoraFecha.setText("Fecha: " + transaccion.getFecha() + " Hora: " + transaccion.getHora());

        if (transaccion.getOperador().equals(context.getResources().getString(R.string.claro))){
            holder.image.setImageDrawable(context.getDrawable(R.drawable.claro_logo));
        }
        if (transaccion.getOperador().equals(context.getResources().getString(R.string.movistar))){
            holder.image.setImageDrawable(context.getDrawable(R.drawable.movistar_logo));
        }
        if (transaccion.getOperador().equals(context.getResources().getString(R.string.tuenti))){
            holder.image.setImageDrawable(context.getDrawable(R.drawable.tuenti));
        }
        if (transaccion.getOperador().equals(context.getResources().getString(R.string.cnt))){
            holder.image.setImageDrawable(context.getDrawable(R.drawable.cnt_logo));
        }
        if (transaccion.getOperador().equals(context.getResources().getString(R.string.directv))){
            holder.image.setImageDrawable(context.getDrawable(R.drawable.directv));
        }
        if (transaccion.getOperador().equals(context.getResources().getString(R.string.tvcable))){
            holder.image.setImageDrawable(context.getDrawable(R.drawable.tvcable));
        }
        if (transaccion.getTipo().equals(context.getResources().getString(R.string.recargas_simert))){
            holder.image.setImageDrawable(context.getDrawable(R.drawable.simmert));
        }
        
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolderSMS extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvMensaje;
        TextView tvHoraFecha;
        TextView tvContrapartida;
        ImageView image;

        public ViewHolderSMS(@NonNull View itemView) {
            super(itemView);
            tvContrapartida = itemView.findViewById(R.id.tvContrapartida);
            tvMensaje = itemView.findViewById(R.id.tvMensaje);
            tvHoraFecha = itemView.findViewById(R.id.tvHoraFecha);
            image = itemView.findViewById(R.id.image);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
