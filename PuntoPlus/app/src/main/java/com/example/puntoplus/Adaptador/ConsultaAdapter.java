package com.example.puntoplus.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.puntoplus.BD.ClsConexion;
import com.example.puntoplus.R;
import com.example.puntoplus.Tools;
import com.example.puntoplus.model.SMS;
import com.example.puntoplus.model.SMS_RECV;

import java.util.List;

public class ConsultaAdapter extends RecyclerView.Adapter<ConsultaAdapter.ViewHolder>{
    private Context context;
    private List<SMS> sms;
    private ClsConexion clsConexion;

    public ConsultaAdapter(Context context, List<SMS> sms) {
        this.context = context;
        this.sms = sms;
        clsConexion = new ClsConexion(context);
    }

    @NonNull
    @Override
    public ConsultaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sms_, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ConsultaAdapter.ViewHolder holder, int position) {
        final SMS p = sms.get(getItemCount()-position-1);
        holder.tvTitulo.setText(p.getDestino());
        holder.tvArea.setText(p.getMsg());
        holder.tvHora.setText(Tools.getFormatTime(p.getHora()));
        holder.tvFecha.setText(Tools.getFormatDate(p.getFecha()));
        /*if (p.isRecv()) {
            SMS_RECV r = (SMS_RECV)p;
            if (r.getRecv_visto().equals("f")) {
                holder.visto.setVisibility(View.VISIBLE);
                holder.lySms.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clsConexion.actualizarVisto(p.getFechahora(), true);
                        holder.visto.setVisibility(View.INVISIBLE);
                    }
                });
            }
        }*/
    }

    @Override
    public int getItemCount() {
        return sms.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitulo, tvArea, tvHora, tvFecha;
        private LinearLayout lySms;
        private View visto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvArea = itemView.findViewById(R.id.tvArea);
            tvHora = itemView.findViewById(R.id.tvHora);
            tvFecha = itemView.findViewById(R.id.tvFecha);
            lySms = itemView.findViewById(R.id.sms);
            visto= itemView.findViewById(R.id.visto);
        }
    }
}
