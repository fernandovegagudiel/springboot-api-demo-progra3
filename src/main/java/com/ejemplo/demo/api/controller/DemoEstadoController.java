package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.api.contract.DemoEstadoApi;
import com.ejemplo.demo.api.dto.EstadoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoEstadoController implements DemoEstadoApi {

    private Integer valorSingleton = 0;

    @Override
    public ResponseEntity<EstadoResponse> actualizarSingleton(@PathVariable("valor") Integer valor) {
        this.valorSingleton = valor;
        return ResponseEntity.ok(new EstadoResponse("singleton", this.valorSingleton));
    }

    @Override
    public ResponseEntity<EstadoResponse> obtenerSingleton() {
        return ResponseEntity.ok(new EstadoResponse("singleton", this.valorSingleton));
    }

    @Override
    public ResponseEntity<EstadoResponse> reiniciarSingleton() {
        this.valorSingleton = 0;
        return ResponseEntity.ok(new EstadoResponse("singleton", this.valorSingleton));
    }

    @Override
    public ResponseEntity<EstadoResponse> actualizarManual(@PathVariable("valor") Integer valor) {
        return ResponseEntity.ok(new EstadoResponse("manual", valor));
    }

    @Override
    public ResponseEntity<EstadoResponse> obtenerManual() {
        return ResponseEntity.ok(new EstadoResponse("manual", 0));
    }
}
