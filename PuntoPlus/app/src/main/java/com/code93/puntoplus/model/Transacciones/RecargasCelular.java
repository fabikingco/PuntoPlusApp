package com.code93.puntoplus.model.Transacciones;

public class RecargasCelular {

    public String operador;
    public String numero;
    public String monto;

    public RecargasCelular() {
    }

    public RecargasCelular(String operador, String numero, String monto) {
        this.operador = operador;
        this.numero = numero;
        this.monto = monto;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }
}
