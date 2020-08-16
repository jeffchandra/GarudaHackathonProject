package com.example.garudahackathonproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RoleActivity extends AppCompatActivity {

    Intent intent;
    Button creator, business;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);
        creator = findViewById(R.id.creator);
        business = findViewById(R.id.business);
        intent = (new Intent(RoleActivity.this, RegisterActivity.class));
        creator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("Role", "Creator");
                startActivity(intent);
            }
        });
        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("Role", "Business");
                startActivity(intent);
            }
        });
    }
}