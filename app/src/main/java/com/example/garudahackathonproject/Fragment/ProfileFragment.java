package com.example.garudahackathonproject.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.garudahackathonproject.Adapter.UserAdapter;
import com.example.garudahackathonproject.Adapter.UserSocialMedia;
import com.example.garudahackathonproject.Model.User;
import com.example.garudahackathonproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    TextView followers, fullname, username, email, number, interests, targets, locations;
    String instagram, tiktok, youtube;

    FirebaseUser firebaseUser;
    String profileid;
    String role;

    private RecyclerView recyclerView;
    private UserSocialMedia userSocialMedia;
    private UserAdapter userAdapter;
    private List<String> mUserSocial;

    boolean[] interestBoolean = new boolean[38];
    boolean[] targetBoolean = new boolean[11];
    boolean[] locationBoolean = new boolean[34];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        SharedPreferences prefs = getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        profileid = prefs.getString("profileid","none");

        followers = view.findViewById(R.id.followers);
        fullname = view.findViewById(R.id.fullname);
        username = view.findViewById(R.id.username);
        email = view.findViewById(R.id.email);
        number = view.findViewById(R.id.number);
        interests = view.findViewById(R.id.interests);
        targets = view.findViewById(R.id.targets);
        locations = view.findViewById(R.id.locations);

        recyclerView = view.findViewById(R.id.recycler_view_for_website);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mUserSocial = new ArrayList<>();
        List<User> mUser = new ArrayList<>();
        userAdapter = new UserAdapter(getContext(),mUser);
        userSocialMedia = new UserSocialMedia(getContext(), mUserSocial);
        recyclerView.setAdapter(userSocialMedia);

        userInfo();


        if (profileid.equals(firebaseUser.getUid())) {
            email.setTextColor(getResources().getColor(R.color.colorPrimaryHint));
            number.setTextColor(getResources().getColor(R.color.colorPrimaryHint));
        } else {
            email.setTextColor(getResources().getColor(R.color.colorPrimary));
            number.setTextColor(getResources().getColor(R.color.colorPrimary));
            email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("mailto:"+email.getText().toString())));
                }
            });
            number.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", number.getText().toString(), null)));
                }
            });
        }

        return view;
    }

    private void userInfo() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(profileid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (getContext() == null) {
                    return;
                }
                User user = dataSnapshot.getValue(User.class);

                username.setText(user.getUsername());
                fullname.setText(user.getFullname());
                email.setText(user.getEmail());
                number.setText(user.getNumber());
                role = user.getRole();

                instagram = user.getInstagram();
                tiktok = user.getTiktok();
                youtube = user.getYoutube();

                mUserSocial.clear();
                if(!instagram.equals(""))mUserSocial.add("https://www.instagram.com/"+instagram+"/");
                if(!tiktok.equals(""))mUserSocial.add("https://www.tiktok.com/@"+tiktok+"/");
                if(!youtube.equals(""))mUserSocial.add("https://www.youtube.com/"+youtube+"/");
                userSocialMedia.notifyDataSetChanged();

                interestBoolean[0]=user.isInterest0();
                interestBoolean[1]=user.isInterest1();
                interestBoolean[2]=user.isInterest2();
                interestBoolean[3]=user.isInterest3();
                interestBoolean[4]=user.isInterest4();
                interestBoolean[5]=user.isInterest5();
                interestBoolean[6]=user.isInterest6();
                interestBoolean[7]=user.isInterest7();
                interestBoolean[8]=user.isInterest8();
                interestBoolean[9]=user.isInterest9();
                interestBoolean[10]=user.isInterest10();
                interestBoolean[11]=user.isInterest11();
                interestBoolean[12]=user.isInterest12();
                interestBoolean[13]=user.isInterest13();
                interestBoolean[14]=user.isInterest14();
                interestBoolean[15]=user.isInterest15();
                interestBoolean[16]=user.isInterest16();
                interestBoolean[17]=user.isInterest17();
                interestBoolean[18]=user.isInterest18();
                interestBoolean[19]=user.isInterest19();
                interestBoolean[20]=user.isInterest20();
                interestBoolean[21]=user.isInterest21();
                interestBoolean[22]=user.isInterest22();
                interestBoolean[23]=user.isInterest23();
                interestBoolean[24]=user.isInterest24();
                interestBoolean[25]=user.isInterest25();
                interestBoolean[26]=user.isInterest26();
                interestBoolean[27]=user.isInterest27();
                interestBoolean[28]=user.isInterest28();
                interestBoolean[29]=user.isInterest29();
                interestBoolean[30]=user.isInterest30();
                interestBoolean[31]=user.isInterest31();
                interestBoolean[32]=user.isInterest32();
                interestBoolean[33]=user.isInterest33();
                interestBoolean[34]=user.isInterest34();
                interestBoolean[35]=user.isInterest35();
                interestBoolean[36]=user.isInterest36();
                interestBoolean[37]=user.isInterest37();
                targetBoolean[0]=user.isTarget0();
                targetBoolean[1]=user.isTarget1();
                targetBoolean[2]=user.isTarget2();
                targetBoolean[3]=user.isTarget3();
                targetBoolean[4]=user.isTarget4();
                targetBoolean[5]=user.isTarget5();
                targetBoolean[6]=user.isTarget6();
                targetBoolean[7]=user.isTarget7();
                targetBoolean[8]=user.isTarget8();
                targetBoolean[9]=user.isTarget9();
                targetBoolean[10]=user.isTarget10();
                locationBoolean[0]=user.isLocation0();
                locationBoolean[1]=user.isLocation1();
                locationBoolean[2]=user.isLocation2();
                locationBoolean[3]=user.isLocation3();
                locationBoolean[4]=user.isLocation4();
                locationBoolean[5]=user.isLocation5();
                locationBoolean[6]=user.isLocation6();
                locationBoolean[7]=user.isLocation7();
                locationBoolean[8]=user.isLocation8();
                locationBoolean[9]=user.isLocation9();
                locationBoolean[10]=user.isLocation10();
                locationBoolean[11]=user.isLocation11();
                locationBoolean[12]=user.isLocation12();
                locationBoolean[13]=user.isLocation13();
                locationBoolean[14]=user.isLocation14();
                locationBoolean[15]=user.isLocation15();
                locationBoolean[16]=user.isLocation16();
                locationBoolean[17]=user.isLocation17();
                locationBoolean[18]=user.isLocation18();
                locationBoolean[19]=user.isLocation19();
                locationBoolean[20]=user.isLocation20();
                locationBoolean[21]=user.isLocation21();
                locationBoolean[22]=user.isLocation22();
                locationBoolean[23]=user.isLocation23();
                locationBoolean[24]=user.isLocation24();
                locationBoolean[25]=user.isLocation25();
                locationBoolean[26]=user.isLocation26();
                locationBoolean[27]=user.isLocation27();
                locationBoolean[28]=user.isLocation28();
                locationBoolean[29]=user.isLocation29();
                locationBoolean[30]=user.isLocation30();
                locationBoolean[31]=user.isLocation31();
                locationBoolean[32]=user.isLocation32();
                locationBoolean[33]=user.isLocation33();
                interestBoolean[0]=user.isInterest0();
                interestBoolean[1]=user.isInterest1();
                interestBoolean[2]=user.isInterest2();
                interestBoolean[3]=user.isInterest3();
                interestBoolean[4]=user.isInterest4();
                interestBoolean[5]=user.isInterest5();
                interestBoolean[6]=user.isInterest6();
                interestBoolean[7]=user.isInterest7();
                interestBoolean[8]=user.isInterest8();
                interestBoolean[9]=user.isInterest9();
                interestBoolean[10]=user.isInterest10();
                interestBoolean[11]=user.isInterest11();
                interestBoolean[12]=user.isInterest12();
                interestBoolean[13]=user.isInterest13();
                interestBoolean[14]=user.isInterest14();
                interestBoolean[15]=user.isInterest15();
                interestBoolean[16]=user.isInterest16();
                interestBoolean[17]=user.isInterest17();
                interestBoolean[18]=user.isInterest18();
                interestBoolean[19]=user.isInterest19();
                interestBoolean[20]=user.isInterest20();
                interestBoolean[21]=user.isInterest21();
                interestBoolean[22]=user.isInterest22();
                interestBoolean[23]=user.isInterest23();
                interestBoolean[24]=user.isInterest24();
                interestBoolean[25]=user.isInterest25();
                interestBoolean[26]=user.isInterest26();
                interestBoolean[27]=user.isInterest27();
                interestBoolean[28]=user.isInterest28();
                interestBoolean[29]=user.isInterest29();
                interestBoolean[30]=user.isInterest30();
                interestBoolean[31]=user.isInterest31();
                interestBoolean[32]=user.isInterest32();
                interestBoolean[33]=user.isInterest33();
                interestBoolean[34]=user.isInterest34();
                interestBoolean[35]=user.isInterest35();
                interestBoolean[36]=user.isInterest36();
                interestBoolean[37]=user.isInterest37();
                targetBoolean[0]=user.isTarget0();
                targetBoolean[1]=user.isTarget1();
                targetBoolean[2]=user.isTarget2();
                targetBoolean[3]=user.isTarget3();
                targetBoolean[4]=user.isTarget4();
                targetBoolean[5]=user.isTarget5();
                targetBoolean[6]=user.isTarget6();
                targetBoolean[7]=user.isTarget7();
                targetBoolean[8]=user.isTarget8();
                targetBoolean[9]=user.isTarget9();
                targetBoolean[10]=user.isTarget10();
                locationBoolean[0]=user.isLocation0();
                locationBoolean[1]=user.isLocation1();
                locationBoolean[2]=user.isLocation2();
                locationBoolean[3]=user.isLocation3();
                locationBoolean[4]=user.isLocation4();
                locationBoolean[5]=user.isLocation5();
                locationBoolean[6]=user.isLocation6();
                locationBoolean[7]=user.isLocation7();
                locationBoolean[8]=user.isLocation8();
                locationBoolean[9]=user.isLocation9();
                locationBoolean[10]=user.isLocation10();
                locationBoolean[11]=user.isLocation11();
                locationBoolean[12]=user.isLocation12();
                locationBoolean[13]=user.isLocation13();
                locationBoolean[14]=user.isLocation14();
                locationBoolean[15]=user.isLocation15();
                locationBoolean[16]=user.isLocation16();
                locationBoolean[17]=user.isLocation17();
                locationBoolean[18]=user.isLocation18();
                locationBoolean[19]=user.isLocation19();
                locationBoolean[20]=user.isLocation20();
                locationBoolean[21]=user.isLocation21();
                locationBoolean[22]=user.isLocation22();
                locationBoolean[23]=user.isLocation23();
                locationBoolean[24]=user.isLocation24();
                locationBoolean[25]=user.isLocation25();
                locationBoolean[26]=user.isLocation26();
                locationBoolean[27]=user.isLocation27();
                locationBoolean[28]=user.isLocation28();
                locationBoolean[29]=user.isLocation29();
                locationBoolean[30]=user.isLocation30();
                locationBoolean[31]=user.isLocation31();
                locationBoolean[32]=user.isLocation32();
                locationBoolean[33]=user.isLocation33();

                interests.setText(showPreferences(interestBoolean,getResources().getStringArray(R.array.interests_preferences)));
                targets.setText(showPreferences(targetBoolean,getResources().getStringArray(R.array.targets_preferences)));
                locations.setText(showPreferences(locationBoolean,getResources().getStringArray(R.array.locations_preferences)));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public String showPreferences(boolean[] preferences, String[] stringArray) {
        String preferencesString = "";
        for (int i = 0; i < preferences.length; i++) if (preferences[i]) preferencesString+=stringArray[i]+"\n";
        return preferencesString;
    }
}