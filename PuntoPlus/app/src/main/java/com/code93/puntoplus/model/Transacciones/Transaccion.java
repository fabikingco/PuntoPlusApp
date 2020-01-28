package com.code93.puntoplus.model.Transacciones;

public class Transaccion {

    private String tipoTransaccion;
    private String operador;
    private String numeroContrapartida;
    private String monto;
    private String mensajeEnviado;
    private String mensajeRecibido;


    public Transaccion() {
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public String getNumeroContrapartida() {
        return numeroContrapartida;
    }

    public void setNumeroContrapartida(String numeroContrapartida) {
        this.numeroContrapartida = numeroContrapartida;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getMensajeEnviado() {
        return mensajeEnviado;
    }

    public void setMensajeEnviado(String mensajeEnviado) {
        this.mensajeEnviado = mensajeEnviado;
    }

    public String getMensajeRecibido() {
        return mensajeRecibido;
    }

    public void setMensajeRecibido(String mensajeRecibido) {
        this.mensajeRecibido = mensajeRecibido;
    }
}
