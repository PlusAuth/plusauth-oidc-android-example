package com.plusauth.androidexample;

import android.content.Context;

import com.plusauth.android.OIDC;
import com.plusauth.android.OIDCBuilder;

public class PlusAuthInstance {
    private static OIDC plusAuth;

    public static OIDC get(Context context) {
        if (plusAuth == null) {
            plusAuth = new OIDCBuilder(context, "{your-client-id}", "{your-plusauth-host}")
                    .setLoggingEnabled(true)
                    .build();
        }

         return plusAuth;
    }

}
