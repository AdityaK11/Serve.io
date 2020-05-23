package com.spoton.serveio.ui.VolunteerUser.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.spoton.serveio.Common;
import com.spoton.serveio.R;
import com.spoton.serveio.adapters.TaskAdapter;
import com.spoton.serveio.model.MessageModel;
import com.spoton.serveio.model.Task;
import com.spoton.serveio.ui.NgoUser.activity.NgoHomeActivity;
import com.spoton.serveio.ui.general.activity.ChatActivity;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import io.paperdb.Paper;

/**
 * A simple {@link Fragment} subclass.
 */
public class VolunteerChatFragment extends Fragment {

    public VolunteerChatFragment() {
        // Required empty public constructor
    }

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    ListView chatListView;
    ArrayList<String> sent;
    ArrayList<String> received;
    ArrayList<String> allChat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_volunteer_chat, container, false);

        chatListView = v.findViewById(R.id.chatListView);

        sent = new ArrayList<>();
        received = new ArrayList<>();
        allChat = new ArrayList<>();

        final String UserKey = Paper.book().read(Common.User_Key);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("messages");
        reference.child(UserKey).child("received").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //chatHistory.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    MessageModel m = postSnapshot.getValue(MessageModel.class);
                    if(!received.contains(m.getSender()))
                        received.add(m.getSender());
                }
                allChat.addAll(received);
                ArrayAdapter<String> chatHistoryAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, allChat);
                chatListView.setAdapter(chatHistoryAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        reference.child(UserKey).child("sent").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //chatHistory.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    MessageModel m = postSnapshot.getValue(MessageModel.class);
                    if(!sent.contains(m.getReceiver()))
                        sent.add(m.getReceiver());
                }
                allChat.retainAll(sent);
                ArrayAdapter<String> chatHistoryAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, allChat);
                chatListView.setAdapter(chatHistoryAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        chatListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                String selectedFromList = (String) chatListView.getItemAtPosition(position);
                intent.putExtra("OtherUser",selectedFromList);
                startActivity(intent);
            }
        });

        return v;
    }
}
