package com.example.patterns.behavioral.mediator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FrontDesk extends Component {
    private static final Logger logger = LoggerFactory.getLogger(FrontDesk.class);
    
    public FrontDesk(RestaurantMediator mediator) {
        super(mediator, "Recepción");
    }

    public void receiveOrder(String orderDetails) {
        logger.info("{}: Orden recibida - {}", name, orderDetails);
        mediator.notify(this, "ORDER_RECEIVED:" + orderDetails);
    }

    @Override
    public void receive(String message) {
        logger.info("{} recibe: {}", name, message);
    }
}
