package com.invocker.invocker_math;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.invocker.invocker_math.Adapter.OverDialog;
import com.invocker.invocker_math.Model.LevelM;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Play extends AppCompatActivity {
    //time to play each level
    private final int TIME_PLAY_EACH_LEVEL = 1500;
    //define color backgroud
    private String[] arrColor = {"#99FF33", "#CC66FF", "#FF3300", "#330099", "#CC9933"};
    //view
    private TextView txtFormula;
    private ImageView btnCheckTrue;
    private ImageView btnCheckFalse;
    private TextView txtScore;
    private TextView txtResult;
    private ProgressBar progressBarTimerTask;
    private RelativeLayout relativeLayout;
    private Timer timer;
    private TimerTask timerTask;
    private LevelM model;
    private Random random;
    private int score = 0;
    private Context context;
    private OverDialog dialog;

    private void initViews() {
        txtResult = findViewById(R.id.txt_result);
        progressBarTimerTask = findViewById(R.id.progressBar_timer_task);
        txtScore = findViewById(R.id.txt_score);
        txtFormula = findViewById(R.id.txt_formula);
        btnCheckTrue = findViewById(R.id.btn_check_true);
        btnCheckFalse = findViewById(R.id.btn_check_false);
        relativeLayout = findViewById(R.id.play_screen);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //rm title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //rm notification
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_play);
        initViews();
        random = new Random();
        model = Gneratel.generalLever(1);
        //show level info on screen
        displayNewLevel(model);
        createTimerTask();

    }

    private void createTimerTask() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showGameOver(score);
                    }
                });
            }
        };
        timer.schedule(timerTask, TIME_PLAY_EACH_LEVEL);
    }

    private void showGameOver(final int score) {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 400 milliseconds
        v.vibrate(150);
        btnCheckTrue.setEnabled(false);
        btnCheckFalse.setEnabled(false);
        cancelTimer();
        dialog = new OverDialog(Play.this, score);
        dialog.setCanceledOnTouchOutside(false);
        try {
            dialog.show();

        }catch (Exception e){

        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {

        }
        return super.onKeyDown(keyCode, event);
    }

    private void cancelTimer() {
        timer.cancel();
        timerTask.cancel();

    }

    private void displayNewLevel(LevelM model) {
        showProcessBar();
        txtFormula.setText(model.strOperator);
        txtResult.setText(model.strResult);
        //ranrom color bacgrould
        int indexColor = random.nextInt(arrColor.length);
        relativeLayout.setBackgroundColor(Color.parseColor(arrColor[indexColor]));

    }

    private void showProcessBar() {
        CountDownTimer countDownTimer = new CountDownTimer(TIME_PLAY_EACH_LEVEL, TIME_PLAY_EACH_LEVEL / 10) {
            @Override
            public void onTick(long millisUntilFinished) {
                int xl = progressBarTimerTask.getProgress();
                if (xl >= progressBarTimerTask.getMax()) {
                    xl = 0;
                }
                progressBarTimerTask.setProgress(xl + 10);
            }

            @Override
            public void onFinish() {

            }
        };
        countDownTimer.start();
    }

    private void nextLevel(int score) {
        txtScore.setText(String.valueOf(score));
        cancelTimer();
        createTimerTask();
        model = Gneratel.generalLever(score);
        displayNewLevel(model);
    }

    public void check_true(View view) {
        if (model.correctWrong == true) {
            score += 1;
            nextLevel(score);
        } else {
            showGameOver(score);
        }
    }

    public void check_false(View view) {
        if (model.correctWrong == false) {
            score += 1;
            nextLevel(score);
        } else {
            showGameOver(score);
        }
    }
}
