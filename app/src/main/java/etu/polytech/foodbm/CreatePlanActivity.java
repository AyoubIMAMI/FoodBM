package etu.polytech.foodbm;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

import org.xml.sax.DTDHandler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreatePlanActivity extends AppCompatActivity {

    // creating variables for
    // EditText and buttons.
    private EditText PlanName, Periode, PlanDate;


    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;

    // creating a variable for
    // our object class
    PlanInfo planInfo;
    public Button sendDatabtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plan);

        // initializing our edittext and button
        PlanName = findViewById(R.id.editTextTextPersonName);
        Periode = findViewById(R.id.editTextNumber);
        PlanDate = findViewById(R.id.editTextDate);

        // below line is used to get the
        // instance of our FIrebase database.
        firebaseDatabase = FirebaseDatabase.getInstance();

        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("Plan");


        // initializing our object
        // class variable.
       planInfo = new PlanInfo();

        sendDatabtn = findViewById(R.id.ajouter);

        // adding on click listener for our button.
        sendDatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // getting text from our edittext fields.
                String name = PlanName.getText().toString();
               String periode = Periode.getText().toString();
               PlanDate.addTextChangedListener(new TextWatcher() {
                   private String current = "";
                   private String ddmmyyyy = "DDMMYYYY";
                   private Calendar cal = Calendar.getInstance();

                   @Override
                   public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                       if (!s.toString().equals(current)) {
                           String clean = s.toString().replaceAll("[^\\d.]", "");
                           String cleanC = current.replaceAll("[^\\d.]", "");

                           int cl = clean.length();
                           int sel = cl;
                           for ( i = 2; i <= cl && i < 6; i += 2) {
                               sel++;
                           }

                           if (clean.equals(cleanC)) sel--;

                           if (clean.length() < 8) {
                               clean = clean + ddmmyyyy.substring(clean.length());
                           } else {
                               //This part makes sure that when we finish entering numbers
                               //the date is correct, fixing it otherwise
                               int day = Integer.parseInt(clean.substring(0, 2));
                               int mon = Integer.parseInt(clean.substring(2, 4));
                               int year = Integer.parseInt(clean.substring(4, 8));

                               if (mon > 12) mon = 12;
                               cal.set(Calendar.MONTH, mon - 1);

                               year = (year < 1900) ? 1900 : (year > 2100) ? 2100 : year;
                               cal.set(Calendar.YEAR, year);
                               // ^ first set year for the line below to work correctly
                               //with leap years - otherwise, date e.g. 29/02/2012
                               //would be automatically corrected to 28/02/2012

                               day = (day > cal.getActualMaximum(Calendar.DATE)) ? cal.getActualMaximum(Calendar.DATE) : day;
                               clean = String.format("%02d%02d%02d", day, mon, year);
                           }

                           clean = String.format("%s/%s/%s", clean.substring(0, 2),
                                   clean.substring(2, 4),
                                   clean.substring(4, 8));

                           sel = sel < 0 ? 0 : sel;
                           current = clean;
                           PlanDate.setText(current);
                           PlanDate.setSelection(sel < current.length() ? sel : current.length());


                       }
                   }
                   @Override
                   public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                   }

                   @Override
                   public void afterTextChanged(Editable editable) {

                   }
               });
                String date = PlanDate.getText().toString();
                if (TextUtils.isEmpty(name) && TextUtils.isEmpty(periode) && TextUtils.isEmpty((CharSequence) PlanDate)) {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(CreatePlanActivity.this, "Please add some data.", Toast.LENGTH_SHORT).show();
                } else {
                    // else call the method to add
                    // data to our database.
                    addDatatoFirebase(name,periode, date);
                }
            }
        });
    }

    private void addDatatoFirebase(String name, String Periode, String dateplan) {

        planInfo.setPlanName(name);
        planInfo.setPeriode(Periode);
        planInfo.setPlanDate(dateplan);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.setValue(planInfo);

                // after adding this data we are showing toast message.
                Toast.makeText(CreatePlanActivity.this, "data added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(CreatePlanActivity.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}