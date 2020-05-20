package com.spoton.serveio.ui.VolunteerUser.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.spoton.serveio.R;

public class DonateFoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_food);

        Button foodSubmitButton = findViewById(R.id.foodSubmitButton);
        final ConstraintLayout donateFoodLayout = findViewById(R.id.donateFoodLayout);

        foodSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donateFoodLayout.setVisibility(View.GONE);
                setContentView(R.layout.congrats_popup);
            }
        });

    }
}

