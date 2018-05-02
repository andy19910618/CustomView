package wh.com.customview.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import wh.com.customview.R;
import wh.com.customview.views.MyProgressBar;

public class FirstActivity extends AppCompatActivity {
    int progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ((MyProgressBar)findViewById(R.id.myP)).setPoint(0,5);
        mHandler.sendEmptyMessageDelayed(1,1000);
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if(progress < 15){
                        progress++;
                        ((MyProgressBar)findViewById(R.id.myP)).setPoint(progress,15);
                        mHandler.sendEmptyMessageDelayed(1,1000);
                    }
                    break;
            }
        }
    };
}
