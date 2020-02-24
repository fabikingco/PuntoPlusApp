package com.code93.puntoplus;

import android.app.Application;
import android.content.Context;

import com.code93.puntoplus.BD.ClsConexion;
import com.code93.puntoplus.model.Comercio;
import com.mazenrashed.printooth.Printooth;

public class appInit extends Application {

    public static Comercio sComercio = null;

    @Override
    public void onCreate() {
        super.onCreate();
        cargarComercio(getApplicationContext());
        initPrint();

    }

    private void initPrint() {
        Printooth.INSTANCE.init(this);
    }

    public static void cargarComercio(Context context) {
        ClsConexion bd = new ClsConexion(context);
        sComercio = bd.getComercioBD();
    }
}
