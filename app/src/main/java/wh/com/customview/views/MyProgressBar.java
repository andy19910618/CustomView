package wh.com.customview.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import wh.com.customview.R;

/**
 * @创建者: andy
 * @创建时间: 2018-05-02 上午 11:32
 * @描述:
 * @版本: $Rev$
 * @更新者: $Author$
 * @更新时间: $Date$
 * @更新描述: TODO
 */

public class MyProgressBar extends View {
    Context mContext;
    private Paint paint, textPaint;

    private float viewWidth, viewHeight;
    private float cirWidth = 2;
    private float lineWidth = 2;
    private float textSize = 12;
    private float circleRadius = 7;
    private RectF circleRectF = new RectF();

    private int max = 1;
    private int progress = 0;



    public MyProgressBar(Context context) {
        super(context);
        mContext = context;
        init(null,0);
    }

    public MyProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(attrs,0);
    }

    public MyProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init(attrs,defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyle){
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.MyProgressBar, defStyle, 0);
        cirWidth  = typedArray.getDimension(R.styleable.MyProgressBar_myProgressBarCirWidth,cirWidth);
        circleRadius  = typedArray.getDimension(R.styleable.MyProgressBar_myProgressBarCircleRadius,circleRadius);
        lineWidth = typedArray.getDimension(R.styleable.MyProgressBar_myProgressBarLineWidth,lineWidth);
        textSize  = typedArray.getDimension(R.styleable.MyProgressBar_myProgressBarTextSize,textSize);
        typedArray.recycle();

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = getMeasuredWidth();
        viewHeight = getMeasuredHeight();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(lineWidth);
        paint.setColor(Color.parseColor("#f2f2f2"));
        canvas.drawLine(circleRadius,viewHeight / 2,viewWidth-(2*circleRadius),viewHeight / 2,paint);

        float percent = progress *  100 / max;
        float width = 0;
        if(progress == 0){
            width = (viewWidth * percent / 100) + 2 * circleRadius;
        }else if(progress == max){
            width = (viewWidth * percent / 100) - 2 * circleRadius;
        }else{
            width = (viewWidth * percent / 100);
        }
        paint.setColor(Color.WHITE);
        canvas.drawCircle(width,viewHeight / 2,circleRadius,paint);

        paint.setColor(Color.parseColor("#cccccc"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(cirWidth);
        canvas.drawCircle(width, viewHeight / 2,circleRadius,paint);

        textPaint.setTextSize(textSize);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setColor(Color.parseColor("#b2b2b2"));
        canvas.drawText(String.valueOf(progress),width,(viewHeight / 2 + 2 * circleRadius + (textSize / 2) + 10),textPaint);
    }

    public void setPoint(int progress,int max) {
        this.progress = progress;
        this.max = max;
        invalidate();
    }
}
