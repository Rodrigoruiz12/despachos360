# MS-Despachos

Microservicio encargado de la gestion logstica y seguimiento de envios.

## Caractersticas
- **Consumidor RabbitMQ:** Escucha la cola despachos.queue. Cuando una orden es creada, automticamente genera una guia de despacho en estado "PREPARACION".
- Permite rastrear el estado del envo a traves de un endpoint para el usuario.
- Se registra en Netflix Eureka.
- Protegido va Microsoft Entra ID (Azure AD).

## Instrucciones de Uso
- Puerto por defecto: 8086
- Ejecutar con mvn spring-boot:run o a travs de docker-compose up.
