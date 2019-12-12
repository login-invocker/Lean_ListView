package com.invocker.invocker_math;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class Click_Invocker extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //rm title bar

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //rm notification
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_click_invocker);
        //db fist start
        SQLiteDatabase db = openOrCreateDatabase("mydb.db", this.MODE_PRIVATE, null);
        db.close();
    }

    public void Invocker(View view) {
        Intent intent = new Intent(this, Play.class);
        startActivity(intent);
    }

    public void chiase(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Let me recommend you this application\n\n http://play.google.com");
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }

    public void rate(View view) {
        try {
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=" + this.getPackageName());
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            // To count with Play market backstack, After pressing back button,
            // return app after rate
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            }
            startActivity(goToMarket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
