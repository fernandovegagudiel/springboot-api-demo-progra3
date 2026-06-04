package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.api.contract.CategoriasApi;
import com.ejemplo.demo.api.dto.CategoriaRequest;
import com.ejemplo.demo.api.dto.CategoriaResponse;
import com.ejemplo.demo.domain.model.Categoria;
import com.ejemplo.demo.domain.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CategoriaController implements CategoriasApi {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @Override
    public ResponseEntity<List<CategoriaResponse>> listarCategorias() {
        List<CategoriaResponse> respuesta = categoriaService.obtenerTodas()
                .stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(respuesta);
    }

    @Override
    public ResponseEntity<CategoriaResponse> obtenerCategoriaPorId(@PathVariable("id") Long id) {
        Categoria categoria = categoriaService.obtenerPorId(id);
        return ResponseEntity.ok(convertirAResponse(categoria));
    }

    @Override
    public ResponseEntity<CategoriaResponse> crearCategoria(@Valid @RequestBody CategoriaRequest request) {
        Categoria categoria = new Categoria();
        categoria.setNombre(request.getNombre());
        categoria.setDescripcion(request.getDescripcion());

        Categoria guardada = categoriaService.guardar(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertirAResponse(guardada));
    }

    @Override
    public ResponseEntity<CategoriaResponse> actualizarCategoria(@PathVariable("id") Long id,
                                                                 @Valid @RequestBody CategoriaRequest request) {
        Categoria datos = new Categoria();
        datos.setNombre(request.getNombre());
        datos.setDescripcion(request.getDescripcion());

        Categoria actualizada = categoriaService.actualizar(id, datos);
        return ResponseEntity.ok(convertirAResponse(actualizada));
    }

    @Override
    public ResponseEntity<Void> eliminarCategoria(@PathVariable("id") Long id) {
        categoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    private CategoriaResponse convertirAResponse(Categoria categoria) {
        CategoriaResponse response = new CategoriaResponse();
        response.setId(categoria.getId());
        response.setNombre(categoria.getNombre());
        response.setDescripcion(categoria.getDescripcion());
        return response;
    }
}
