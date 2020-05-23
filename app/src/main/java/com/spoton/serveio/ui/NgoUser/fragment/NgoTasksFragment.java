package com.spoton.serveio.ui.NgoUser.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.spoton.serveio.Common;
import com.spoton.serveio.R;
import com.spoton.serveio.adapters.TaskAdapter;
import com.spoton.serveio.model.Task;

import java.util.ArrayList;

import io.paperdb.Paper;

/**
 * A simple {@link Fragment} subclass.
 */
public class NgoTasksFragment extends Fragment {

    public NgoTasksFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerViewTasks;
    FloatingActionButton addFab;

    ArrayList<Task> array_tasks = new ArrayList<>();

    String UserKey;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ngo_tasks, container, false);

        recyclerViewTasks = view.findViewById(R.id.rv_tasks_ngo);
        addFab = view.findViewById(R.id.fab_add_task);

        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        Paper.init(getActivity().getBaseContext());
        UserKey = Paper.book().read(Common.User_Key);

        getTasks();
        return view;
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
                    if(t.getNgoId().equals(UserKey)){
                        array_tasks.add(t);
                    }

                }
                //Toast.makeText(getContext(), taskTitle.toString(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(getContext(), taskLoc.toString(), Toast.LENGTH_SHORT).show();

                TaskAdapter myAdapter = new TaskAdapter(getContext(),array_tasks);
                recyclerViewTasks.setAdapter(myAdapter);
                recyclerViewTasks.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private  void showDialog(){
        final AlertDialog.Builder alertDailog = new AlertDialog.Builder(getActivity());
        alertDailog.setTitle("Add New Task");
        alertDailog.setMessage("Enter Full Details");

        LayoutInflater inflater = this.getLayoutInflater();
        View add_task_layout = inflater.inflate(R.layout.layout_add_task,null);

        final EditText title = add_task_layout .findViewById(R.id.et_title);
        final EditText desc = add_task_layout .findViewById(R.id.et_desc);
        final EditText tLocation = add_task_layout .findViewById(R.id.et_location);

        alertDailog.setView(add_task_layout);

        alertDailog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                addTask(title.getText().toString(),desc.getText().toString(),tLocation.getText().toString());
            }
        });
        alertDailog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDailog.show();
    }

    private void addTask(final String title, final String desc, final String tLocation) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference mRef = database.getReference().child("Tasks").child("no");
        final DatabaseReference mRef2 = database.getReference().child("Tasks").child("list");

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Double no = dataSnapshot.getValue(Double.class);
                no = no+1;
                Task t = new Task(no,title,desc,UserKey,"xyz",tLocation);
                mRef2.child(String.valueOf(no.intValue())).setValue(t);
                mRef.setValue(no.intValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
