package com.invocker.invocker_math;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.invocker.invocker_math.Adapter.AdapterGsonStorage;
import com.invocker.invocker_math.Language.LocaleHelper;
import com.invocker.invocker_math.Model.ListUser;
import com.invocker.invocker_math.Model.UserScope;

import java.util.List;
import java.util.Locale;

public class Click_Invocker extends AppCompatActivity {
    private TextView txtRanks;
    private Locale mlocale;
    private Spinner mLanguage;
    private ArrayAdapter<String> mAdapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //rm title bar

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //rm notification
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_click_invocker);
        //control
        mLanguage = (Spinner) findViewById(R.id.snp_language);
        addSpinner();
        txtRanks = findViewById(R.id.txt_rank_user);
        if (addRanks() != -1) {
            txtRanks.setText(String.valueOf(getResources().getString(R.string.highest) + ": " + addRanks()));
        }
    }

    private void addSpinner() {
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.language_option));
        mLanguage.setAdapter(mAdapter);

        if (LocaleHelper.getLanguage(this).equalsIgnoreCase("en")) {
            mLanguage.setSelection(mAdapter.getPosition("English"));
        } else if (LocaleHelper.getLanguage(this).equalsIgnoreCase("vi")) {
            mLanguage.setSelection(mAdapter.getPosition("Tiếng Việt"));
        }
        mLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Resources resources;
                switch (i) {
                    case 0:
                        context = LocaleHelper.setLocale(getApplicationContext(), "en");
                        resources = context.getResources();
                        break;
                    case 1:
                        context = LocaleHelper.setLocale(getApplicationContext(), "in");
                        resources = context.getResources();
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    private int addRanks() {
        try {
            AdapterGsonStorage newStorage = new AdapterGsonStorage(this);
            ListUser list = newStorage.stringtoOjbect(newStorage.readData());
            List<UserScope> userlist = list.getUserScopes();
            return userlist.get(0).getScope();
        } catch (Exception e) {

        }

        return -1;
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

    public void rank(View view) {
        Intent intent = new Intent(this, ListUserActivity.class);
        startActivity(intent);
    }
}
