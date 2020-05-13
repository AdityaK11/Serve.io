package com.spoton.serveio.ui.general.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.spoton.serveio.R;
import com.spoton.serveio.ui.NgoUser.activity.NgoHomeActivity;
import com.spoton.serveio.ui.VolunteerUser.activity.VolunteerHomeActivity;

public class VolunteerRegisterActivity extends AppCompatActivity {
    EditText et_name_volunteer_register, et_location_volunteer_register,et_email_volunteer_register,et_phno_volunteer_register,
            et_password_volunteer_register,et_age_volunteer_register;
    Button btn_login_volunteer_register,btn_volunteer_register;

    ProgressBar pb_volunteer_register;

    DatabaseReference reference2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_register);

        et_name_volunteer_register = findViewById(R.id.et_name_volunteer_register);
        et_location_volunteer_register = findViewById(R.id.et_location_volunteer_register);
        et_email_volunteer_register = findViewById(R.id.et_email_volunteer_register);
        et_phno_volunteer_register = findViewById(R.id.et_phno_volunteer_register);
        et_password_volunteer_register = findViewById(R.id.et_password_volunteer_register);
        et_age_volunteer_register=findViewById(R.id.et_age_volunteer_register);
        btn_login_volunteer_register = findViewById(R.id.btn_login_volunteer_register) ;
        btn_volunteer_register = findViewById(R.id.btn_volunteer_register) ;
        pb_volunteer_register = findViewById(R.id.pb_volunteer_register);
        btn_volunteer_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference2 = FirebaseDatabase.getInstance().getReference("Volunteers");
                String name = et_name_volunteer_register.getText().toString().trim();
                String location = et_location_volunteer_register.getText().toString().trim();
                String email = et_email_volunteer_register.getText().toString().trim();
                String phno = et_phno_volunteer_register.getText().toString().trim();
                String password = et_password_volunteer_register.getText().toString().trim();
                String age1 = et_age_volunteer_register.getText().toString().trim();
                int age = Integer.parseInt(age1);
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Enter name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(phno)) {
                    Toast.makeText(getApplicationContext(), "Enter phone number!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(age1)) {
                    Toast.makeText(getApplicationContext(), "Enter age!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(location)) {
                    Toast.makeText(getApplicationContext(), "Enter location!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (age < 18 || age > 60) {
                    Toast.makeText(getApplicationContext(), "Age not within range! Only 18-60 years volunteers allowed!", Toast.LENGTH_SHORT).show();
                    return;
                }
                VolunteersHelperClass helperClass = new VolunteersHelperClass(name,location,email,phno,password,age1);
                reference2.child(phno).setValue(helperClass);

                pb_volunteer_register.setVisibility(View.VISIBLE);
                Toast.makeText(VolunteerRegisterActivity.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(VolunteerRegisterActivity.this, VolunteerHomeActivity.class));
                finish();
            }

        });
        btn_login_volunteer_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VolunteerRegisterActivity.this, VolunteerLoginActivity.class) );
                pb_volunteer_register.setVisibility(View.VISIBLE);
            }
        });
    }
}