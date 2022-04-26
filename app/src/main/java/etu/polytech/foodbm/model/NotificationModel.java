package etu.polytech.foodbm.model;

import static android.content.ContentValues.TAG;

import android.app.Notification;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import etu.polytech.foodbm.VoucherInfo;
import etu.polytech.foodbm.controller.CurrencyController;
import etu.polytech.foodbm.controller.NotificationController;

public class NotificationModel {
    private static int notificationId = 0;
    private NotificationController controller;
    VoucherInfo voucherInfo =new VoucherInfo();
    Notification notif;



    public NotificationModel(NotificationController controller){
        this.controller = controller;
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference datadoc = db.collection("voucher").document(
                "ROW"
        );
        db.collection("voucher")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.get("vname"));
                                voucherInfo.setVName(document.getString("vname"));
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public void sendNotification(){
        notificationId++;
        String name = "Coupon "+voucherInfo.getVName()+" a atteint sa date d'expiration";
        String description = voucherInfo.getDescription();
        this.notif = this.controller.onNotificationSent(name, description, notificationId, NotificationCompat.PRIORITY_DEFAULT);
    }

}
