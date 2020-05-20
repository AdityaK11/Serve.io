package com.spoton.serveio.ui.VolunteerUser.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.spoton.serveio.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class VolunteerProfileFragment extends Fragment {

    public VolunteerProfileFragment() {
        // Required empty public constructor
    }
    TextView id1, tv_name, location1, mail1, phoneNo, age;

    DatabaseReference data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.fragment_volunteer_profile, container, false);

        id1 = (TextView)RootView.findViewById(R.id.iD);
        tv_name = (TextView)RootView.findViewById(R.id.tv_name);
        location1 = (TextView)RootView.findViewById(R.id.location);
        mail1 = (TextView)RootView.findViewById(R.id.mail);
        phoneNo = (TextView)RootView.findViewById(R.id.phone);
        age = RootView.findViewById(R.id.age);


        data = FirebaseDatabase.getInstance().getReference("Volunteers").child("users").child("Volunteer1");

        try{
            data.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String name = dataSnapshot.child("name").getValue(String.class);
                    String email = dataSnapshot.child("email").getValue(String.class);
                    String mobile = dataSnapshot.child("phoneNo").getValue(String.class);
                    String loc = dataSnapshot.child("location").getValue(String.class);
                    String ID = dataSnapshot.child("id").getValue(String.class);
                    String Age = dataSnapshot.child("age").getValue(String.class);

                    id1.setText(ID);
                    mail1.setText(email);
                    location1.setText(loc);
                    tv_name.setText(name);
                    phoneNo.setText(mobile);
                    age.setText(Age);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        return RootView;

    }
}
