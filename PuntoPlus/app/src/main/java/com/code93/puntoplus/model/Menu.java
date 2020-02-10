package com.code93.puntoplus.model;

public class Menu {
    private String id;
    private int version;
    private boolean activo;

    public Menu(String id, int version, boolean activo) {
        this.id = id;
        this.version = version;
        this.activo = activo;
    }

    public Menu() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
