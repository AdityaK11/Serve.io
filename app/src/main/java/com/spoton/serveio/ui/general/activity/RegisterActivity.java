package com.spoton.serveio.ui.general.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.spoton.serveio.R;

public class RegisterActivity extends AppCompatActivity {
    ProgressBar pb_register_activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button btn_login_if_already = findViewById(R.id.btn_login_if_already);
        Button btn_register_as_volunteer = findViewById(R.id.btn_register_as_volunteer);
        Button btn_register_as_ngo = findViewById(R.id.btn_register_as_ngo);
        pb_register_activity= findViewById(R.id.pb_register_activity);

        btn_login_if_already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pb_register_activity.setVisibility(View.VISIBLE);
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);

            }

        });

        btn_register_as_volunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pb_register_activity.setVisibility(View.VISIBLE);
                startActivity(new Intent(RegisterActivity.this, VolunteerRegisterActivity.class));
                finish();
            }
        });

        btn_register_as_ngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pb_register_activity.setVisibility(View.VISIBLE);
                startActivity(new Intent(RegisterActivity.this, NgoRegisterActivity.class));
                finish();
            }
        });
    }
}
