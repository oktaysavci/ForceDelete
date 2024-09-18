package com.svc.forcedelete;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import java.util.List;
import android.app.Activity;

public class UninstallActivity extends Activity {

    private List<String> userApps;
    private int currentAppIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userApps = getIntent().getStringArrayListExtra("userApps");

        if (userApps != null && !userApps.isEmpty()) {
            requestUninstall(userApps.get(currentAppIndex));
        } else {
            finish();
        }
    }

    private void requestUninstall(String packageName) {
        Intent intent = new Intent(Intent.ACTION_DELETE);
        intent.setData(Uri.parse("package:" + packageName));
        intent.putExtra(Intent.EXTRA_RETURN_RESULT, true);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            currentAppIndex++;
            if (currentAppIndex < userApps.size()) {
                requestUninstall(userApps.get(currentAppIndex));
            } else {
                startUninstallActivityAgain();
            }
        }
    }

    private void startUninstallActivityAgain() {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
        finish();
    }
}

