package etu.polytech.foodbm;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class VoucherActivity extends AppCompatActivity {

    private Button CreateVoucher;
    private View getdata;
    private FirebaseFirestore firestore;
    ArrayList<VoucherInfo> note=new ArrayList<>();
    private DocumentSnapshot documentSnapshot;
    private Query query;
    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher);

        NavFragment navFragment = NavFragment.newInstance(R.id.voucherButton);
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_container, navFragment).commit();

        mRecyclerView = findViewById(R.id.recyclerView);

        this.CreateVoucher = (Button) findViewById(R.id.addVoucherButton);
        CreateVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VoucherActivity.this, AddVoucherActivity.class);
                startActivity(intent);
                finish();
            }
        });
        getdata = findViewById(R.id.AfficherVoucher);
        firestore = FirebaseFirestore.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference doc = db.collection("voucher").document();

    }
    private  void getdata(){

        FirebaseFirestore db=FirebaseFirestore.getInstance();
        CollectionReference  collectionReference=db.collection("voucher");
      Query notesQuery=null;
       
        if(documentSnapshot!=null){

      }
      else{ query=collectionReference.whereEqualTo("Description","viande");
      }


        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document:task.getResult()){
                        VoucherInfo voucherInfo=document.toObject(VoucherInfo.class);
                        note.add(voucherInfo);

                    }



                    }
                if(task.getResult().size()!=0){
                    documentSnapshot=task.getResult().getDocuments().get(task.getResult().size()-1);
                    
                }
                else {
                    Log.d(TAG,"Query fAILED, Check Logs");
                }

                }
            }
            );}}






