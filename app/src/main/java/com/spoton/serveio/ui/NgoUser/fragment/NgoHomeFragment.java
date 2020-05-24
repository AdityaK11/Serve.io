package com.spoton.serveio.ui.NgoUser.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.spoton.serveio.Common;
import com.spoton.serveio.R;
import com.spoton.serveio.model.Ngo;
import com.spoton.serveio.ui.general.activity.SplashScreen;

import io.paperdb.Paper;

/**
 * A simple {@link Fragment} subclass.
 */
public class NgoHomeFragment extends Fragment {

    TextView displayUPI;
    Button addUPI;
    EditText getUPI;

    public NgoHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ngo_home,container,false);
        displayUPI = view.findViewById(R.id.tv_upi);
        addUPI = view.findViewById(R.id.btn_add_upi);
        getUPI = view.findViewById(R.id.et_upi);

        final TextView name = view.findViewById(R.id.tv_ngo_name);
        final TextView description = view.findViewById(R.id.tv_ngo_description);
        final TextView location = view.findViewById(R.id.tv_ngo_location);

        Paper.init(getActivity().getBaseContext());

        String userKey = Paper.book().read(Common.User_Key);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Ngos").child("users").child(userKey);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Ngo n = dataSnapshot.getValue(Ngo.class);

                name.setText(n.getName());
                description.setText(n.getDescription());
                location.setText("("+n.getLocation()+")");


                String value = dataSnapshot.child("upi").getValue().toString();
                if(dataSnapshot.exists() && !value.equals("")) {
                    displayUPI.setText("UPI Id: "+value);
                }
                else {
                    displayUPI.setText("No UPI Id! Enter below:"+value);
                    addUPI.setVisibility(View.VISIBLE);
                    getUPI.setVisibility(View.VISIBLE);

                    addUPI.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String UPI = getUPI.getText().toString();
                            if(UPI.isEmpty()) {
                                Toast.makeText(getContext(), "No ID entered!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                FirebaseDatabase.getInstance().getReference().child("Ngos").child("4").child("upi").setValue(UPI);
                                Toast.makeText(getContext(), "ID added successfully :)", Toast.LENGTH_SHORT).show();
                                addUPI.setVisibility(View.INVISIBLE);
                                getUPI.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Button logout = view.findViewById(R.id.btn_logout_ngo);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Paper.book().destroy();
                Intent i = new Intent(getActivity().getBaseContext(), SplashScreen.class);
                startActivity(i);
                getActivity().finish();
            }
        });

        return view;
    }
}
