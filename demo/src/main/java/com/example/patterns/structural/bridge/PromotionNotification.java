package com.example.patterns.structural.bridge;

public class PromotionNotification extends Notification {
    private String promotionName;
    private int discountPercentage;

    public PromotionNotification(NotificationSender sender, String promotionName, int discountPercentage) {
        super(sender);
        this.promotionName = promotionName;
        this.discountPercentage = discountPercentage;
    }

    @Override
    public void notify(String recipient) {
        String message = String.format(
            "¡Promoción especial! %s - %d%% de descuento. ¡No te lo pierdas!",
            promotionName, discountPercentage
        );
        sender.send(recipient, message);
    }
}
