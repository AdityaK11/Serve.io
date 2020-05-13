package com.spoton.serveio.ui.general.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import com.spoton.serveio.R;

public class LoginActivity extends AppCompatActivity {
    ProgressBar pb_login_activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btn_register_if_new = findViewById(R.id.btn_register_if_new);
        pb_login_activity = findViewById(R.id.pb_login_activity);
        btn_register_if_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pb_login_activity.setVisibility(View.VISIBLE);
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });
        Button btn_login_as_volunteer = findViewById(R.id.btn_login_as_volunteer);
        btn_login_as_volunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pb_login_activity.setVisibility(View.VISIBLE);
                startActivity(new Intent(LoginActivity.this, VolunteerLoginActivity.class));
                finish();
            }
        });
        Button btn_login_as_ngo = findViewById(R.id.btn_login_as_ngo);
        btn_login_as_ngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pb_login_activity.setVisibility(View.VISIBLE);
                startActivity(new Intent(LoginActivity.this, NgoLoginActivity.class));
                finish();
            }
        });
    }
}
