package com.spoton.serveio.ui.NgoUser.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.spoton.serveio.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NgoTasksFragment extends Fragment {

    public NgoTasksFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerViewTasks;
    FloatingActionButton addFab;


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

        getTasks();
        return view;
    }

    private void getTasks() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mRef = database.getReference().child("Tasks");


    }

    private  void showDialog(){
        final AlertDialog.Builder alertDailog = new AlertDialog.Builder(getActivity());
        alertDailog.setTitle("Add New Task");
        alertDailog.setMessage("Enter Full Details");

        LayoutInflater inflater = this.getLayoutInflater();
        View add_task_layout = inflater.inflate(R.layout.layout_add_task,null);

        EditText title = add_task_layout .findViewById(R.id.et_title);
        EditText desc = add_task_layout .findViewById(R.id.et_desc);
        EditText tLocation = add_task_layout .findViewById(R.id.et_location);
        CardView add = add_task_layout .findViewById(R.id.cv_add_task);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        alertDailog.setView(add_task_layout);

        alertDailog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                /*if(newTable!=null){
                    tableList.push().setValue(newTable);
                    //edtRestName1=add_name.getText().toString();

                }*/
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
}
