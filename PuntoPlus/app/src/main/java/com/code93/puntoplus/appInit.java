package com.code93.puntoplus;

import android.app.Application;
import android.content.Context;

import com.code93.puntoplus.BD.ClsConexion;
import com.mazenrashed.printooth.Printooth;

public class appInit extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        cargarComercio(getApplicationContext());
        initPrint();

    }

    private void initPrint() {
        Printooth.INSTANCE.init(this);
    }

    private void cargarComercio(Context context) {
        ClsConexion bd = new ClsConexion(this);
    }
}
