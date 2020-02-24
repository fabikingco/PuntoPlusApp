package com.code93.puntoplus.Actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.code93.puntoplus.BD.ClsConexion;
import com.code93.puntoplus.Fragments.DialogDataFragment;
import com.code93.puntoplus.R;
import com.code93.puntoplus.Tools;
import com.code93.puntoplus.appInit;

import io.opencensus.internal.Utils;

import static com.code93.puntoplus.appInit.sComercio;

public class ConfiguracionComercioActivity extends AppCompatActivity {

    public static final int DIALOG_QUEST_CODE = 300;

    LinearLayout lyNombre, lyDocumento, lyDireccion, lyEstado, lyCiudad, lyTelefono1, lyTelefono2;
    TextView tvNombre, tvDocumento, tvDireccion, tvEstado, tvCiudad, tvTelefono1, tvTelefono2;

    ClsConexion bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion_comercio);

        bd = new ClsConexion(this);

        initWidgets();
        cargarDatosDelComercioActual();
    }

    private void cargarDatosDelComercioActual() {
        if (sComercio.getName() != null)
            tvNombre.setText(sComercio.getName());
        if (sComercio.getDocumento() != null)
            tvDocumento.setText(sComercio.getDocumento());
        if (sComercio.getDireccion() != null)
            tvDireccion.setText(sComercio.getDireccion());
        if (sComercio.getEstado() != null)
            tvEstado.setText(sComercio.getEstado());
        if (sComercio.getCiudad() != null)
            tvCiudad.setText(sComercio.getCiudad());
        if (sComercio.getTelefono1() != null)
            tvTelefono1.setText(sComercio.getTelefono1());
        if (sComercio.getTelefono2() != null)
            tvTelefono2.setText(sComercio.getTelefono2());
    }

    private void initWidgets() {
        lyNombre    = findViewById(R.id.lyNombre);
        lyDocumento = findViewById(R.id.lyDocumento);
        lyDireccion = findViewById(R.id.lyDireccion);
        lyEstado    = findViewById(R.id.lyEstado);
        lyCiudad    = findViewById(R.id.lyCiudad);
        lyTelefono1 = findViewById(R.id.lyTelefono1);
        lyTelefono2 = findViewById(R.id.lyTelefono2);
        tvNombre    = findViewById(R.id.tvNombre);
        tvDocumento = findViewById(R.id.tvDocumento);
        tvDireccion = findViewById(R.id.tvDireccion);
        tvEstado    = findViewById(R.id.tvEstado);
        tvCiudad    = findViewById(R.id.tvCiudad);
        tvTelefono1 = findViewById(R.id.tvTelefono1);
        tvTelefono2 = findViewById(R.id.tvTelefono2);
    }

    public void onClickDatosComercio(View view) {
        switch (view.getId()){
            case R.id.lyNombre:
                cargarFragment(sComercio.getName(), "Nombre",
                        "Nombre del comercio", ClsConexion.comercio_name, tvNombre);
                break;
            case R.id.lyDocumento:
                cargarFragment(sComercio.getDocumento(), "Documento",
                        "Documento del comercio", ClsConexion.comercio_documento, tvDocumento);
                break;
            case R.id.lyDireccion:
                cargarFragment(sComercio.getDireccion(), "Direccion",
                        "Dirección del comercio", ClsConexion.comercio_direccion, tvDireccion);
                break;
        }
    }

    public void cargarFragment(String data, String valueDefault, String titulo, String columnUpdate, TextView textView) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (data != null) {
            data = valueDefault;
        }
        DialogDataFragment dialog = DialogDataFragment.newInstance(titulo, Tools.checkNull(data));
        dialog.setRequestCode(DIALOG_QUEST_CODE);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(android.R.id.content, dialog).addToBackStack(null).commit();
        dialog.setmListener(new DialogDataFragment.OnFragmentInteractionListener() {
            @Override
            public void onFragmentInteraction(int requestCode, Object obj) {
                if (bd.updateColumnStringComercio(columnUpdate, obj.toString())) {
                    appInit.cargarComercio(ConfiguracionComercioActivity.this);
                    textView.setText(obj.toString());
                }

            }
        });
    }
}
