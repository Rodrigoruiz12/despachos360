package com.botilleria.despachos.service;

import com.botilleria.despachos.model.Despacho;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class DespachoService {
    // Simulacion de BD en memoria
    private List<Despacho> despachos = new ArrayList<>();

    public Despacho crearDespacho(String ordenId) {
        Despacho despacho = new Despacho();
        despacho.setId(UUID.randomUUID().toString());
        despacho.setOrdenId(ordenId);
        despacho.setCodigoSeguimiento("BTL-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase());
        despacho.setEstado("PREPARACION");
        
        despachos.add(despacho);
        System.out.println("Despacho creado para la orden: " + ordenId + " | Tracking: " + despacho.getCodigoSeguimiento());
        return despacho;
    }

    public Despacho buscarPorOrdenId(String ordenId) {
        return despachos.stream()
                .filter(d -> d.getOrdenId().equals(ordenId))
                .findFirst()
                .orElse(null);
    }
    
    public Despacho actualizarEstado(String id, String nuevoEstado) {
        Despacho d = despachos.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
        if (d != null) {
            d.setEstado(nuevoEstado);
        }
        return d;
    }
}
