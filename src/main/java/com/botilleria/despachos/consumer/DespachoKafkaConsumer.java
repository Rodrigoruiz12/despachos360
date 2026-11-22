package com.botilleria.despachos.consumer;

import com.botilleria.despachos.dto.DespachoPagoEventoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Consumidor Kafka en ms-despachos: escucha alertas antifraude y pagos (pagos.alertas)
 * para retener envios sospechosos, manteniendo despachos.queue de RabbitMQ intacta.
 */
@Component
public class DespachoKafkaConsumer {

    private final ObjectMapper objectMapper;

    public DespachoKafkaConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(
            topics = "${app.kafka.topic-pagos:pagos.alertas}",
            groupId = "${spring.kafka.consumer.group-id:ms-despachos-group}",
            autoStartup = "${app.kafka.enabled:true}"
    )
    public void consumirAlertaLogistica(String payload) {
        DespachoPagoEventoDto evento;
        try {
            evento = objectMapper.readValue(payload, DespachoPagoEventoDto.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Payload no es JSON valido en pagos.alertas", e);
        }

        if ("FRAUDE_BLOQUEADO".equalsIgnoreCase(evento.getTipoEvento())) {
            System.out.println("[MS-DESPACHOS][KAFKA] RETENCION PREVENTIVA por fraude para orden="
                    + evento.getOrdenId() + " | usuario=" + evento.getUsuarioId());
        } else {
            System.out.println("[MS-DESPACHOS][KAFKA] Evento recibido: " + evento.getTipoEvento()
                    + " | orden=" + evento.getOrdenId());
        }
    }
}
