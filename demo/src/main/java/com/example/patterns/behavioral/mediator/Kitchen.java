package com.example.patterns.behavioral.mediator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Kitchen extends Component {
    private static final Logger logger = LoggerFactory.getLogger(Kitchen.class);
    
    public Kitchen(RestaurantMediator mediator) {
        super(mediator, "Cocina");
    }

    public void completeOrder(String orderId) {
        logger.info("{}: Orden completada - {}", name, orderId);
        mediator.notify(this, "ORDER_READY:" + orderId);
    }

    @Override
    public void receive(String message) {
        if (message.startsWith("ORDER_RECEIVED:")) {
            String orderDetails = message.substring(15);
            logger.info("{} comienza a preparar: {}", name, orderDetails);
        } else {
            logger.info("{} recibe: {}", name, message);
        }
    }
}
