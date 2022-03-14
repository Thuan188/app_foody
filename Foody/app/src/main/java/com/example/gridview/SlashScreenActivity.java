package com.example.gridview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SlashScreenActivity extends AppCompatActivity {
    private NotificationManagerCompat notificationManagerCompat;
    TextView txtPhienBan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slash_screen);
        txtPhienBan = findViewById(R.id.txtPhienBan);
        try{
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(),0);
            txtPhienBan.setText(getString(R.string.version) + " " + packageInfo.versionName);
            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {



                        Intent intent=new Intent(SlashScreenActivity.this, ThanhvienActivity.class);
                        startActivity(intent);

                    finish();
                }},2000);
        }catch (PackageManager.NameNotFoundException e)
        {

        }
    }
}