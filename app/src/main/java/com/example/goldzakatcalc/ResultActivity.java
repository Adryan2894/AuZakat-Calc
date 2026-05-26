package com.example.goldzakatcalc;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    TextView tvTotalValue, tvPayableValue, tvTotalZakat;

    @Override
    protected void onCreate(Bundle Bundle) {
        super.onCreate(Bundle);
        setContentView(R.layout.activity_result);

        // 1. Initialize structural views first to lock window anchors
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Calculation Results");
        }

        Button btnBack = findViewById(R.id.btnBackHome);
        tvTotalValue = findViewById(R.id.tvOutputTotal);
        tvPayableValue = findViewById(R.id.tvOutputPayable);
        tvTotalZakat = findViewById(R.id.tvOutputZakat);

        // 2. Fetch argument parcels safely
        double totalValue = getIntent().getDoubleExtra("TOTAL_VALUE", 0.0);
        double payableValue = getIntent().getDoubleExtra("PAYABLE_VALUE", 0.0);
        double totalZakat = getIntent().getDoubleExtra("TOTAL_ZAKAT", 0.0);

        // 3. Format visual components cleanly
        tvTotalValue.setText("RM " + String.format("%.2f", totalValue));
        tvPayableValue.setText("RM " + String.format("%.2f", payableValue));
        tvTotalZakat.setText("RM " + String.format("%.2f", totalZakat));

        btnBack.setOnClickListener(v -> finish());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}