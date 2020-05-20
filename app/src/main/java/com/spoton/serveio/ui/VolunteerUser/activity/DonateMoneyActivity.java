package com.spoton.serveio.ui.VolunteerUser.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.spoton.serveio.R;

public class DonateMoneyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_money);

        Button moneySubmitButton = findViewById(R.id.foodSubmitButton);
        final ConstraintLayout donateMoneyLayout = findViewById(R.id.donateFoodLayout);

        moneySubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donateMoneyLayout.setVisibility(View.GONE);
                setContentView(R.layout.congrats_popup);
            }
        });
    }
}

