package com.example.goldzakatcalc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    private TextView tvGithubLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Setup Toolbar
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Developer Profile"); // Set tajuk toolbar di sini
        }

        // Setup GitHub Link
        tvGithubLink = findViewById(R.id.tvGithubLink);

        // ⚠️ SILA TUKAR: Gantikan dengan link GitHub anda yang sebenar
        final String githubUrl = "https://github.com/Adryan2894/AuZakat-Calculator";
        tvGithubLink.setText(githubUrl);

        // Membuatkan text URL boleh diklik untuk buka browser
        tvGithubLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(githubUrl));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish(); // Menutup halaman ini dan kembali ke halaman utama (Main)
        return true;
    }
}