package etu.polytech.foodbm.model;

import androidx.core.app.NotificationCompat;

import etu.polytech.foodbm.VoucherInfo;
import etu.polytech.foodbm.controller.CurrencyController;
import etu.polytech.foodbm.controller.NotificationController;

public class NotificationModel {
    private static int notificationId = 0;
    private NotificationController controller;


    public NotificationModel(NotificationController controller){
        this.controller = controller;
    }

    public void sendNotification(){
        notificationId++;

        VoucherInfo voucherInfo =new VoucherInfo();
        String name = voucherInfo.getVDate();
        String description = voucherInfo.getDescription();

        this.controller.onNotificationSent(name, description, notificationId, NotificationCompat.PRIORITY_DEFAULT);
    }

}
