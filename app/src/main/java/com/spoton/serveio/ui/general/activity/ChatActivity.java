package com.spoton.serveio.ui.general.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.spoton.serveio.Common;
import com.spoton.serveio.R;
import com.spoton.serveio.adapters.MessageAdapter;
import com.spoton.serveio.model.MessageModel;
import com.spoton.serveio.model.Task;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import io.paperdb.Paper;

public class ChatActivity extends AppCompatActivity {

    EditText message;
    int no=0,t=0;
    String value;
    CardView sendButton;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    String currentUser;
    String currentReceiver;

    ArrayList<MessageModel> array_messages = new ArrayList<>();

    RecyclerView recyclerViewMessages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Paper.init(this);
        currentUser = Paper.book().read(Common.User_Key);

        Intent intent = getIntent();
        currentReceiver = intent.getStringExtra("OtherUser");

        sendButton = findViewById(R.id.btn_send);
        message = findViewById(R.id.message);

        recyclerViewMessages = findViewById(R.id.rv_messages);
        recyclerViewMessages.setLayoutManager(new LinearLayoutManager(ChatActivity.this));


        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("messages");

        loadMessages();

        sendButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String currentTimeStamp = SystemClock.elapsedRealtime()+"";
                value = message.getText().toString();

                Map<String, Object> messageSender = new HashMap<>();
                messageSender.put("sender", currentUser);
                messageSender.put("receiver", currentReceiver);
                messageSender.put("time", ServerValue.TIMESTAMP);
                messageSender.put("message", value);
                messageSender.put("type", "s");

                Map<String, Object> messageReceiver = new HashMap<>();
                messageReceiver.put("sender", currentUser);
                messageReceiver.put("receiver", currentReceiver);
                messageReceiver.put("time", ServerValue.TIMESTAMP);
                messageReceiver.put("message", value);
                messageReceiver.put("type", "r");

                DatabaseReference sender = reference.child(currentUser).child("sent");
                DatabaseReference receiver = reference.child(currentReceiver).child("received");
                sender.child(currentTimeStamp).setValue(messageSender);
                receiver.child(currentTimeStamp).setValue(messageReceiver);

                message.setText("");
            }
        });


    }

    private void loadMessages() {

        final ArrayList<MessageModel> sent = new ArrayList<>();
        final ArrayList<MessageModel> received = new ArrayList<>();

        DatabaseReference mRef = rootNode.getReference("messages").child(currentUser).child("sent");
        final DatabaseReference mRef2 = rootNode.getReference("messages").child(currentUser).child("received");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                sent.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    MessageModel m = postSnapshot.getValue(MessageModel.class);
                    if(!sent.contains(m)){
                        sent.add(m);
                    }

                }
                mRef2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        received.clear();
                        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                            MessageModel m2 = postSnapshot.getValue(MessageModel.class);
                            if(!received.contains(m2)){
                                received.add(m2);
                            }
                        }
                        array_messages.clear();
                        array_messages.addAll(sent);
                        array_messages.addAll(received);
                        Collections.sort(array_messages);
                        MessageAdapter adapter = new MessageAdapter(ChatActivity.this,array_messages);
                        recyclerViewMessages.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
