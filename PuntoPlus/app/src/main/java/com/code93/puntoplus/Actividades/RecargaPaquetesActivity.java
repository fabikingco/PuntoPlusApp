package com.code93.puntoplus.Actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.code93.puntoplus.BD.ClsConexion;
import com.code93.puntoplus.Fragments.DialogDataFragment;
import com.code93.puntoplus.MainActivity;
import com.code93.puntoplus.R;
import com.code93.puntoplus.Tools;
import com.code93.puntoplus.VentanaConfirmacionActivity;
import com.code93.puntoplus.model.Transacciones.Transaccion;

import java.util.Timer;
import java.util.TimerTask;

import dmax.dialog.SpotsDialog;

import static com.code93.puntoplus.Tools.armarMonto;

public class RecargaPaquetesActivity extends AppCompatActivity {

    public static final int DIALOG_QUEST_CODE = 300;

    private TextView tvTitulo, tvSubtitulo,
            tvTituloContrapartida1, tvDataContrapartida1,
            tvTituloContrapartida2, tvDataContrapartida2,
            tvTituloContrapartida3, tvDataContrapartida3,
            tvTituloMonto, tvDataMonto;
    private ImageView imageViewTrans;
    private LinearLayout lyMonto, lyContrapartida1, lyContrapartida2;
    private AppCompatButton btnGenerarSms;
    Transaccion transaccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recarga_paquetes);

        findView();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            transaccion = (Transaccion) bundle.getSerializable("transaccion");
        } else {
            Toast.makeText(this, "El tipo de ingreso no llego o fallo ", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RecargaPaquetesActivity.this, MainActivity.class));
            finish();
        }

        setImageView();

        tvTitulo.setText(transaccion.getTipo());
        tvSubtitulo.setText(transaccion.getOperador());
        tvDataContrapartida2.setText(transaccion.getContrapartida2());
        tvDataContrapartida3.setText(transaccion.getContrapartida3());
        tvDataMonto.setText(transaccion.getMonto());

        onClickLinears();

    }

    private void setImageView() {
        if (transaccion.getOperador().equals(getResources().getString(R.string.claro))){
            imageViewTrans.setImageDrawable(getDrawable(R.drawable.claro_logo));
        }
        if (transaccion.getOperador().equals(getResources().getString(R.string.movistar))){
            imageViewTrans.setImageDrawable(getDrawable(R.drawable.movistar_logo));
        }
        if (transaccion.getOperador().equals(getResources().getString(R.string.tuenti))){
            imageViewTrans.setImageDrawable(getDrawable(R.drawable.tuenti));
        }
        if (transaccion.getOperador().equals(getResources().getString(R.string.cnt))){
            imageViewTrans.setImageDrawable(getDrawable(R.drawable.cnt_logo));
        }
    }

    private void onClickLinears() {
        lyContrapartida1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                DialogDataFragment dialog = DialogDataFragment.newInstance(tvTituloContrapartida1.getText().toString(), Tools.checkNull(tvDataContrapartida1.getText().toString()));
                dialog.setRequestCode(DIALOG_QUEST_CODE);
                dialog.setInputType(InputType.TYPE_CLASS_PHONE);
                dialog.setMaxLeng(10);
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.add(android.R.id.content, dialog).addToBackStack(null).commit();
                dialog.setmListener(new DialogDataFragment.OnFragmentInteractionListener() {
                    @Override
                    public void onFragmentInteraction(int requestCode, Object obj) {
                        tvDataContrapartida1.setText(obj.toString());
                        tvTituloContrapartida1.setTextColor(getResources().getColor(R.color.grey_80));

                    }
                });
            }
        });

        btnGenerarSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GenerarSms();
            }
        });
    }

    private void GenerarSms() {
        if (!validacionDeCamposLlenos()) {
            Toast.makeText(this, "Valide que todos los campos esten completos", Toast.LENGTH_SHORT).show();
            return;
        }

       /* Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", "9305", null));
        smsIntent.putExtra("sms_body", builder.toString());
        startActivity(smsIntent);*/

        final AlertDialog alertDialog = new AlertDialog.Builder(RecargaPaquetesActivity.this).create();
        alertDialog.setTitle("Ecuamovil");
        alertDialog.setMessage(" - - ");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                StringBuilder builder = new StringBuilder();

                String monto = transaccion.getMonto();
                builder.append(transaccion.getContrapartida4());
                builder.append(" ");
                builder.append(monto);
                builder.append(" ");
                builder.append(tvDataContrapartida1.getText().toString());

                Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", "9305", null));
                smsIntent.putExtra("sms_body", builder.toString());
                startActivity(smsIntent);

                final SpotsDialog spotsDialog = new SpotsDialog(RecargaPaquetesActivity.this, "Esperando mensaje de respuesta...");
                spotsDialog.show();
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        spotsDialog.dismiss();
                        validarMensaje();
                    }
                }, 6000);
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(RecargaPaquetesActivity.this, MainActivity.class));
                finish();
            }
        });

        alertDialog.show();
    }

    private void validarMensaje() {
        transaccion.setId(Tools.getLocalDateTime());
        transaccion.setName1(tvTituloContrapartida1.getText().toString());
        transaccion.setContrapartida1(tvDataContrapartida1.getText().toString());
        transaccion.setFecha(Tools.getLocalFormatDate());
        transaccion.setHora(Tools.getLocalFormatTime());
        ClsConexion clsConexion = new ClsConexion(getApplicationContext());
        if (clsConexion.ingresarRegistroTransaccion(transaccion)) {
            Intent intent = new Intent(RecargaPaquetesActivity.this, VentanaConfirmacionActivity.class);
            intent.putExtra("transaccion", transaccion);
            startActivity(intent);
        } else {
            Toast.makeText(this, "No fue posible guardar transaccion en bd", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validacionDeCamposLlenos() {
        if (tvDataContrapartida1.getText() == null || tvDataContrapartida1.getText().toString().trim().equals("")){
            tvTituloContrapartida1.setTextColor(getResources().getColor(R.color.red_900));
            return false;
        }

        return true;
    }

    private void findView() {
        tvTitulo = findViewById(R.id.tvTitulo);
        tvSubtitulo = findViewById(R.id.tvSubtitulo);
        tvTituloContrapartida1 = findViewById(R.id.tvTituloContrapartida1);
        tvDataContrapartida1 = findViewById(R.id.tvDataContrapartida1);
        tvTituloContrapartida2 = findViewById(R.id.tvTituloContrapartida2);
        tvDataContrapartida2 = findViewById(R.id.tvDataContrapartida2);
        tvTituloContrapartida3 = findViewById(R.id.tvTituloContrapartida3);
        tvDataContrapartida3 = findViewById(R.id.tvDataContrapartida3);
        tvTituloMonto = findViewById(R.id.tvTituloMonto);
        tvDataMonto = findViewById(R.id.tvDataMonto);
        imageViewTrans = findViewById(R.id.imageViewTrans);
        lyMonto = findViewById(R.id.lyMonto);
        lyContrapartida1 = findViewById(R.id.lyContrapartida1);
        btnGenerarSms = findViewById(R.id.btnGenerarSms);
    }
}
