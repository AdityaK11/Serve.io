package com.spoton.serveio.ui.general.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.spoton.serveio.Common;
import com.spoton.serveio.R;
import com.spoton.serveio.ui.NgoUser.activity.NgoHomeActivity;
import com.spoton.serveio.ui.VolunteerUser.activity.VolunteerHomeActivity;

import io.paperdb.Paper;


public class NgoLoginActivity extends AppCompatActivity {

    EditText et_phno_ngo_login, et_password_ngo_login;
    Button btn_createnewacc_ngo_login, btn_ngo_login;
    ProgressBar pb_ngo_login;


    public Boolean validatePhno() {
        String phno = et_phno_ngo_login.getText().toString().trim();
        if (phno.isEmpty()) {
            et_phno_ngo_login.setError("Field cannot be empty");
            return false;
        } else {
            et_phno_ngo_login.setError(null);
            return true;
        }
    }

    public Boolean validatePassword() {
        String password = et_password_ngo_login.getText().toString().trim();
        if (password.isEmpty()) {
            et_password_ngo_login.setError("Field cannot be empty");
            return false;
        } else {
            et_password_ngo_login.setError(null);
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

        final String phnofromUser = et_phno_ngo_login.getText().toString().trim();
        final String passwordfromUser= et_password_ngo_login.getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Ngos").child("users");
        Query checkUser = reference.orderByChild("phoneNo").equalTo(phnofromUser);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    et_phno_ngo_login.setError(null);
                    String passwordfromDB="";
                    String UserKey="";
                    for (DataSnapshot supportItem: dataSnapshot.getChildren()) {
                        passwordfromDB = supportItem.child("password").getValue(String.class);
                        UserKey = supportItem.getKey();
                        break;
                    }
                    if (passwordfromDB.equals(passwordfromUser)) {
                        Paper.book().write(Common.User_Key,UserKey);
                        Paper.book().write(Common.userType,"Ngos");
                        startActivity(new Intent(NgoLoginActivity.this, NgoHomeActivity.class));
                        finish();
                    } else {
                        et_password_ngo_login.setError("Wrong password");
                        et_phno_ngo_login.requestFocus();

                    }
                } else {
                    et_phno_ngo_login.setError("No such phone no");
                    et_phno_ngo_login.requestFocus();
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
        setContentView(R.layout.activity_ngo_login);

        Paper.init(this);

        btn_ngo_login = findViewById(R.id.btn_ngo_login);
        et_phno_ngo_login = findViewById(R.id.et_phno_ngo_login);
        et_password_ngo_login = findViewById(R.id.et_password_ngo_login);
        pb_ngo_login = findViewById(R.id.pb_ngo_login);
        btn_createnewacc_ngo_login = findViewById(R.id.btn_createnewacc_ngo_login);
        btn_ngo_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser(v);
                pb_ngo_login.setVisibility(View.VISIBLE);

            }

        });
        btn_createnewacc_ngo_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NgoLoginActivity.this,NgoRegisterActivity.class));
                finish();
            }
        });




    }
}
