package etu.polytech.foodbm;

import android.content.ContentValues;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.sql.StatementEvent;


public class VoucherInfo extends AppCompatActivity {
    private String VName;
    private String Description;
    private String VDate;
    private List<String> note=new ArrayList<>();
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    DocumentReference datadoc = db.collection("voucher").document(
            "\n" +
                    "bAYAnDbNNYfmZiP8zb6B"
    );
    public String Vdate2;


    public VoucherInfo() {
        datadoc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    Vdate2=document.getReference().toString();
                    if (document.exists()) {
                        Log.d(ContentValues.TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d(ContentValues.TAG, "No such document");
                    }
                } else {
                    Log.d(ContentValues.TAG, "get failed with ", task.getException());
                }
            }

        });


    }

    public String getVName() {
        return VName;
    }

    public void setVName(String VoucherName) {
        this.VName = VoucherName;
    }

    public String getDescription() {
        return note.toString();
    }

    public void setDescription(String Description) {
        this.Description =Description;
    }

    public String getVDate() {

       return this.Vdate2;
    }
    public void setVDate(String VDate) {
        this.VDate =VDate;
    }


   }

