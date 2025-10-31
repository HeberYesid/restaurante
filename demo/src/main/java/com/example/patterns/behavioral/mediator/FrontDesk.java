package com.example.patterns.behavioral.mediator;

public class FrontDesk extends Component {
    public FrontDesk(RestaurantMediator mediator) {
        super(mediator, "Recepción");
    }

    public void receiveOrder(String orderDetails) {
        System.out.println(name + ": Orden recibida - " + orderDetails);
        mediator.notify(this, "ORDER_RECEIVED:" + orderDetails);
    }

    @Override
    public void receive(String message) {
        System.out.println(name + " recibe: " + message);
    }
}
