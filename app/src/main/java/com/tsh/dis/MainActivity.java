package com.tsh.dis;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Handler;
import android.widget.ListView;
import android.widget.TextView;
import android.util.Log;
import android.os.Vibrator;
import android.content.Context;
import android.media.MediaPlayer;




public class MainActivity extends AppCompatActivity {
    TextView heartRateNumber;
    ListView lv;
    private Handler mHandler = new Handler();
    int num;
    boolean x;
    boolean isStart;
    CoordinatorLayout cL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isStart = false;


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isStart == false) {
                    isStart = true;
                    num = 70;
                    heartRateNumber = (TextView) findViewById(R.id.hrNum);
                    heartRateNumber.setText("" + num);
                    mHandler.postDelayed(updateTask, 500);
                    x = true;
                } else {
                    isStart = false;
                    mHandler.postDelayed(updateTask, 0);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        ;

        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    // This updates number in screen during working time
    private Runnable updateTask = new Runnable() {
        public void run() {
            Log.d(getString(R.string.app_name) + " ChatList.updateTask()",
                    "updateTask run!");
            if (x == true) {
                num = num + 1;
                if (num > 108) {
                    x = false;
                }
            } else {
                num = num - 1;
            }
            heartRateNumber = (TextView) findViewById(R.id.hrNum);
            heartRateNumber.setText("" + num);
            cL = (CoordinatorLayout) findViewById(R.id.bg);
            if (num > 100) {
                cL.setBackgroundColor(0xffff0000);
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(400);
            } else if (num > 80 && num < 101) {
                cL.setBackgroundColor(0xffffff00);
            } else {
                cL.setBackgroundColor(0xffffffff);
            }

            if (isStart == true) {
                mHandler.postDelayed(updateTask, 500);

            } else {
                heartRateNumber.setText("Stop");
                cL.setBackgroundColor(0xffffffff);
            }
        }
    };
}
