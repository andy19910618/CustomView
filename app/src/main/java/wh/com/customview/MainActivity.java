package wh.com.customview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import wh.com.customview.activity.FirstActivity;
import wh.com.customview.activity.SecondActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        ((Button) findViewById(R.id.first)).setOnClickListener(this);
        ((Button) findViewById(R.id.second)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.first:
                startActivity(new Intent(mContext, FirstActivity.class));
                break;

            case R.id.second:
                startActivity(new Intent(mContext, SecondActivity.class));
                break;
        }
    }
}
