package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.api.dto.PrestamoRequest;
import com.ejemplo.demo.api.dto.PrestamoResponse;
import com.ejemplo.demo.domain.repository.PrestamoRepository;
import com.ejemplo.demo.domain.service.PrestamoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class) // <-- Esto es vital para que Mockito funcione
class PrestamoControllerTest {

    @Mock
    private PrestamoRepository prestamoRepository; // Creamos el mock del repositorio

    @InjectMocks
    private PrestamoService service; // Mockito creará el service pasándole el repo automáticamente

    @Test
    void testSimulacionExitosa() {
        // 1. Preparar datos
        PrestamoRequest req = new PrestamoRequest(new BigDecimal("1000"), new BigDecimal("10"), 12);
        
        // 2. Ejecutar
        // Como usamos @InjectMocks, 'service' ya tiene el repo inyectado y no es nulo
        PrestamoResponse res = service.calcular(req);
        
        // 3. Validar
        assertNotNull(res);
        System.out.println("✅ Test Exitoso: Cuota " + res.cuotaMensual());
    }
}