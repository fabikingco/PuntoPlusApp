package com.code93.puntoplus.model.Transacciones;

import java.io.Serializable;

public class Transaccion implements Serializable {

    private String id;
    private String tipo;
    private String operador;
    private String monto;
    private String name1;
    private String contrapartida1;
    private String name2;
    private String contrapartida2;
    private String name3;
    private String contrapartida3;
    private String name4;
    private String contrapartida4;
    private String fecha;
    private String hora;

    public Transaccion() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getContrapartida1() {
        return contrapartida1;
    }

    public void setContrapartida1(String contrapartida1) {
        this.contrapartida1 = contrapartida1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getContrapartida2() {
        return contrapartida2;
    }

    public void setContrapartida2(String contrapartida2) {
        this.contrapartida2 = contrapartida2;
    }

    public String getName3() {
        return name3;
    }

    public void setName3(String name3) {
        this.name3 = name3;
    }

    public String getContrapartida3() {
        return contrapartida3;
    }

    public void setContrapartida3(String contrapartida3) {
        this.contrapartida3 = contrapartida3;
    }

    public String getName4() {
        return name4;
    }

    public void setName4(String name4) {
        this.name4 = name4;
    }

    public String getContrapartida4() {
        return contrapartida4;
    }

    public void setContrapartida4(String contrapartida4) {
        this.contrapartida4 = contrapartida4;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
