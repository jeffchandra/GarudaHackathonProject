package com.example.garudahackathonproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.garudahackathonproject.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;

public class EditProfileActivity extends AppCompatActivity {

    ImageView close;
    TextView save;
    MaterialEditText username, fullname, number, instagram, tiktok, youtube;

    FirebaseUser firebaseUser;
    StorageReference storageReference;

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
        setContentView(R.layout.activity_edit_profile);

        close = findViewById(R.id.close);
        save = findViewById(R.id.save);
        username = findViewById(R.id.username);
        fullname = findViewById(R.id.fullname);
        number = findViewById(R.id.number);
        instagram = findViewById(R.id.instagram);
        tiktok = findViewById(R.id.tiktok);
        youtube = findViewById(R.id.youtube);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference("uploads");

        for (int i = 0; i < interest.length; i++) interest[i] = findViewById(getResources().getIdentifier("interest"+i,"id",this.getPackageName()));
        for (int i = 0; i < target.length; i++) target[i] = findViewById(getResources().getIdentifier("target"+i,"id",this.getPackageName()));
        for (int i = 0; i < location.length; i++) location[i] = findViewById(getResources().getIdentifier("location"+i,"id",this.getPackageName()));

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                username.setText(user.getUsername());
                fullname.setText(user.getFullname());
                number.setText(user.getNumber());
                instagram.setText(user.getInstagram());
                tiktok.setText(user.getTiktok());
                youtube.setText(user.getYoutube());
                role = user.getRole();

                if (role.equals("Business")) fullname.setHint("Business Name");
                else fullname.setHint("Full Name");

                interest[0].setChecked(user.isInterest0());
                interest[1].setChecked(user.isInterest1());
                interest[2].setChecked(user.isInterest2());
                interest[3].setChecked(user.isInterest3());
                interest[4].setChecked(user.isInterest4());
                interest[5].setChecked(user.isInterest5());
                interest[6].setChecked(user.isInterest6());
                interest[7].setChecked(user.isInterest7());
                interest[8].setChecked(user.isInterest8());
                interest[9].setChecked(user.isInterest9());
                interest[10].setChecked(user.isInterest10());
                interest[11].setChecked(user.isInterest11());
                interest[12].setChecked(user.isInterest12());
                interest[13].setChecked(user.isInterest13());
                interest[14].setChecked(user.isInterest14());
                interest[15].setChecked(user.isInterest15());
                interest[16].setChecked(user.isInterest16());
                interest[17].setChecked(user.isInterest17());
                interest[18].setChecked(user.isInterest18());
                interest[19].setChecked(user.isInterest19());
                interest[20].setChecked(user.isInterest20());
                interest[21].setChecked(user.isInterest21());
                interest[22].setChecked(user.isInterest22());
                interest[23].setChecked(user.isInterest23());
                interest[24].setChecked(user.isInterest24());
                interest[25].setChecked(user.isInterest25());
                interest[26].setChecked(user.isInterest26());
                interest[27].setChecked(user.isInterest27());
                interest[28].setChecked(user.isInterest28());
                interest[29].setChecked(user.isInterest29());
                interest[30].setChecked(user.isInterest30());
                interest[31].setChecked(user.isInterest31());
                interest[32].setChecked(user.isInterest32());
                interest[33].setChecked(user.isInterest33());
                interest[34].setChecked(user.isInterest34());
                interest[35].setChecked(user.isInterest35());
                interest[36].setChecked(user.isInterest36());
                interest[37].setChecked(user.isInterest37());
                target[0].setChecked(user.isTarget0());
                target[1].setChecked(user.isTarget1());
                target[2].setChecked(user.isTarget2());
                target[3].setChecked(user.isTarget3());
                target[4].setChecked(user.isTarget4());
                target[5].setChecked(user.isTarget5());
                target[6].setChecked(user.isTarget6());
                target[7].setChecked(user.isTarget7());
                target[8].setChecked(user.isTarget8());
                target[9].setChecked(user.isTarget9());
                target[10].setChecked(user.isTarget10());
                location[0].setChecked(user.isLocation0());
                location[1].setChecked(user.isLocation1());
                location[2].setChecked(user.isLocation2());
                location[3].setChecked(user.isLocation3());
                location[4].setChecked(user.isLocation4());
                location[5].setChecked(user.isLocation5());
                location[6].setChecked(user.isLocation6());
                location[7].setChecked(user.isLocation7());
                location[8].setChecked(user.isLocation8());
                location[9].setChecked(user.isLocation9());
                location[10].setChecked(user.isLocation10());
                location[11].setChecked(user.isLocation11());
                location[12].setChecked(user.isLocation12());
                location[13].setChecked(user.isLocation13());
                location[14].setChecked(user.isLocation14());
                location[15].setChecked(user.isLocation15());
                location[16].setChecked(user.isLocation16());
                location[17].setChecked(user.isLocation17());
                location[18].setChecked(user.isLocation18());
                location[19].setChecked(user.isLocation19());
                location[20].setChecked(user.isLocation20());
                location[21].setChecked(user.isLocation21());
                location[22].setChecked(user.isLocation22());
                location[23].setChecked(user.isLocation23());
                location[24].setChecked(user.isLocation24());
                location[25].setChecked(user.isLocation25());
                location[26].setChecked(user.isLocation26());
                location[27].setChecked(user.isLocation27());
                location[28].setChecked(user.isLocation28());
                location[29].setChecked(user.isLocation29());
                location[30].setChecked(user.isLocation30());
                location[31].setChecked(user.isLocation31());
                location[32].setChecked(user.isLocation32());
                location[33].setChecked(user.isLocation33());
                interest[0].setChecked(user.isInterest0());
                interest[1].setChecked(user.isInterest1());
                interest[2].setChecked(user.isInterest2());
                interest[3].setChecked(user.isInterest3());
                interest[4].setChecked(user.isInterest4());
                interest[5].setChecked(user.isInterest5());
                interest[6].setChecked(user.isInterest6());
                interest[7].setChecked(user.isInterest7());
                interest[8].setChecked(user.isInterest8());
                interest[9].setChecked(user.isInterest9());
                interest[10].setChecked(user.isInterest10());
                interest[11].setChecked(user.isInterest11());
                interest[12].setChecked(user.isInterest12());
                interest[13].setChecked(user.isInterest13());
                interest[14].setChecked(user.isInterest14());
                interest[15].setChecked(user.isInterest15());
                interest[16].setChecked(user.isInterest16());
                interest[17].setChecked(user.isInterest17());
                interest[18].setChecked(user.isInterest18());
                interest[19].setChecked(user.isInterest19());
                interest[20].setChecked(user.isInterest20());
                interest[21].setChecked(user.isInterest21());
                interest[22].setChecked(user.isInterest22());
                interest[23].setChecked(user.isInterest23());
                interest[24].setChecked(user.isInterest24());
                interest[25].setChecked(user.isInterest25());
                interest[26].setChecked(user.isInterest26());
                interest[27].setChecked(user.isInterest27());
                interest[28].setChecked(user.isInterest28());
                interest[29].setChecked(user.isInterest29());
                interest[30].setChecked(user.isInterest30());
                interest[31].setChecked(user.isInterest31());
                interest[32].setChecked(user.isInterest32());
                interest[33].setChecked(user.isInterest33());
                interest[34].setChecked(user.isInterest34());
                interest[35].setChecked(user.isInterest35());
                interest[36].setChecked(user.isInterest36());
                interest[37].setChecked(user.isInterest37());
                target[0].setChecked(user.isTarget0());
                target[1].setChecked(user.isTarget1());
                target[2].setChecked(user.isTarget2());
                target[3].setChecked(user.isTarget3());
                target[4].setChecked(user.isTarget4());
                target[5].setChecked(user.isTarget5());
                target[6].setChecked(user.isTarget6());
                target[7].setChecked(user.isTarget7());
                target[8].setChecked(user.isTarget8());
                target[9].setChecked(user.isTarget9());
                target[10].setChecked(user.isTarget10());
                location[0].setChecked(user.isLocation0());
                location[1].setChecked(user.isLocation1());
                location[2].setChecked(user.isLocation2());
                location[3].setChecked(user.isLocation3());
                location[4].setChecked(user.isLocation4());
                location[5].setChecked(user.isLocation5());
                location[6].setChecked(user.isLocation6());
                location[7].setChecked(user.isLocation7());
                location[8].setChecked(user.isLocation8());
                location[9].setChecked(user.isLocation9());
                location[10].setChecked(user.isLocation10());
                location[11].setChecked(user.isLocation11());
                location[12].setChecked(user.isLocation12());
                location[13].setChecked(user.isLocation13());
                location[14].setChecked(user.isLocation14());
                location[15].setChecked(user.isLocation15());
                location[16].setChecked(user.isLocation16());
                location[17].setChecked(user.isLocation17());
                location[18].setChecked(user.isLocation18());
                location[19].setChecked(user.isLocation19());
                location[20].setChecked(user.isLocation20());
                location[21].setChecked(user.isLocation21());
                location[22].setChecked(user.isLocation22());
                location[23].setChecked(user.isLocation23());
                location[24].setChecked(user.isLocation24());
                location[25].setChecked(user.isLocation25());
                location[26].setChecked(user.isLocation26());
                location[27].setChecked(user.isLocation27());
                location[28].setChecked(user.isLocation28());
                location[29].setChecked(user.isLocation29());
                location[30].setChecked(user.isLocation30());
                location[31].setChecked(user.isLocation31());
                location[32].setChecked(user.isLocation32());
                location[33].setChecked(user.isLocation33());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < interestBoolean.length; i++) interestBoolean[i] = interest[i].isChecked();
                for (int i = 0; i < targetBoolean.length; i++) targetBoolean[i] = target[i].isChecked();
                for (int i = 0; i < locationBoolean.length; i++) locationBoolean[i] = location[i].isChecked();
                updateProfile(username.getText().toString(),fullname.getText().toString(),number.getText().toString(),instagram.getText().toString(),tiktok.getText().toString(),youtube.getText().toString(),interestBoolean,targetBoolean,locationBoolean);
            }
        });
    }

    private void updateProfile(String username, String fullname, String number, String instagram, String tiktok, String youtube, boolean[] interest, boolean[] target, boolean[] location) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("username", username);
        hashMap.put("fullname", fullname);
        hashMap.put("number", number);
        hashMap.put("instagram", instagram);
        hashMap.put("tiktok", tiktok);
        hashMap.put("youtube", youtube);
        for (int i = 0; i < interest.length; i++) hashMap.put("interest"+i+"", interest[i]);
        for (int i = 0; i < target.length; i++) hashMap.put("target"+i+"", target[i]);
        for (int i = 0; i < location.length; i++) hashMap.put("location"+i+"", location[i]);

        reference.updateChildren(hashMap);
    }
}