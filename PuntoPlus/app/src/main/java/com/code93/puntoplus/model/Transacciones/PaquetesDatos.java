package com.code93.puntoplus.model.Transacciones;

public class PaquetesDatos {

    private String titulo;
    private String monto;
    private String descripcion;
    private String codigo;

    public PaquetesDatos(String titulo, String monto, String descripcion, String codigo) {
        this.titulo = titulo;
        this.monto = monto;
        this.descripcion = descripcion;
        this.codigo = codigo;
    }

    public PaquetesDatos() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
