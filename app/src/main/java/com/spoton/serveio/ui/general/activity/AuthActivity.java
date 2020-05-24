package com.spoton.serveio.ui.general.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ibm.cloud.appid.android.api.AppID;
import com.ibm.cloud.appid.android.api.AuthorizationException;
import com.ibm.cloud.appid.android.api.AuthorizationListener;
import com.ibm.cloud.appid.android.api.LoginWidget;
import com.ibm.cloud.appid.android.api.tokens.AccessToken;
import com.ibm.cloud.appid.android.api.tokens.IdentityToken;
import com.ibm.cloud.appid.android.api.tokens.RefreshToken;
import com.spoton.serveio.R;
import com.spoton.serveio.ui.NgoUser.activity.NgoHomeActivity;
import com.spoton.serveio.ui.VolunteerUser.activity.VolunteerHomeActivity;

public class AuthActivity extends AppCompatActivity {

    Button authButton;
    TextView authTextView;
    String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        authButton = findViewById(R.id.authButton);
        authTextView = findViewById(R.id.authTextView);
        type = getIntent().getStringExtra("type");


        AppID.getInstance().initialize(getApplicationContext(), "2a5db7ea-939a-4fca-b30a-1c6ea4a711dc", AppID.REGION_UK);
    }

    public void authButtonClick(View view) {
        authTextView.setText("Authentication in progress....");
        LoginWidget loginWidget = AppID.getInstance().getLoginWidget();
        loginWidget.launch(this, new AuthorizationListener() {
            @Override
            public void onAuthorizationCanceled() {

            }

            @Override
            public void onAuthorizationFailure(AuthorizationException exception) {

            }

            @Override
            public void onAuthorizationSuccess(AccessToken accessToken, final IdentityToken identityToken, RefreshToken refreshToken) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        authButton.setVisibility(View.INVISIBLE);
                        authTextView.setText("Hello " + identityToken.getName());
                        if (type.equals("ngo")) {
                            Intent intent = new Intent(AuthActivity.this, NgoHomeActivity.class);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(AuthActivity.this, VolunteerHomeActivity.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        });
    }
}
