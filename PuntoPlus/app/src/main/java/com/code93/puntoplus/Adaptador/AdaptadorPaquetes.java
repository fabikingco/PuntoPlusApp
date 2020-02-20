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

    @NonNull
    @Override
    public AdaptadorPaquetes.ViewHolderPaquetes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_paquetes, parent, false);
        return new AdaptadorPaquetes.ViewHolderPaquetes(view);
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

        public ViewHolderPaquetes(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvMensaje = itemView.findViewById(R.id.tvMensaje);
            tvMonto = itemView.findViewById(R.id.tvMonto);
            image = itemView.findViewById(R.id.image);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
