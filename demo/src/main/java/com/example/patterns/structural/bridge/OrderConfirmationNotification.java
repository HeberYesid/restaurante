package com.example.patterns.structural.bridge;

public class OrderConfirmationNotification extends Notification {
    private String orderNumber;
    private double total;

    public OrderConfirmationNotification(NotificationSender sender, String orderNumber, double total) {
        super(sender);
        this.orderNumber = orderNumber;
        this.total = total;
    }

    @Override
    public void notify(String recipient) {
        String message = String.format(
            "Su orden #%s ha sido confirmada. Total: $%.2f. ¡Gracias por su preferencia!",
            orderNumber, total
        );
        sender.send(recipient, message);
    }
}
