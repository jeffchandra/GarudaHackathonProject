package com.example.garudahackathonproject.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.garudahackathonproject.Adapter.UserAdapter;
import com.example.garudahackathonproject.Adapter.UserSuggested;
import com.example.garudahackathonproject.Model.User;
import com.example.garudahackathonproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private RecyclerView recyclerView, recyclerView_suggested, recyclerView_suggested2;
    private UserAdapter userAdapter;
    private UserSuggested userSuggested,userSuggested1;
    private List<User> mUsers, mUsers2, mUsers3;
    private RelativeLayout relativeLayout;

    FirebaseUser firebaseUser;
    EditText search_bar;

    boolean[] interest = new boolean[38];
    boolean[] target = new boolean[11];
    boolean[] location = new boolean[34];

    boolean[] interestOther = new boolean[38];
    boolean[] targetOther = new boolean[11];
    boolean[] locationOther = new boolean[34];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                interest[0] = user.isInterest0();
                interest[1] = user.isInterest1();
                interest[2] = user.isInterest2();
                interest[3] = user.isInterest3();
                interest[4] = user.isInterest4();
                interest[5] = user.isInterest5();
                interest[6] = user.isInterest6();
                interest[7] = user.isInterest7();
                interest[8] = user.isInterest8();
                interest[9] = user.isInterest9();
                interest[10] = user.isInterest10();
                interest[11] = user.isInterest11();
                interest[12] = user.isInterest12();
                interest[13] = user.isInterest13();
                interest[14] = user.isInterest14();
                interest[15] = user.isInterest15();
                interest[16] = user.isInterest16();
                interest[17] = user.isInterest17();
                interest[18] = user.isInterest18();
                interest[19] = user.isInterest19();
                interest[20] = user.isInterest20();
                interest[21] = user.isInterest21();
                interest[22] = user.isInterest22();
                interest[23] = user.isInterest23();
                interest[24] = user.isInterest24();
                interest[25] = user.isInterest25();
                interest[26] = user.isInterest26();
                interest[27] = user.isInterest27();
                interest[28] = user.isInterest28();
                interest[29] = user.isInterest29();
                interest[30] = user.isInterest30();
                interest[31] = user.isInterest31();
                interest[32] = user.isInterest32();
                interest[33] = user.isInterest33();
                interest[34] = user.isInterest34();
                interest[35] = user.isInterest35();
                interest[36] = user.isInterest36();
                interest[37] = user.isInterest37();
                target[0] = user.isTarget0();
                target[1] = user.isTarget1();
                target[2] = user.isTarget2();
                target[3] = user.isTarget3();
                target[4] = user.isTarget4();
                target[5] = user.isTarget5();
                target[6] = user.isTarget6();
                target[7] = user.isTarget7();
                target[8] = user.isTarget8();
                target[9] = user.isTarget9();
                target[10] = user.isTarget10();
                location[0] = user.isLocation0();
                location[1] = user.isLocation1();
                location[2] = user.isLocation2();
                location[3] = user.isLocation3();
                location[4] = user.isLocation4();
                location[5] = user.isLocation5();
                location[6] = user.isLocation6();
                location[7] = user.isLocation7();
                location[8] = user.isLocation8();
                location[9] = user.isLocation9();
                location[10] = user.isLocation10();
                location[11] = user.isLocation11();
                location[12] = user.isLocation12();
                location[13] = user.isLocation13();
                location[14] = user.isLocation14();
                location[15] = user.isLocation15();
                location[16] = user.isLocation16();
                location[17] = user.isLocation17();
                location[18] = user.isLocation18();
                location[19] = user.isLocation19();
                location[20] = user.isLocation20();
                location[21] = user.isLocation21();
                location[22] = user.isLocation22();
                location[23] = user.isLocation23();
                location[24] = user.isLocation24();
                location[25] = user.isLocation25();
                location[26] = user.isLocation26();
                location[27] = user.isLocation27();
                location[28] = user.isLocation28();
                location[29] = user.isLocation29();
                location[30] = user.isLocation30();
                location[31] = user.isLocation31();
                location[32] = user.isLocation32();
                location[33] = user.isLocation33();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView_suggested = view.findViewById(R.id.suggested_bar);
        recyclerView_suggested.setHasFixedSize(true);
        recyclerView_suggested.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        recyclerView_suggested2 = view.findViewById(R.id.suggested_bar2);
        recyclerView_suggested2.setHasFixedSize(true);
        recyclerView_suggested2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        search_bar = view.findViewById(R.id.search_bar);
        relativeLayout = view.findViewById(R.id.discover_layout);

        search_bar.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    recyclerView.setVisibility(View.VISIBLE);
                    relativeLayout.setVisibility(View.GONE);
                    readUsers();
                } else {
                    recyclerView.setVisibility(View.GONE);
                    relativeLayout.setVisibility(View.VISIBLE);
                    discoverUsers();
                    discoverUsers2();
                }
            }
        });

        mUsers = new ArrayList<>();
        mUsers2 = new ArrayList<>();
        mUsers3 = new ArrayList<>();
        userAdapter = new UserAdapter(getContext(), mUsers);
        recyclerView.setAdapter(userAdapter);

        userSuggested = new UserSuggested(getContext(), mUsers2);
        recyclerView_suggested.setAdapter(userSuggested);
        discoverUsers();
        userSuggested1 = new UserSuggested(getContext(), mUsers3);
        recyclerView_suggested2.setAdapter(userSuggested1);
        discoverUsers2();

        search_bar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                recyclerView.setVisibility(View.VISIBLE);
                relativeLayout.setVisibility(View.GONE);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                recyclerView.setVisibility(View.VISIBLE);
                relativeLayout.setVisibility(View.GONE);
                searchUsers(charSequence.toString().toLowerCase());
            }

            @Override
            public void afterTextChanged(Editable s) {
                recyclerView.setVisibility(View.VISIBLE);
                relativeLayout.setVisibility(View.GONE);
            }
        });

        return view;
    }

    private void searchUsers(String s) {
        Query query = FirebaseDatabase.getInstance().getReference("Users").orderByChild("username")
                .startAt(s)
                .endAt(s+"\uf8ff");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUsers.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    mUsers.add(user);
                }

                userAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void readUsers() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (search_bar.getText().toString().equals("")) {
                    mUsers.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        User user = snapshot.getValue(User.class);
                        mUsers.add(user);
                    }

                    userAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void discoverUsers() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUsers2.clear();
                int max = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    if (user.getRole().equals("Creator")) {
                        if (getMatch(user)>max){
                            mUsers2.add(0,user);
                        } else {
                            mUsers2.add(user);
                        }
                    }
                }

                userSuggested.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void discoverUsers2() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUsers3.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    if (user.getRole().equals("Business")) {
                        //mUsers3.add(user);
                    }
                }

                userSuggested1.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public int getMatch(User user) {
        interestOther[0] = user.isInterest0();
        interestOther[1] = user.isInterest1();
        interestOther[2] = user.isInterest2();
        interestOther[3] = user.isInterest3();
        interestOther[4] = user.isInterest4();
        interestOther[5] = user.isInterest5();
        interestOther[6] = user.isInterest6();
        interestOther[7] = user.isInterest7();
        interestOther[8] = user.isInterest8();
        interestOther[9] = user.isInterest9();
        interestOther[10] = user.isInterest10();
        interestOther[11] = user.isInterest11();
        interestOther[12] = user.isInterest12();
        interestOther[13] = user.isInterest13();
        interestOther[14] = user.isInterest14();
        interestOther[15] = user.isInterest15();
        interestOther[16] = user.isInterest16();
        interestOther[17] = user.isInterest17();
        interestOther[18] = user.isInterest18();
        interestOther[19] = user.isInterest19();
        interestOther[20] = user.isInterest20();
        interestOther[21] = user.isInterest21();
        interestOther[22] = user.isInterest22();
        interestOther[23] = user.isInterest23();
        interestOther[24] = user.isInterest24();
        interestOther[25] = user.isInterest25();
        interestOther[26] = user.isInterest26();
        interestOther[27] = user.isInterest27();
        interestOther[28] = user.isInterest28();
        interestOther[29] = user.isInterest29();
        interestOther[30] = user.isInterest30();
        interestOther[31] = user.isInterest31();
        interestOther[32] = user.isInterest32();
        interestOther[33] = user.isInterest33();
        interestOther[34] = user.isInterest34();
        interestOther[35] = user.isInterest35();
        interestOther[36] = user.isInterest36();
        interestOther[37] = user.isInterest37();
        targetOther[0] = user.isTarget0();
        targetOther[1] = user.isTarget1();
        targetOther[2] = user.isTarget2();
        targetOther[3] = user.isTarget3();
        targetOther[4] = user.isTarget4();
        targetOther[5] = user.isTarget5();
        targetOther[6] = user.isTarget6();
        targetOther[7] = user.isTarget7();
        targetOther[8] = user.isTarget8();
        targetOther[9] = user.isTarget9();
        targetOther[10] = user.isTarget10();
        locationOther[0] = user.isLocation0();
        locationOther[1] = user.isLocation1();
        locationOther[2] = user.isLocation2();
        locationOther[3] = user.isLocation3();
        locationOther[4] = user.isLocation4();
        locationOther[5] = user.isLocation5();
        locationOther[6] = user.isLocation6();
        locationOther[7] = user.isLocation7();
        locationOther[8] = user.isLocation8();
        locationOther[9] = user.isLocation9();
        locationOther[10] = user.isLocation10();
        locationOther[11] = user.isLocation11();
        locationOther[12] = user.isLocation12();
        locationOther[13] = user.isLocation13();
        locationOther[14] = user.isLocation14();
        locationOther[15] = user.isLocation15();
        locationOther[16] = user.isLocation16();
        locationOther[17] = user.isLocation17();
        locationOther[18] = user.isLocation18();
        locationOther[19] = user.isLocation19();
        locationOther[20] = user.isLocation20();
        locationOther[21] = user.isLocation21();
        locationOther[22] = user.isLocation22();
        locationOther[23] = user.isLocation23();
        locationOther[24] = user.isLocation24();
        locationOther[25] = user.isLocation25();
        locationOther[26] = user.isLocation26();
        locationOther[27] = user.isLocation27();
        locationOther[28] = user.isLocation28();
        locationOther[29] = user.isLocation29();
        locationOther[30] = user.isLocation30();
        locationOther[31] = user.isLocation31();
        locationOther[32] = user.isLocation32();
        locationOther[33] = user.isLocation33();
        int match = 0;
        for (int i = 0; i < interest.length; i++) if (interest[i]==interestOther[i]&&interestOther[i]) match++;
        for (int i = 0; i < target.length; i++) if (target[i]==targetOther[i]&&targetOther[i]) match++;
        for (int i = 0; i < location.length; i++) if (location[i]==locationOther[i]&&locationOther[i]) match++;
        return match;
    }
}