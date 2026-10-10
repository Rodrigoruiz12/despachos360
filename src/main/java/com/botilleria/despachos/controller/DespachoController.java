package com.botilleria.despachos.controller;

import com.botilleria.despachos.model.Despacho;
import com.botilleria.despachos.service.DespachoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/despachos")
public class DespachoController {

    @Autowired
    private DespachoService despachoService;

    @GetMapping("/orden/{ordenId}")
    public ResponseEntity<Despacho> obtenerPorOrden(@PathVariable String ordenId) {
        Despacho d = despachoService.buscarPorOrdenId(ordenId);
        if (d != null) {
            return ResponseEntity.ok(d);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Despacho> cambiarEstado(@PathVariable String id, @RequestBody Map<String, String> payload) {
        String nuevoEstado = payload.get("estado");
        Despacho d = despachoService.actualizarEstado(id, nuevoEstado);
        if (d != null) {
            return ResponseEntity.ok(d);
        }
        return ResponseEntity.notFound().build();
    }
}
