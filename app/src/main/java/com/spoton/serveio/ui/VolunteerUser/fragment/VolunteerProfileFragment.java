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
    TextView id1, tv_name, location1, mail1, phoneNo;

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


        data = FirebaseDatabase.getInstance().getReference("Volunteers");

        try{
            data.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String name = dataSnapshot.child("1").child("name").getValue(String.class);
                    String email = dataSnapshot.child("1").child("email").getValue(String.class);
                    String no = dataSnapshot.child("1").child("phoneNo").getValue(String.class);
                    String loc = dataSnapshot.child("1").child("location").getValue(String.class);
                    String i = dataSnapshot.child("1").child("id").getValue(String.class);

                    id1.setText(i);
                    mail1.setText(email);
                    location1.setText(loc);
                    tv_name.setText(name);
                    phoneNo.setText(no);

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
