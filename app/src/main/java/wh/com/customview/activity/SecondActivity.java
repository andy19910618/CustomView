package wh.com.customview.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import wh.com.customview.R;
import wh.com.customview.views.CountdownView;

public class SecondActivity extends AppCompatActivity {
    long time = 3000;
    String TAG = "AppCompatActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ((Button) findViewById(R.id.btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.sendEmptyMessageDelayed(1,40);
            }
        });

    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Log.d(TAG,"" + time);
                    if(time > 1000){
                        ((CountdownView)findViewById(R.id.countdownView)).setTime(time);
                        time -= 40;
                        mHandler.sendEmptyMessageDelayed(1,40);
                    }
                    break;
            }
        }
    };
}
