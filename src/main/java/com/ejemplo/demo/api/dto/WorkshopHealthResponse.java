package com.ejemplo.demo.api.dto;

public class WorkshopHealthResponse {
    private String estado;
    private String mensaje;

    public WorkshopHealthResponse() {
    }

    public WorkshopHealthResponse(String estado, String mensaje) {
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
