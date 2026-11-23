package com.botilleria.despachos.service;

import com.botilleria.despachos.dto.DespachoEventoKafkaDto;
import com.botilleria.despachos.model.Despacho;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class KafkaDespachoProducer {

    @Autowired(required = false)
    private KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${app.kafka.enabled:true}")
    private boolean kafkaEnabled;

    @Value("${app.kafka.topic-despachos:despachos.eventos}")
    private String topicDespachos;

    public void publicarEventoDespacho(String tipoEvento, Despacho despacho) {
        if (!kafkaEnabled || kafkaTemplate == null || despacho == null) {
            return;
        }
        try {
            DespachoEventoKafkaDto dto = new DespachoEventoKafkaDto();
            dto.setTipoEvento(tipoEvento);
            dto.setOrigen("ms-despachos");
            dto.setDespachoId(despacho.getId());
            dto.setOrdenId(despacho.getOrdenId());
            dto.setCodigoSeguimiento(despacho.getCodigoSeguimiento());
            dto.setEstado(despacho.getEstado());
            dto.setTimestamp(Instant.now().toString());

            kafkaTemplate.send(topicDespachos, despacho.getOrdenId(), objectMapper.writeValueAsString(dto));
        } catch (Exception e) {
            System.err.println("[ms-despachos] Aviso Kafka (no bloqueante): " + e.getMessage());
        }
    }
}
