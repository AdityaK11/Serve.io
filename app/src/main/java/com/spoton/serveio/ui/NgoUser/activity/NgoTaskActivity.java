package com.spoton.serveio.ui.NgoUser.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.spoton.serveio.R;

public class NgoTaskActivity extends AppCompatActivity {
    TextView tv_ngos_task_title,tv_ngos_task_description,tv_ngos_task_location,tv_ngos_location;
    String title,description,task_location,ngo_location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_task_display_details);

        setContentView(R.layout.activity_volunteer_task_details_display);
        tv_ngos_task_title = findViewById(R.id.tv_ngos_task_title);
        tv_ngos_task_description = findViewById(R.id.tv_ngos_task_description);
        tv_ngos_task_location = findViewById(R.id.tv_ngos_task_location);
        tv_ngos_location = findViewById(R.id.tv_ngos_location);

        getNgosTasksData();
        setNgosTasksData();
    }
    private void getNgosTasksData()
    {
        if(getIntent().hasExtra("title") && getIntent().hasExtra("description") && getIntent().hasExtra("ngoLocation")
                && getIntent().hasExtra("taskLocation") && getIntent().hasExtra("id"))
        {
            title=getIntent().getStringExtra("title");
            description = getIntent().getStringExtra("description");
            task_location = getIntent().getStringExtra("taskLocation");
            ngo_location = getIntent().getStringExtra("ngoLocation");
        }
        else
        {
            Toast.makeText(NgoTaskActivity.this,"No data",Toast.LENGTH_SHORT).show();
        }
    }
    private void setNgosTasksData()
    {
        tv_ngos_task_title.setText(title);
        tv_ngos_task_description.setText(description);
        tv_ngos_task_location.setText(task_location);
        tv_ngos_location.setText(ngo_location);
    }
}

