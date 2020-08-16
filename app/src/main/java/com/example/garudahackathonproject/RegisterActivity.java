package com.example.garudahackathonproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText username, fullname, email, password, number;
    Button register;
    TextView txt_login, txt_role;

    FirebaseAuth auth;
    DatabaseReference reference;
    ProgressDialog pd;

    String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        fullname = findViewById(R.id.fullname);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        number = findViewById(R.id.number);
        register = findViewById(R.id.register);
        txt_login = findViewById(R.id.txt_login);
        txt_role = findViewById(R.id.txt_role);

        auth = FirebaseAuth.getInstance();

        role = getIntent().getStringExtra("Role");

        txt_role.setText("Not a "+role+"?");
        if (role.equals("Business")) {
            fullname.setHint("Business Name");
            email.setHint("Business Email");
        }

        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        txt_role.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (role.equals("Business")) {
                    role = "Creator";
                    fullname.setHint("Full Name");
                    email.setHint("Email");
                } else {
                    role = "Business";
                    fullname.setHint("Business Name");
                    email.setHint("Business Email");
                }
                txt_role.setText("Not a "+role+"?");
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd = new ProgressDialog(RegisterActivity.this);
                pd.setMessage("Please wait...");
                pd.show();

                String str_username = username.getText().toString();
                String str_fullname = fullname.getText().toString();
                String str_email = email.getText().toString();
                String str_password = password.getText().toString();
                String str_number = number.getText().toString();
                String str_role = role;

                if (TextUtils.isEmpty(str_username) || TextUtils.isEmpty(str_fullname) || TextUtils.isEmpty(str_email) || TextUtils.isEmpty(str_password) || TextUtils.isEmpty(str_number)) {
                    Toast.makeText(RegisterActivity.this,"All fields are required!",Toast.LENGTH_SHORT).show();
                } else if (str_password.length()<6) {
                    Toast.makeText(RegisterActivity.this, "Password must have 6 characters or more", Toast.LENGTH_SHORT).show();
                } else {
                    register(str_username, str_fullname, str_email, str_password, str_number, str_role);
                }
            }
        });
    }

    private void register(final String username, final String fullname, final String email, String password, final String number, final String role) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser firebaseUser = auth.getCurrentUser();
                    String userid = firebaseUser.getUid();

                    reference = FirebaseDatabase.getInstance().getReference().child("Users").child(userid);

                    boolean[] defaultBoolean = {false,false,false};

                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("id", userid);
                    hashMap.put("username", username.toLowerCase());
                    hashMap.put("fullname", fullname);
                    hashMap.put("email", email);
                    hashMap.put("number", number);
                    hashMap.put("role", role);
                    hashMap.put("instagram", "");
                    hashMap.put("tiktok", "");
                    hashMap.put("youtube", "");
                    for (int i = 0; i < 38; i++) hashMap.put("interest"+i+"", false);
                    for (int i = 0; i < 11; i++) hashMap.put("target"+i+"", false);
                    for (int i = 0; i < 34; i++) hashMap.put("location"+i+"", false);

                    reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                pd.dismiss();
                                Intent intent = new Intent(RegisterActivity.this, PreferencesActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        }
                    });
                } else {
                    pd.dismiss();
                    Toast.makeText(RegisterActivity.this, "You can't register with this email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}