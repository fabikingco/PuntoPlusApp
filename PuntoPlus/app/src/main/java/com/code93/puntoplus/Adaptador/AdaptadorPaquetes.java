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
import com.code93.puntoplus.model.Transacciones.PaquetesDatos;
import com.code93.puntoplus.model.Transacciones.Transaccion;

import java.util.ArrayList;

public class AdaptadorPaquetes extends RecyclerView.Adapter<AdaptadorPaquetes.ViewHolderPaquetes>{

    ArrayList<PaquetesDatos> arrayList;
    private String operador;
    Context context;

    public AdaptadorPaquetes(ArrayList<PaquetesDatos> arrayList, String operador, Context context) {
        this.arrayList = arrayList;
        this.operador = operador;
        this.context = context;
    }

    private OnItemClickListenerPaquetes listener;

    /**
     * Interfaz de comunicación
     */
    public interface OnItemClickListenerPaquetes {
        void onItemClick(RecyclerView.ViewHolder item, int position, int id);
    }

    public void setOnItemClickListener(OnItemClickListenerPaquetes listener) {
        this.listener = listener;
    }

    public OnItemClickListenerPaquetes getOnItemClickListener() {
        return listener;
    }

    @NonNull
    @Override
    public AdaptadorPaquetes.ViewHolderPaquetes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_paquetes, parent, false);
        return new AdaptadorPaquetes.ViewHolderPaquetes(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPaquetes holder, int position) {
        PaquetesDatos paquetesDatos = arrayList.get(position);

        if (operador.equals(context.getResources().getString(R.string.claro))){
            holder.image.setImageDrawable(context.getDrawable(R.drawable.claro_logo));
        }
        if (operador.equals(context.getResources().getString(R.string.movistar))){
            holder.image.setImageDrawable(context.getDrawable(R.drawable.movistar_logo));
        }
        if (operador.equals(context.getResources().getString(R.string.tuenti))){
            holder.image.setImageDrawable(context.getDrawable(R.drawable.tuenti));
        }
        if (operador.equals(context.getResources().getString(R.string.cnt))){
            holder.image.setImageDrawable(context.getDrawable(R.drawable.cnt_logo));
        }

        holder.tvTitulo.setText(paquetesDatos.getTitulo());
        holder.tvMensaje.setText(paquetesDatos.getDescripcion());
        holder.tvMonto.setText("$ " + paquetesDatos.getMonto());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolderPaquetes extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvTitulo;
        TextView tvMensaje;
        TextView tvMonto;
        ImageView image;
        AdaptadorPaquetes adapter;

        public ViewHolderPaquetes(@NonNull View itemView, AdaptadorPaquetes adapter) {
            super(itemView);
            this.adapter = adapter;
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvMensaje = itemView.findViewById(R.id.tvMensaje);
            tvMonto = itemView.findViewById(R.id.tvMonto);
            image = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            final OnItemClickListenerPaquetes listener = adapter.getOnItemClickListener();
            int id = v.getId();
            if (listener != null) {
                listener.onItemClick(this, getAdapterPosition(), id);
            }
        }
    }
}
