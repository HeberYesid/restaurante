package com.example.patterns.behavioral.mediator;

public class Kitchen extends Component {
    public Kitchen(RestaurantMediator mediator) {
        super(mediator, "Cocina");
    }

    public void completeOrder(String orderId) {
        System.out.println(name + ": Orden completada - " + orderId);
        mediator.notify(this, "ORDER_READY:" + orderId);
    }

    @Override
    public void receive(String message) {
        if (message.startsWith("ORDER_RECEIVED:")) {
            String orderDetails = message.substring(15);
            System.out.println(name + " comienza a preparar: " + orderDetails);
        } else {
            System.out.println(name + " recibe: " + message);
        }
    }
}
