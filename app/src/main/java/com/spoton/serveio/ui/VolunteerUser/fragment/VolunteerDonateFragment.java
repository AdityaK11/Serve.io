package com.spoton.serveio.ui.VolunteerUser.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.spoton.serveio.R;
import com.spoton.serveio.ui.VolunteerUser.activity.DonateFoodActivity;
import com.spoton.serveio.ui.VolunteerUser.activity.DonateMoneyActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class VolunteerDonateFragment extends Fragment {

    public VolunteerDonateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_volunteer_donate, container, false);

        ImageButton donateFoodButton,donateMoneyButton;
        donateFoodButton = view.findViewById(R.id.donateFoodButton);
        donateMoneyButton = view.findViewById(R.id.donateMoneyButton);

        donateFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), DonateFoodActivity.class);
                startActivity(intent);
            }
        });

        donateMoneyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), DonateMoneyActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
