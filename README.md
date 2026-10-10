# MS-Despachos

Microservicio encargado de la gestin logstica y seguimiento de envos.

## Caractersticas
- **Consumidor RabbitMQ:** Escucha la cola despachos.queue. Cuando una orden es creada, automticamente genera una gua de despacho en estado "PREPARACION".
- Permite rastrear el estado del envo a travs de un endpoint para el usuario.
- Se registra en Netflix Eureka.
- Protegido va Microsoft Entra ID (Azure AD).

## Instrucciones de Uso
- Puerto por defecto: 8086
- Ejecutar con mvn spring-boot:run o a travs de docker-compose up.
