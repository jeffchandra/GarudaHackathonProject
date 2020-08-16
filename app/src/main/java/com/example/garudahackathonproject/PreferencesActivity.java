package com.example.garudahackathonproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.HashMap;

public class PreferencesActivity extends AppCompatActivity {

    EditText instagram, tiktok, youtube;
    Button register;

    FirebaseAuth auth;
    FirebaseUser firebaseUser;

    CheckBox[] interest = new CheckBox[38];
    CheckBox[] target = new CheckBox[11];
    CheckBox[] location = new CheckBox[34];
    boolean[] interestBoolean = new boolean[38];
    boolean[] targetBoolean = new boolean[11];
    boolean[] locationBoolean = new boolean[34];

    String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        instagram = findViewById(R.id.instagram);
        tiktok = findViewById(R.id.tiktok);
        youtube = findViewById(R.id.youtube);
        register = findViewById(R.id.register);

        Arrays.fill(interestBoolean,false);
        Arrays.fill(targetBoolean,false);
        Arrays.fill(locationBoolean,false);

        for (int i = 0; i < interest.length; i++) interest[i] = findViewById(getResources().getIdentifier("interest"+i,"id",this.getPackageName()));
        for (int i = 0; i < target.length; i++) target[i] = findViewById(getResources().getIdentifier("target"+i,"id",this.getPackageName()));
        for (int i = 0; i < location.length; i++) location[i] = findViewById(getResources().getIdentifier("location"+i,"id",this.getPackageName()));

        auth = FirebaseAuth.getInstance();

        role = getIntent().getStringExtra("Role");

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < interestBoolean.length; i++) interestBoolean[i] = interest[i].isChecked();
                for (int i = 0; i < targetBoolean.length; i++) targetBoolean[i] = target[i].isChecked();
                for (int i = 0; i < locationBoolean.length; i++) locationBoolean[i] = location[i].isChecked();

                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("instagram", instagram.getText().toString()+"");
                hashMap.put("tiktok", tiktok.getText().toString()+"");
                hashMap.put("youtube", youtube.getText().toString()+"");
                for (int i = 0; i < interest.length; i++) hashMap.put("interest"+i+"", interestBoolean[i]);
                for (int i = 0; i < target.length; i++) hashMap.put("target"+i+"", targetBoolean[i]);
                for (int i = 0; i < location.length; i++) hashMap.put("location"+i+"", locationBoolean[i]);

                reference.updateChildren(hashMap);

                startActivity(new Intent(PreferencesActivity.this, MainActivity.class));
            }
        });
    }
}