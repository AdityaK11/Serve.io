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

        TextView hello = findViewById(R.id.hello);
        hello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SplashScreen.this, NgoHomeActivity.class);
                startActivity(i);
            }
        });
    }
}
