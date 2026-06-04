package com.ejemplo.demo.api.dto;

public class EstadoResponse {
    private String tipo;
    private Integer valorActual;

    public EstadoResponse() {
    }

    public EstadoResponse(String tipo, Integer valorActual) {
        this.tipo = tipo;
        this.valorActual = valorActual;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getValorActual() {
        return valorActual;
    }

    public void setValorActual(Integer valorActual) {
        this.valorActual = valorActual;
    }
}
