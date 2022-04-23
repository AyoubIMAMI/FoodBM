package etu.polytech.foodbm;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import javax.sql.StatementEvent;


public class VoucherInfo extends AppCompatActivity {
    private String VName;
    private String Description;
    private String VDate;
    private List<String> note=new ArrayList<>();
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    DocumentReference datadoc = db.collection("voucher").document("ROW");
    public String Vdate2;


    public VoucherInfo() {
        datadoc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                   // Vdate2= documentSnapshot.getString("name");

                }
                else{
                    Toast.makeText(VoucherInfo.this, "Row not found ", Toast.LENGTH_SHORT).show();
                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(VoucherInfo.this, "Failure to get Data ", Toast.LENGTH_SHORT).show();
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

