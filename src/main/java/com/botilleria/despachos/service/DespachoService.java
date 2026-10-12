package com.botilleria.despachos.service;

import com.botilleria.despachos.model.Despacho;
import com.botilleria.despachos.repository.DespachoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class DespachoService {

    @Autowired(required = false)
    private DespachoRepository despachoRepository;

    private final List<Despacho> despachosMemoria = new ArrayList<>();

    public Despacho crearDespacho(String ordenId) {
        Despacho despacho = new Despacho();
        despacho.setId(UUID.randomUUID().toString());
        despacho.setOrdenId(ordenId);
        despacho.setCodigoSeguimiento("BTL-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase());
        despacho.setEstado("PREPARACION");
        
        if (despachoRepository != null) {
            try {
                despachoRepository.save(despacho);
                System.out.println("Despacho persistido en Supabase para orden: " + ordenId + " | Tracking: " + despacho.getCodigoSeguimiento());
                return despacho;
            } catch (Exception e) {
                System.err.println("Aviso conexion Supabase en despachos: " + e.getMessage());
            }
        }
        
        despachosMemoria.add(despacho);
        System.out.println("Despacho creado en memoria para la orden: " + ordenId + " | Tracking: " + despacho.getCodigoSeguimiento());
        return despacho;
    }

    public Despacho buscarPorOrdenId(String ordenId) {
        if (despachoRepository != null) {
            try {
                Optional<Despacho> opt = despachoRepository.findByOrdenId(ordenId);
                if (opt.isPresent()) return opt.get();
            } catch (Exception e) {
                System.err.println("Aviso conexion Supabase en despachos: " + e.getMessage());
            }
        }
        return despachosMemoria.stream()
                .filter(d -> d.getOrdenId().equals(ordenId))
                .findFirst()
                .orElse(null);
    }
    
    public Despacho actualizarEstado(String id, String nuevoEstado) {
        if (despachoRepository != null) {
            try {
                Optional<Despacho> opt = despachoRepository.findById(id);
                if (opt.isPresent()) {
                    Despacho d = opt.get();
                    d.setEstado(nuevoEstado);
                    return despachoRepository.save(d);
                }
            } catch (Exception e) {
                System.err.println("Aviso conexion Supabase en despachos: " + e.getMessage());
            }
        }
        Despacho d = despachosMemoria.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
        if (d != null) {
            d.setEstado(nuevoEstado);
        }
        return d;
    }
}
