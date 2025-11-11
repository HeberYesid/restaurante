package com.example.patterns.behavioral.mediator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeliveryService extends Component {
    private static final Logger logger = LoggerFactory.getLogger(DeliveryService.class);
    
    public DeliveryService(RestaurantMediator mediator) {
        super(mediator, "Servicio de Entrega");
    }

    public void deliverOrder(String orderId) {
        logger.info("{}: Entregando orden - {}", name, orderId);
        mediator.notify(this, "ORDER_DELIVERED:" + orderId);
    }

    @Override
    public void receive(String message) {
        if (message.startsWith("ORDER_READY:")) {
            String orderId = message.substring(12);
            logger.info("{} recoge orden lista: {}", name, orderId);
        } else {
            logger.info("{} recibe: {}", name, message);
        }
    }
}
