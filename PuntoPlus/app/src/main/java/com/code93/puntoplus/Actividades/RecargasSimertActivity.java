package com.code93.puntoplus.Actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.code93.puntoplus.Fragments.DialogDataFragment;
import com.code93.puntoplus.R;
import com.code93.puntoplus.Tools;

public class RecargasSimertActivity extends AppCompatActivity {

    public static final int DIALOG_QUEST_CODE = 300;

    private TextView tvTitulo, tvSubtitulo,
            tvTituloContrapartida1, tvDataContrapartida1,
            tvTituloContrapartida2, tvDataContrapartida2,
            tvTituloContrapartida3, tvDataContrapartida3,
            tvTituloMonto, tvDataMonto;
    private ImageView imageViewTrans;
    private LinearLayout lyMonto, lyContrapartida1, lyContrapartida2, lyContrapartida3;
    String data;
    String[] tipoIngreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recargas_simert);
        findView();
        tvTitulo.setText("Recargas Simert");
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

        validarTipoSimert();

        onClickLinears();

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

        lyContrapartida2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                DialogDataFragment dialog = DialogDataFragment.newInstance(tvTituloContrapartida2.getText().toString(), Tools.checkNull(tvDataContrapartida2.getText().toString()));
                dialog.setRequestCode(DIALOG_QUEST_CODE);
                dialog.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.add(android.R.id.content, dialog).addToBackStack(null).commit();
                dialog.setmListener(new DialogDataFragment.OnFragmentInteractionListener() {
                    @Override
                    public void onFragmentInteraction(int requestCode, Object obj) {
                        tvDataContrapartida2.setText(obj.toString());
                        tvTituloContrapartida2.setTextColor(getResources().getColor(R.color.grey_80));
                    }
                });
            }
        });

        lyContrapartida3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                DialogDataFragment dialog = DialogDataFragment.newInstance(tvTituloContrapartida3.getText().toString(), Tools.checkNull(tvDataContrapartida3.getText().toString()));
                dialog.setRequestCode(DIALOG_QUEST_CODE);
                dialog.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.add(android.R.id.content, dialog).addToBackStack(null).commit();
                dialog.setmListener(new DialogDataFragment.OnFragmentInteractionListener() {
                    @Override
                    public void onFragmentInteraction(int requestCode, Object obj) {
                        tvDataContrapartida3.setText(obj.toString());
                        tvTituloContrapartida3.setTextColor(getResources().getColor(R.color.grey_80));
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



    }

    private void validarTipoSimert() {
        if (tipoIngreso[1].equals(getResources().getString(R.string.recarga_parqueo))) {
            lyContrapartida2.setVisibility(View.GONE);
            lyContrapartida3.setVisibility(View.GONE);
        }
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
    }

    public void GenerarSms(View view) {
        if (!validacionDeCamposLlenos()) {
            Toast.makeText(this, "Valide que todos los campos esten completos", Toast.LENGTH_SHORT).show();
            return;
        }

        String mensaje = "";
    }

    private boolean validacionDeCamposLlenos() {
        if (tvDataContrapartida1.getText() == null || tvDataContrapartida1.getText().toString().trim().equals("")){
            tvTituloContrapartida1.setTextColor(getResources().getColor(R.color.red_900));
            return false;
        }

        if (tipoIngreso[1].equals(getResources().getString(R.string.parqueo_directo))) {
            if (tvDataContrapartida2.getText() == null || tvDataContrapartida2.getText().toString().trim().equals("")){
                tvTituloContrapartida2.setTextColor(getResources().getColor(R.color.red_900));
                return false;
            }
            if (tvDataContrapartida3.getText() == null || tvDataContrapartida3.getText().toString().trim().equals("")){
                tvTituloContrapartida3.setTextColor(getResources().getColor(R.color.red_900));
                return false;
            }
        }

        if (tvDataMonto.getText() == null || tvDataMonto.getText().toString().trim().equals("")){
            tvTituloMonto.setTextColor(getResources().getColor(R.color.red_900));
            return false;
        }

        return true;
    }
}
