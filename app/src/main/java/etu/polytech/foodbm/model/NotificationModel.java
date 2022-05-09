package etu.polytech.foodbm.model;

import static android.content.ContentValues.TAG;

import android.app.Notification;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArraySet;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import etu.polytech.foodbm.VoucherInfo;
import etu.polytech.foodbm.controller.CurrencyController;
import etu.polytech.foodbm.controller.NotificationController;

public class NotificationModel {
    private static int notificationId = 0;
    private NotificationController controller;
    VoucherInfo voucherInfo =new VoucherInfo();
    List<String> vouch=new ArrayList<>();
    List<String> image=new ArrayList<>();
    Notification notif;
    private Bitmap bitmap;




    public NotificationModel(NotificationController controller){
        this.controller = controller;



    }

    public void sendNotification(){
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
                               Log.d(TAG, document.getId() + " => " + document.get("vdate"));
                                LocalDate date = LocalDate.now();
                                 String formattedDate = date.format(DateTimeFormatter
                                        .ofLocalizedDate(FormatStyle.SHORT));

                               int compar=document.get("vdate").toString().compareTo(formattedDate);
                               if(compar<0)
                                vouch.add(String.valueOf(document.get("vname")));
                                    if(document.get("imageID")!=null){
                                        image.add(String.valueOf(document.get("imageID")));

                               }

                              }

                            int i;
                            for(i=0;i<vouch.size();++i){
                                Log.d(TAG,vouch.toString());
                                Log.d(TAG,image.toString());
                                String name = "Coupon "+vouch.get(i)+" a atteint sa date d'expiration";
                                String description = voucherInfo.getDescription();
                                notificationId++;
                                if(image.get(i)!=null && image.get(i)!=""){
                                    Log.d(TAG,image.get(i));
                                    byte[] decodedString = Base64.decode(image.get(i), Base64.DEFAULT);

                                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                                    notif = controller.onNotificationSent2(name, description, notificationId,decodedByte, NotificationCompat.PRIORITY_DEFAULT);
                                }
                                else {
                                    notif = controller.onNotificationSent(name, description, notificationId, NotificationCompat.PRIORITY_DEFAULT);
                                }
                            }

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });


    }

}
