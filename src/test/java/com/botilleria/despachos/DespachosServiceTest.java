package com.botilleria.despachos;

import com.botilleria.despachos.model.Despacho;
import com.botilleria.despachos.service.DespachoService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DespachosServiceTest {

    @Test
    public void alObtenerTodos_deberiaRetornarTresDespachos() {
        DespachoService servicio = new DespachoService();
        List<Despacho> lista = servicio.obtenerTodos();

        assertNotNull(lista);
        assertEquals(3, lista.size());
    }

    @Test
    public void alBuscarPorId_deberiaRetornarElDespachoCorrecto() {
        DespachoService servicio = new DespachoService();
        Despacho despacho = servicio.buscarPorId(1L);

        assertNotNull(despacho);
        assertEquals("Pisco Alto del Carmen 35°", despacho.getNombre());
    }
}