package com.example.patterns.structural.bridge;

public class ReservationNotification extends Notification {
    private String date;
    private String time;
    private int tableNumber;

    public ReservationNotification(NotificationSender sender, String date, String time, int tableNumber) {
        super(sender);
        this.date = date;
        this.time = time;
        this.tableNumber = tableNumber;
    }

    @Override
    public void notify(String recipient) {
        String message = String.format(
            "Reservación confirmada para el %s a las %s. Mesa: %d. ¡Le esperamos!",
            date, time, tableNumber
        );
        sender.send(recipient, message);
    }
}
