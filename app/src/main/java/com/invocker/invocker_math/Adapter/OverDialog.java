package com.invocker.invocker_math.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.invocker.invocker_math.Click_Invocker;
import com.invocker.invocker_math.Play;
import com.invocker.invocker_math.R;

public class OverDialog extends Dialog {
    private RelativeLayout layoutSco;
    private TextView dialogScore;
    private EditText edtNameUser;
    private Button btnDialogHome;
    private Button btnChiase;
    private int score;
    private Button btnPlayAgain;
    Activity context;

    private void initViews() {
        layoutSco = (RelativeLayout) findViewById(R.id.layout_sco);
        dialogScore = (TextView) findViewById(R.id.dialog_score);
        edtNameUser = (EditText) findViewById(R.id.edt_name_user);
        btnDialogHome = (Button) findViewById(R.id.btn_dialog_home);
        btnChiase = (Button) findViewById(R.id.btn_chiase);
        btnPlayAgain = (Button) findViewById(R.id.btn_play_again);
    }

    public OverDialog(@NonNull Activity context, int score) {
        super(context);
        this.context = context;
        this.score = score;
        setContentView(R.layout.dialog_over);
        initViews();
        dialogScore.setText("Score:" + score);
        addEvent();
    }

    private void addEvent() {
        btnDialogHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Click_Invocker.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });
        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Play.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                context.startActivity(intent);
            }
        });
        btnChiase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtNameUser.getText().toString().trim().isEmpty()) {
                    Toast.makeText(context, "Input Your name", Toast.LENGTH_SHORT).show();
                } else {


                    AdapterGsonStorage newgson = new AdapterGsonStorage(context, score, edtNameUser.getText().toString());
                    newgson.objectToString();
                    //    Toast.makeText(context,newgson.readData(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
