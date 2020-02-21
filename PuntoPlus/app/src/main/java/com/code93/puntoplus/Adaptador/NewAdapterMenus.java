package com.code93.puntoplus.Adaptador;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.code93.puntoplus.R;
import com.code93.puntoplus.model.menuItemsModelo;

import java.util.ArrayList;
import java.util.List;

public class NewAdapterMenus extends RecyclerView.Adapter<NewAdapterMenus.ViewHolder2> {

    public List<menuItemsModelo> menuItemsModeloList;
    Context context;

    public NewAdapterMenus(List<menuItemsModelo> menuItemsModeloList, Context context) {
        this.menuItemsModeloList = menuItemsModeloList;
        this.context = context;

    }

    private OnItemClickListenerMesas listener;

    /**
     * Interfaz de comunicación
     */
    public interface OnItemClickListenerMesas {
        void onItemClick(RecyclerView.ViewHolder item, int position, int id);
    }

    public void setOnItemClickListener(OnItemClickListenerMesas listener) {
        this.listener = listener;
    }

    public OnItemClickListenerMesas getOnItemClickListener() {
        return listener;
    }

    @Override
    public NewAdapterMenus.ViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        int idItemMenu;

        idItemMenu = R.layout.tarjeta_operadores;

        View view= LayoutInflater.from(parent.getContext()).inflate(idItemMenu, parent, false);

        return new ViewHolder2(view, menuItemsModeloList, this);
    }

    @Override
    public void onBindViewHolder(NewAdapterMenus.ViewHolder2 holder, int position) {
        holder.TextoItemMenu.setText(menuItemsModeloList.get(position).getTextoItem());
        holder.logoItem.setImageDrawable(context.getResources().getDrawable(menuItemsModeloList.get(position).getImgItemMenu()));
    }

    @Override
    public int getItemCount() {
        return menuItemsModeloList.size();
    }

    public static class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView TextoItemMenu;
        ImageView logoItem;
        List<menuItemsModelo> menuItems = new ArrayList<menuItemsModelo>();
        ArrayList<Drawable> drawables;
        NewAdapterMenus adapter;

        public ViewHolder2(View itemView,List<menuItemsModelo> menuItems, NewAdapterMenus adapter) {
            super(itemView);
            this.menuItems = menuItems;
            this.adapter = adapter;
            itemView.setOnClickListener(this);
            TextoItemMenu=(TextView)itemView.findViewById(R.id.tvItemMenuTrans);
            logoItem= (ImageView)itemView.findViewById(R.id.imgItemTrans);
            drawables = new ArrayList<>();
        }

        @Override
        public void onClick(View view) {
            final OnItemClickListenerMesas listener = adapter.getOnItemClickListener();
            int id = view.getId();
            if (listener != null) {
                listener.onItemClick(this, getAdapterPosition(), id);
            }
        }
    }
}
