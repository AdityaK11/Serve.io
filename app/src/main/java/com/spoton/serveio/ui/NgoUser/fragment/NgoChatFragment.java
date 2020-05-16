package com.spoton.serveio.ui.NgoUser.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.spoton.serveio.R;
import com.spoton.serveio.ui.general.activity.ChatActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class NgoChatFragment extends Fragment {

    public NgoChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_ngo_chat, container, false);

        Button b = v.findViewById(R.id.btn_chat);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity().getBaseContext(), ChatActivity.class);
                startActivity(i);
            }
        });

        return v;
    }
}
