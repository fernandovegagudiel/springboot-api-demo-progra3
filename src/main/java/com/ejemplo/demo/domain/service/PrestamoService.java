package com.ejemplo.demo.domain.service;

import com.ejemplo.demo.api.dto.PrestamoRequest;
import com.ejemplo.demo.api.dto.PrestamoResponse;
import com.ejemplo.demo.domain.model.Prestamo;
import com.ejemplo.demo.domain.repository.PrestamoRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class PrestamoService {
    
    private final PrestamoRepository prestamoRepository;

    // Arreglo de raíz: Inyección por constructor en lugar de @Autowired en el campo
    public PrestamoService(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    public PrestamoResponse calcular(PrestamoRequest req) {
        double P = req.monto().doubleValue();
        double r = req.tasaAnual().doubleValue() / 12 / 100; 
        int n = req.meses();

        double cuota = (P * r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);

        BigDecimal cuotaMensual = new BigDecimal(cuota).setScale(2, RoundingMode.HALF_UP);
        BigDecimal totalPagar = cuotaMensual.multiply(new BigDecimal(n)).setScale(2, RoundingMode.HALF_UP);
        BigDecimal interesTotal = totalPagar.subtract(req.monto()).setScale(2, RoundingMode.HALF_UP);

        Prestamo entidad = new Prestamo();
        entidad.setMonto(req.monto());
        entidad.setMeses(n);
        
        // Aquí ya no dará NullPointerException porque el constructor garantiza que existe
        prestamoRepository.save(entidad); 

        return new PrestamoResponse(cuotaMensual, interesTotal, totalPagar);
    }
    
    public List<Prestamo> obtenerTodos() {
        return prestamoRepository.findAll();
    }
    
    public void eliminarPrestamo(Long id) {
        prestamoRepository.deleteById(id);
    }
}