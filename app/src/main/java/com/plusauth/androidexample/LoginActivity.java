package com.plusauth.androidexample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.plusauth.android.OIDC;
import com.plusauth.android.auth.exceptions.AuthenticationException;
import com.plusauth.android.auth.login.LoginRequest;
import com.plusauth.android.model.Credentials;
import com.plusauth.android.util.AuthenticationCallback;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button login = findViewById(R.id.login_login_btn);
        OIDC plusAuth = PlusAuthInstance.get(this);

        login.setOnClickListener(v -> {
            login.setEnabled(false);
            plusAuth
                    .login(this, new LoginRequest().setScope("openid offline_access profile email"), new AuthenticationCallback() {
                        @Override
                        public void onSuccess(Credentials credentials) {
                            Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                            intent.putExtra(ProfileActivity.EXTRA_CREDENTIALS, credentials);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onFailure(AuthenticationException e) {
                            Log.e(TAG, "Login failed", e);
                            runOnUiThread(() ->
                                    login.setEnabled(true));
                        }

                    });
        });
    }
}