package com.svc.forcedelete;

import android.content.Intent;
import android.os.Bundle;
import android.app.*;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startService(new Intent(this, MyService.class));
        finish(); 
    }
}

