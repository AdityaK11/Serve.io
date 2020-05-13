package com.spoton.serveio.ui.general.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import com.spoton.serveio.R;
import com.spoton.serveio.ui.VolunteerUser.activity.VolunteerHomeActivity;

public class VolunteerLoginActivity extends AppCompatActivity {

    EditText et_phno_volunteer_login,et_password_volunteer_login;
    Button btn_createnewacc_volunteer_login, btn_volunteer_login;
    ProgressBar pb_volunteer_login;
    public Boolean validatePhno() {
        String phno = et_phno_volunteer_login.getText().toString().trim();
        if (phno.isEmpty()) {
            et_phno_volunteer_login.setError("Field cannot be empty");
            return false;
        } else {
            et_phno_volunteer_login.setError(null);
            return true;
        }
    }

    public Boolean validatePassword() {
        String password = et_password_volunteer_login.getText().toString().trim();
        if (password.isEmpty()) {
            et_password_volunteer_login.setError("Field cannot be empty");
            return false;
        } else {
            et_password_volunteer_login.setError(null);
            return true;
        }
    }

    public void loginUser(View view) {
        if (!validatePhno() || !validatePassword()) {
            return;
        } else {
            isUser();
        }
    }
    public void isUser() {

        final String phnofromUser = et_phno_volunteer_login.getText().toString().trim();
        final String passwordfromUser= et_password_volunteer_login.getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Volunteers");
        Query checkUser = reference.orderByChild("phno").equalTo(phnofromUser);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    et_phno_volunteer_login.setError(null);

                    String passwordfromDB= dataSnapshot.child(phnofromUser).child("password").getValue(String.class);
                    if (passwordfromDB.equals(passwordfromUser)) {
                        startActivity(new Intent(VolunteerLoginActivity.this, VolunteerHomeActivity.class));
                    } else {
                        et_password_volunteer_login.setError("Wrong password");
                        et_phno_volunteer_login.requestFocus();

                    }
                } else {
                    et_phno_volunteer_login.setError("No such email");
                    et_phno_volunteer_login.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_login);
        btn_volunteer_login = findViewById(R.id.btn_volunteer_login);
        et_phno_volunteer_login = findViewById(R.id.et_phno_volunteer_login);
        et_password_volunteer_login = findViewById(R.id.et_password_volunteer_login);
        pb_volunteer_login = findViewById(R.id.pb_volunteer_login);
        btn_createnewacc_volunteer_login = findViewById(R.id.btn_createnewacc_volunteer_login);
        btn_volunteer_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser(v);
                pb_volunteer_login.setVisibility(View.VISIBLE);

            }

        });
        btn_createnewacc_volunteer_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VolunteerLoginActivity.this,VolunteerRegisterActivity.class));
            }
        });


    }
}

