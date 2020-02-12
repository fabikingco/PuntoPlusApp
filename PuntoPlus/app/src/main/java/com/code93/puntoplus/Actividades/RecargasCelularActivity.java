package com.code93.puntoplus.Actividades;

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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.code93.puntoplus.Fragments.DialogDataFragment;
import com.code93.puntoplus.IngresoTelefonoActivity;
import com.code93.puntoplus.MainActivity;
import com.code93.puntoplus.R;
import com.code93.puntoplus.Tools;
import com.code93.puntoplus.VentanaConfirmacionActivity;
import com.code93.puntoplus.callbackSMS;

import java.util.Timer;
import java.util.TimerTask;

import dmax.dialog.SpotsDialog;

import static com.code93.puntoplus.Tools.armarMonto;

public class RecargasCelularActivity extends AppCompatActivity {

    public static final int DIALOG_QUEST_CODE = 300;

    private TextView tvTitulo, tvSubtitulo,
            tvTituloContrapartida1, tvDataContrapartida1,
            tvTituloContrapartida2, tvDataContrapartida2,
            tvTituloContrapartida3, tvDataContrapartida3,
            tvTituloMonto, tvDataMonto;
    private ImageView imageViewTrans;
    private LinearLayout lyMonto, lyContrapartida1, lyContrapartida2, lyContrapartida3;
    private AppCompatButton btnGenerarSms;
    String data;
    String[] tipoIngreso;
    String monto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recargas_simert);

        findView();
        tvTitulo.setText("Recargas Celular");
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            data = bundle.getString("tipoIngreso", "default");
            if (data.contains("@")) {
                tipoIngreso = data.split("@");
            } else {
                tipoIngreso = new String[1];
                tipoIngreso[0] = data;
                Toast.makeText(this, "" + tipoIngreso[0], Toast.LENGTH_SHORT).show();
            }
            System.out.println(tipoIngreso[0]);
        } else {
            Toast.makeText(this, "El tipo de ingreso no llego o fallo ", Toast.LENGTH_SHORT).show();
            finish();
        }
        if (tipoIngreso[1] != null) {
            tvSubtitulo.setText(tipoIngreso[1]);
        }

        lyContrapartida2.setVisibility(View.GONE);
        lyContrapartida3.setVisibility(View.GONE);

        setImageView();
        onClickLinears();
    }

    private void setImageView() {
        if (tipoIngreso[1].equals(getResources().getString(R.string.claro))){
            imageViewTrans.setImageDrawable(getDrawable(R.drawable.claro_logo));
        }
        if (tipoIngreso[1].equals(getResources().getString(R.string.movistar))){
            imageViewTrans.setImageDrawable(getDrawable(R.drawable.movistar_logo));
        }
        if (tipoIngreso[1].equals(getResources().getString(R.string.tuenti))){
            imageViewTrans.setImageDrawable(getDrawable(R.drawable.tuenti));
        }
        if (tipoIngreso[1].equals(getResources().getString(R.string.cnt))){
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

        lyMonto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                DialogDataFragment dialog = DialogDataFragment.newInstance(tvTituloMonto.getText().toString(), Tools.checkNull(tvDataMonto.getText().toString()));
                dialog.setRequestCode(DIALOG_QUEST_CODE);
                dialog.setInputType(InputType.TYPE_CLASS_NUMBER);
                dialog.setMonto(true);
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.add(android.R.id.content, dialog).addToBackStack(null).commit();
                dialog.setmListener(new DialogDataFragment.OnFragmentInteractionListener() {
                    @Override
                    public void onFragmentInteraction(int requestCode, Object obj) {
                        tvDataMonto.setText(obj.toString());
                        tvTituloMonto.setTextColor(getResources().getColor(R.color.grey_80));
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

        StringBuilder builder = new StringBuilder();
        String oper = armarOperador(tipoIngreso[1]);
        String monto = armarMonto(tvDataMonto.getText().toString());
        builder.append("Rec");
        builder.append(oper);
        builder.append(" ");
        builder.append(monto);
        builder.append(" ");
        builder.append(tvDataContrapartida1.getText().toString());

       /* Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", "9305", null));
        smsIntent.putExtra("sms_body", builder.toString());
        startActivity(smsIntent);*/

        final AlertDialog alertDialog = new AlertDialog.Builder(RecargasCelularActivity.this).create();
        alertDialog.setTitle("Ecuamovil");
        alertDialog.setMessage("Confirme los datos. \nRecargara $ " + tvDataMonto.getText().toString()
                + " al numero " + tvDataContrapartida1.getText().toString() + " del operador " + tipoIngreso[1]);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                StringBuilder builder = new StringBuilder();
                String oper = armarOperador(tipoIngreso[1]);
                String monto = armarMonto(tvDataMonto.getText().toString());
                builder.append("Rec");
                builder.append(oper);
                builder.append(" ");
                builder.append(monto);
                builder.append(" ");
                builder.append(tvDataContrapartida1.getText().toString());

                Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", "9305", null));
                smsIntent.putExtra("sms_body", builder.toString());
                startActivity(smsIntent);

                final SpotsDialog spotsDialog = new SpotsDialog(RecargasCelularActivity.this, "Esperando mensaje de respuesta...");
                spotsDialog.show();
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        spotsDialog.dismiss();
                        validarMensaje();
                    }
                }, 10000);
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(RecargasCelularActivity.this, MainActivity.class));
                finish();
            }
        });

        alertDialog.show();
    }

    private void validarMensaje() {
        Intent intent = new Intent(RecargasCelularActivity.this, VentanaConfirmacionActivity.class);
        intent.putExtra("tipoIngreso", data);
        intent.putExtra("monto", tvDataMonto.getText().toString());
        intent.putExtra("numero", tvDataContrapartida1.getText().toString());
        startActivity(intent);
    }

    private boolean validacionDeCamposLlenos() {
        if (tvDataContrapartida1.getText() == null || tvDataContrapartida1.getText().toString().trim().equals("")){
            tvTituloContrapartida1.setTextColor(getResources().getColor(R.color.red_900));
            return false;
        }

        if (tvDataMonto.getText() == null || tvDataMonto.getText().toString().trim().equals("")){
            tvTituloMonto.setTextColor(getResources().getColor(R.color.red_900));
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
        lyContrapartida2 = findViewById(R.id.lyContrapartida2);
        lyContrapartida3 = findViewById(R.id.lyContrapartida3);
        btnGenerarSms = findViewById(R.id.btnGenerarSms);
    }

    private String armarOperador(String s) {
        if (s.equals(getResources().getString(R.string.claro))) {
            return "cla";
        }
        if (s.equals(getResources().getString(R.string.movistar))) {
            return "mov";
        }
        if (s.equals(getResources().getString(R.string.cnt))) {
            return "cnt";
        }
        if (s.equals(getResources().getString(R.string.tuenti))) {
            return "tue";
        }
        return s;
    }
}
