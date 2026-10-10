package com.botilleria.despachos.consumer;

import com.botilleria.despachos.service.DespachoService;
import com.botilleria.despachos.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class DespachoConsumer {

    @Autowired
    private DespachoService despachoService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void procesarOrden(Map<String, Object> ordenData) {
        System.out.println("Evento recibido en Despachos. Procesando orden para envio...");
        String ordenId = (String) ordenData.get("id");
        if (ordenId != null) {
            despachoService.crearDespacho(ordenId);
        }
    }
}
