package com.spoton.serveio.ui.general.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.spoton.serveio.R;
import com.spoton.serveio.ui.NgoUser.activity.NgoHomeActivity;
import com.spoton.serveio.ui.VolunteerUser.activity.VolunteerHomeActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView login = findViewById(R.id.ngo);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(i);
            }
        });

        TextView reg = findViewById(R.id.volunteer);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SplashScreen.this, RegisterActivity.class);
                startActivity(i);
            }
        });

    }
}
