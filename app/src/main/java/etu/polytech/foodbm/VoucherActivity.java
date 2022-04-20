package etu.polytech.foodbm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VoucherActivity extends AppCompatActivity {

    private Button CreateVoucher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher);
        this.CreateVoucher = (Button) findViewById(R.id.addVoucherButton);
       CreateVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VoucherActivity.this, AddVoucherActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
