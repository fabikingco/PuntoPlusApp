package com.code93.puntoplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;

public class IngresoMontoActivity extends AppCompatActivity {

    EditText editText;
    String[] tipoIngreso;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_monto);

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

        editText = findViewById(R.id.etData);
        editText.addTextChangedListener(new TextWatcher() {
            private boolean isChanged = false;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (isChanged) {
                    return;
                }
                String str = editable.toString();
                isChanged = true;
                String cuttedStr = str;
                for (int i = str.length() - 1; i >= 0; i--) {
                    char c = str.charAt(i);
                    if ('.' == c) {
                        cuttedStr = str.substring(0, i) + str.substring(i + 1);
                        break;
                    }
                }
                int NUM = cuttedStr.length();
                int zeroIndex = -1;
                for (int i = 0; i < NUM - 2; i++) {
                    char c = cuttedStr.charAt(i);
                    if (c != '0') {
                        zeroIndex = i;
                        break;
                    } else if (i == NUM - 3) {
                        zeroIndex = i;
                        break;
                    }
                }
                if (zeroIndex != -1) {
                    cuttedStr = cuttedStr.substring(zeroIndex);
                }
                if (cuttedStr.length() < 3) {
                    cuttedStr = "0" + cuttedStr;
                }
                cuttedStr = cuttedStr.substring(0, cuttedStr.length() - 2)
                        + "." + cuttedStr.substring(cuttedStr.length() - 2);

                editText.setText(cuttedStr);
                /*numeroTotal = Double.valueOf(amountServiceCost);
                numeroTotal = numeroTotal + Double.valueOf(cuttedStr);
                String auxNum = String.valueOf(numeroTotal);

                if (auxNum.length() >= 3) {
                    String[] parts = auxNum.split("\\.");
                    String p = parts[1];
                    if (p.length() == 1)
                        auxNum += "0";
                }*/
                editText.setSelection(editText.length());
                isChanged = false;
            }
        });
    }

    public void cargarMonto(View view) {
        String monto = editText.getText().toString();
        if (validarMonto(monto)) {
            if (MainActivity.recargasCelular != null){
                MainActivity.recargasCelular.setMonto(monto);
                Intent intent = new Intent(this, IngresoTelefonoActivity.class);
                intent.putExtra("tipoIngreso", data + "@" + monto);
                Log.d("put ", data + "@" + monto);
                startActivity(intent);
            }
        } else {
            Toast.makeText(this, "Monto no valido", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean validarMonto(String monto) {
        boolean ret = true;

        return ret;
    }

    public void cancelarProceso(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
