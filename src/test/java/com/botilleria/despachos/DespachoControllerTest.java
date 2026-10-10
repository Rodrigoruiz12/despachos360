package com.botilleria.despachos;

import com.botilleria.despachos.controller.DespachoController;
import com.botilleria.despachos.controller.DespachoController;
import com.botilleria.despachos.model.Despacho;
import com.botilleria.despachos.service.DespachoService;
import com.botilleria.despachos.service.DespachoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(DespachoController.class)
public class DespachoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DespachoService servicioDeDespacho;

    @Test
    public void listarCatalogo_DeberiaRetornarListaDeDespachos() throws Exception {
        Despacho p1 = new Despacho(1L, "Pisco Alto del Carmen 35°", 7500, 20, "https://via.placeholder.com/200");
        Mockito.when(servicioDeDespacho.obtenerTodos()).thenReturn(List.of(p1));

        mockMvc.perform(get("/api/despachos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Pisco Alto del Carmen 35°"));
    }
}