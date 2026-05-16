package com.example.goldzakatcalc;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    // Declare the TextViews where the output will go
    TextView tvTotalValue, tvPayableValue, tvTotalZakat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Button btnBack = findViewById(R.id.btnBackHome);

        // 1. Link Java variables to the IDs in activity_result.xml
        // Make sure these IDs match exactly what you set in your Design view
        tvTotalValue = findViewById(R.id.tvOutputTotal);
        tvPayableValue = findViewById(R.id.tvOutputPayable);
        tvTotalZakat = findViewById(R.id.tvOutputZakat);

        // 2. Get the data sent from MainActivity
        double totalValue = getIntent().getDoubleExtra("TOTAL_VALUE", 0.0);
        double payableValue = getIntent().getDoubleExtra("PAYABLE_VALUE", 0.0);
        double totalZakat = getIntent().getDoubleExtra("TOTAL_ZAKAT", 0.0);

        // 3. Display the results
        // We use String.format to show only 2 decimal places (RM style)
        tvTotalValue.setText("RM " + String.format("%.2f", totalValue));
        tvPayableValue.setText("RM " + String.format("%.2f", payableValue));
        tvTotalZakat.setText("RM " + String.format("%.2f", totalZakat));

        // Inside onCreate, after setContentView
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        btnBack.setOnClickListener(v -> finish());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish(); // Closes this page and goes back to Main
        return true;
    }
}