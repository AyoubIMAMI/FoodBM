package etu.polytech.foodbm;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Map;

public class AddVoucherActivity extends AppCompatActivity {

        private static final int PICK_IMAGE = 100;
        private ImageView preview;

        // creating variables for
        // EditText and buttons.
        private EditText VName, VDescription, VDate;


        // creating a variable for our
        // Firebase Database.
        FirebaseFirestore firebase;

        // creating a variable for our Database
        // Reference for Firebase.
        DatabaseReference databaseReference;

        // creating a variable for
        // our object class

        public Button sendDatabtn;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_create_voucher);

            // initializing our edittext and button
            VName = findViewById(R.id.voucherName);
            VDescription= findViewById(R.id.voucherDescription);
            VDate = findViewById(R.id.VDate);

            // below line is used to get the
            // instance of our FIrebase database.
            firebase= FirebaseFirestore.getInstance();


            // initializing our object
            // class variable.


            sendDatabtn = findViewById(R.id.ajouterV);

            // adding on click listener for our button.
            sendDatabtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // getting text from our edittext fields.
                    String name = VName.getText().toString();
                    String Description = VDescription.getText().toString();
                    VDate.addTextChangedListener(new TextWatcher() {
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
                               VDate.setText(current);
                                VDate.setSelection(sel < current.length() ? sel : current.length());


                            }
                        }
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void afterTextChanged(Editable editable) {

                        }
                    });
                    String date2 = VDate.getText().toString();
                    if (TextUtils.isEmpty(name) && TextUtils.isEmpty(Description) && TextUtils.isEmpty((CharSequence) VDate)) {
                        // if the text fields are empty
                        // then show the below message.
                        Toast.makeText(etu.polytech.foodbm.AddVoucherActivity.this, "Please add some data.", Toast.LENGTH_SHORT).show();
                    } else {
                        // else call the method to add
                        // data to our database.
                        addDatatoFirebase(name,Description, date2);
                    }
                }
            });

            this.preview = findViewById(R.id.preview);
            Button fromGallery = (Button) findViewById(R.id.gallery);
            fromGallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openGallery();
                }
            });

            Button fromCamera = (Button) findViewById(R.id.camera);
            fromCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openCamera();
                }
            });

            Button addAgendaEvent = (Button) findViewById(R.id.addAgendaEvent);
            addAgendaEvent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AddCalendarEvent(view);
                }
            });
        }

        private void addDatatoFirebase(String name, String Description, String date) {


            CollectionReference dbvoucher = firebase.collection("voucher");
           VoucherInfo voucherInfo = new VoucherInfo();
           voucherInfo.setVName(name);
            voucherInfo.setDescription(Description);
            voucherInfo.setVDate(date);
            dbvoucher.add(voucherInfo).addOnSuccessListener(new OnSuccessListener<DocumentReference>(){
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    // after the data addition is successful
                    // we are displaying a success toast message.
                    Toast.makeText(etu.polytech.foodbm.AddVoucherActivity.this, "Your Course has been added to Firebase Firestore", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // this method is called when the data addition process is failed.
                    // displaying a toast message when data addition is failed.
                    Toast.makeText(etu.polytech.foodbm.AddVoucherActivity.this, "Fail to add course \n" + e, Toast.LENGTH_SHORT).show();
                }

            });
        }

        private void openGallery() {
            Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(gallery, PICK_IMAGE);
        }

        static final int REQUEST_IMAGE_CAPTURE = 1;

        private void openCamera() {
            Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            try {
                startActivityForResult(camera, REQUEST_IMAGE_CAPTURE);
            } catch (ActivityNotFoundException e) {
                // display error state to the user
            }
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data){
            super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
                Uri imageUri = data.getData();
                preview.setImageURI(imageUri);
            }
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                preview.setImageBitmap(imageBitmap);
            }

        }

        public void AddCalendarEvent(View view) {
            Calendar calendarEvent = Calendar.getInstance();
            long time = calendarEvent.getTimeInMillis() + 60 * 60 * 1000;
            Intent i = new Intent(Intent.ACTION_EDIT);
            i.setType("vnd.android.cursor.item/event");
            i.putExtra("beginTime", time);
            i.putExtra("endTime", time);
            i.putExtra("title", VDescription.getText());
            startActivity(i);
        }


    }


