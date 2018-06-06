package wh.com.customview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import wh.com.customview.R;
import wh.com.customview.utils.ScreenUtils;

/**
 * @创建者: andy
 * @创建时间: 2018-06-02 上午 10:10
 * @描述:
 * @版本: $Rev$
 * @更新者: $Author$
 * @更新时间: $Date$
 * @更新描述: TODO
 */

public class CountdownView extends View {
    private Context mContext;
    private Paint paint;

    float arc1Start = -30, arc1Angle = 120;
    float arc2Start = 90, arc2Angle = 120;
    float arc3Start = -150, arc3Angle = 120;
    String mTime = "3";

    public CountdownView(Context context) {
        super(context);
        mContext = context;
        init(null, 0);
    }

    public CountdownView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(null, 0);
    }

    public CountdownView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init(null, 0);
    }

    public void init(AttributeSet attr, int defStyle) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画背景圆
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getResources().getColor(R.color.XFFFFFF));
        canvas.drawCircle(ScreenUtils.dp2px(mContext, 75), ScreenUtils.dp2px(mContext, 75), ScreenUtils.dp2px(mContext, 75), paint);


        //画扇形1
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getResources().getColor(R.color.X00BDF3));
        canvas.drawArc(ScreenUtils.dp2px(mContext, 10),
                ScreenUtils.dp2px(mContext, 10),
                ScreenUtils.dp2px(mContext, 140),
                ScreenUtils.dp2px(mContext, 140), arc1Start, arc1Angle, true, paint);


        //画扇形2
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getResources().getColor(R.color.XB3D71C));
        canvas.drawArc(ScreenUtils.dp2px(mContext, 10),
                ScreenUtils.dp2px(mContext, 10),
                ScreenUtils.dp2px(mContext, 140),
                ScreenUtils.dp2px(mContext, 140), arc2Start, arc2Angle, true, paint);

        //画扇形3
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getResources().getColor(R.color.XFF9100));
        canvas.drawArc(ScreenUtils.dp2px(mContext, 10),
                ScreenUtils.dp2px(mContext, 10),
                ScreenUtils.dp2px(mContext, 140),
                ScreenUtils.dp2px(mContext, 140), arc3Start, arc3Angle, true, paint);


        //画时间
        paint.setColor(getResources().getColor(R.color.XFFFFFF));
        paint.setTextSize(ScreenUtils.dp2px(mContext, 35));
        //x轴居中
        paint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetricsInt fm = paint.getFontMetricsInt();
        canvas.drawText(mTime, ScreenUtils.dp2px(mContext, 75),
                //y轴居中
                ScreenUtils.dp2px(mContext, 75 + ScreenUtils.px2dp(mContext, (fm.descent - fm.ascent) / 2 - fm.descent)),
                paint);


    }

    public void setTime(long time) {
        if(time > 2000){
            float addAngle = 2.4f;

            arc3Angle += addAngle;
            arc3Start = arc3Start + 150 / 25;
            Log.d("TAG","addAngle-->" + addAngle + "arc3Start-->" + arc3Start + "arc3Angle-->" +arc3Angle);


            arc2Angle += addAngle;
            arc2Start = arc2Start + 3.6f;

            arc1Start = arc3Angle + arc3Start;
            arc1Angle -= addAngle;
        }else if(time > 1000){

            arc3Angle -= 7.2f;
            arc3Start += 7.2f;

            arc2Angle += 7.2f;

        }
        mTime = String.valueOf(time / 1000 + 1);
        invalidate();
    }


}
