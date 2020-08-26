package com.plusauth.androidexample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.plusauth.android.OIDC;
import com.plusauth.android.auth.exceptions.AuthenticationException;
import com.plusauth.android.auth.logout.LogoutRequest;
import com.plusauth.android.model.Credentials;
import com.plusauth.android.model.UserProfile;
import com.plusauth.android.util.PACallback;
import com.plusauth.android.util.VoidCallback;

public class ProfileActivity extends AppCompatActivity {
    public static final String EXTRA_CREDENTIALS = "credentials";
    private static final String TAG = "AccountActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Credentials credentials = getIntent().getExtras().getParcelable(EXTRA_CREDENTIALS);

        OIDC plusAuth = PlusAuthInstance.get(this);

        TextView accountView = findViewById(R.id.profile_text);

        plusAuth.getApi().userInfo().call(new PACallback<UserProfile, AuthenticationException>() {
            @Override
            public void onSuccess(UserProfile userProfile) {
                runOnUiThread(() -> accountView.setText(userProfile.toString()));
            }

            @Override
            public void onFailure(AuthenticationException e) {
                Log.e(TAG, "Could not get profile", e);
            }
        });


        Button logout = findViewById(R.id.profile_logout_btn);

        logout.setOnClickListener(v -> {
            logout.setEnabled(false);
            plusAuth.logout(this, new LogoutRequest(), new VoidCallback() {
                @Override
                public void onSuccess(Void aVoid) {
                    Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onFailure(AuthenticationException e) {
                    Log.e(TAG, "Logout failed", e);
                    runOnUiThread(() ->
                            logout.setEnabled(true));
                }
            });
        });

    }
}