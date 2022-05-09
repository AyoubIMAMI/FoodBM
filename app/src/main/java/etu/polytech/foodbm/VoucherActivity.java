package etu.polytech.foodbm;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

public class VoucherActivity extends AppCompatActivity {

    private Button CreateVoucher;
    private View getdata;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    ArrayList<VoucherInfo> list;

    private FirebaseFirestore firestore;
    ArrayList<VoucherInfo> note = new ArrayList<>();
    private DocumentSnapshot documentSnapshot;
    private Query query;
    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        myAdapter = new MyAdapter(this,list);
        recyclerView.setAdapter(myAdapter);
        EventChangeListener();
        recyclerView.setAdapter(myAdapter);

        NavFragment navFragment = NavFragment.newInstance(R.id.voucherButton);
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_container, navFragment).commit();

        this.CreateVoucher = (Button) findViewById(R.id.addVoucherButton);
        CreateVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VoucherActivity.this, AddVoucherActivity.class);
                startActivity(intent);
                finish();
            }
        });
        firestore = FirebaseFirestore.getInstance();


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference doc = db.collection("voucher").document();




        }

    private void EventChangeListener() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference doc = db.collection("voucher").document();
        db.collection("voucher").orderBy("vdate").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){
                    Log.e("Firestore error",error.getMessage());
                    return;
                }
                for(DocumentChange dc :value.getDocumentChanges()){
                    if(dc.getType()==DocumentChange.Type.ADDED){
                        list.add(dc.getDocument().toObject(VoucherInfo.class));
                    }
                    myAdapter.notifyDataSetChanged();

                }


            }
        });

    }

    ;



}






