package com.spoton.serveio.ui.VolunteerUser.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.spoton.serveio.R;
import com.spoton.serveio.adapters.TaskAdapter;
import com.spoton.serveio.model.Task;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class VolunteerTaskFragment extends Fragment {

    RecyclerView recyclerViewTasks;

    ArrayList<Task> array_tasks = new ArrayList<>();

    public VolunteerTaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_volunteer_task, container, false);

        recyclerViewTasks = v.findViewById(R.id.rv_tasks_volunteer);

        getTasks();

        return v;
    }

    private void getTasks() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mRef = database.getReference().child("Tasks").child("list");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                array_tasks.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Task t = postSnapshot.getValue(Task.class);
                    array_tasks.add(t);
                }

                TaskAdapter myAdapter = new TaskAdapter(getContext(),array_tasks);
                recyclerViewTasks.setAdapter(myAdapter);
                recyclerViewTasks.setLayoutManager(new LinearLayoutManager(getContext()));
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
