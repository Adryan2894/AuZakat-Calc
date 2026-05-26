package com.example.goldzakatcalc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    Toolbar myToolbar;
    TextInputEditText etWeight, etPrice;
    Spinner spType;
    Button btnCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("AuZakat");
        }

        // Link Java variables to XML IDs safely
        etWeight = findViewById(R.id.goldWeight);
        etPrice = findViewById(R.id.goldPrice);
        spType = findViewById(R.id.goldType);
        btnCalc = findViewById(R.id.btnCalculate);

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weightStr = etWeight.getText().toString().trim();
                String priceStr = etPrice.getText().toString().trim();

                if (weightStr.isEmpty()) {
                    etWeight.setError("Weight is required");
                    etWeight.requestFocus();
                    return;
                }
                if (priceStr.isEmpty()) {
                    etPrice.setError("Price is required");
                    etPrice.requestFocus();
                    return;
                }

                try {
                    double weight = Double.parseDouble(weightStr);
                    double price = Double.parseDouble(priceStr);
                    String type = spType.getSelectedItem().toString();

                    // Using .contains to remain robust against formatted strings like "Keep (85g)"
                    int uruf = type.contains("Keep") ? 85 : 200;

                    double totalValue = weight * price;
                    double weightMinusX = weight - uruf;

                    if (weightMinusX < 0) {
                        weightMinusX = 0;
                    }

                    double zakatPayableValue = weightMinusX * price;
                    double totalZakat = zakatPayableValue * 0.025;

                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("TOTAL_VALUE", totalValue);
                    intent.putExtra("PAYABLE_VALUE", zakatPayableValue);
                    intent.putExtra("TOTAL_ZAKAT", totalZakat);
                    startActivity(intent);

                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Invalid number inputs format", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_share) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            String shareBody = "Check out this Gold Zakat Calculator: https://github.com/Adryan2894/AuZakat-Calc";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(shareIntent, "Share using"));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}