package com.example.patterns.behavioral.mediator;

public class DeliveryService extends Component {
    public DeliveryService(RestaurantMediator mediator) {
        super(mediator, "Servicio de Entrega");
    }

    public void deliverOrder(String orderId) {
        System.out.println(name + ": Entregando orden - " + orderId);
        mediator.notify(this, "ORDER_DELIVERED:" + orderId);
    }

    @Override
    public void receive(String message) {
        if (message.startsWith("ORDER_READY:")) {
            String orderId = message.substring(12);
            System.out.println(name + " recoge orden lista: " + orderId);
        } else {
            System.out.println(name + " recibe: " + message);
        }
    }
}
