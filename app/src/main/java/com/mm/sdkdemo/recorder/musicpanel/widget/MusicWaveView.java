package com.mm.sdkdemo.recorder.musicpanel.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.mm.mediasdk.utils.UIUtils;
import com.mm.sdkdemo.R;

public class MusicWaveView extends View {
    private static final String TAG = "MusicWaveView";

    private int mDisplayTime;
    private int mTotalTime;
    private float[] mWaveArray;
    private int mStartOffset;
    private int mWaveHeight;
    private int mScreenWidth;
    private int mSelectBgWidth;
    private int colorNormal = 0X009cff;
    private int colorPlayed = 0X009cff;
    private int colorSelected = 0X009cff;

    //    private static final int WAVE_STEP = 1 * 1000;
    private static final int WAVE_WIDTH = 12;
    private static final int WAVE_OFFSET = 20;

    private Paint mPaint = new Paint();
    private RectF mRect = new RectF();

    private int mWidth;
    private int mHeight;

    public MusicWaveView(Context context) {
        super(context);
        init(context);
    }

    public MusicWaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MusicWaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mWaveHeight = UIUtils.getPixels(55);
        mScreenWidth = context.getResources().getDisplayMetrics().widthPixels;
        mSelectBgWidth = UIUtils.getPixels(200);
        mStartOffset = (mScreenWidth - mSelectBgWidth) / 2;
        setWillNotDraw(false);
        mPaint.setAntiAlias(true);
        mPaint.setColor(UIUtils.getColor(R.color.music_panle_music_range_blue));
    }

    public void layout() {
        if (mDisplayTime != 0 && mTotalTime != 0) {
            int lWidth = (int) ((mTotalTime / (float) mDisplayTime) * mSelectBgWidth) + mStartOffset * 2;
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) getLayoutParams();
            params.width = lWidth;
            setLayoutParams(params);
            mWidth = lWidth;
            Log.e(TAG, "lWidth..." + lWidth);
            mHeight = params.height;
            generateWaveArray();
            invalidate();
        }

    }

    private void generateWaveArray() {
        int count = (mWidth - (mStartOffset * 2)) / (WAVE_WIDTH + WAVE_OFFSET);
        mWaveArray = new float[count];
        for (int i = 0; i < count; i++) {
            int result = i % 3;
            if (result == 0) {
                mWaveArray[i] = 0.3f;
            } else if (result == 1) {
                mWaveArray[i] = 0.6f;
            } else {
                mWaveArray[i] = 0.9f;
            }
        }
    }

    public void setDisplayTime(int displayTime) {
        mDisplayTime = displayTime;
    }

    public void setTotalTime(int totalTime) {
        mTotalTime = totalTime;
    }

    public int getMusicLayoutWidth() {
        return (mWidth - (mStartOffset * 2));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (getHeight() != 0 && mWaveArray != null) {
            for (int i = 0; i < mWaveArray.length; i++) {
                int height = (int) (mWaveHeight * mWaveArray[i]);
                int left = i * (WAVE_OFFSET + WAVE_WIDTH) + mStartOffset;
                int right = left + WAVE_WIDTH;
                int top = (getHeight() - height) / 2;
                int bottom = top + height;
                mRect.set(left, top, right, bottom);
//                if (i % 3 == 0) {
//                    mPaint.setColor(UIUtils.getColor(R.color.C07));
//                } else if (i % 3 == 1) {
//                    mPaint.setColor(colorPlayed);
//                } else if (i % 3 == 2) {
//                    mPaint.setColor(colorSelected);
//                }
                drawRoundRect(canvas, mPaint, mRect, WAVE_WIDTH);
            }
        }
    }

    private static void drawRoundRect(Canvas canvas, Paint paint, RectF rectF, float radius) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawRoundRect(rectF, radius, radius, paint);
        } else {
            Path path = new Path();
            path.moveTo(rectF.left + radius, rectF.top);
            path.lineTo(rectF.right - radius, rectF.top);
            path.quadTo(rectF.right, rectF.top, rectF.right, rectF.top + radius);

            path.lineTo(rectF.right, rectF.bottom - radius);
            path.quadTo(rectF.right, rectF.bottom, rectF.right - radius, rectF.bottom);

            path.lineTo(rectF.left + radius, rectF.bottom);
            path.quadTo(rectF.left, rectF.bottom, rectF.left, rectF.bottom - radius);

            path.lineTo(rectF.left, rectF.top + radius);
            path.quadTo(rectF.left, rectF.top, rectF.left + radius, rectF.top);
            canvas.drawPath(path, paint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        int width = 0;
        int height = 0;
        //获得宽度MODE
        int modeW = MeasureSpec.getMode(widthMeasureSpec);
        //获得宽度的值
        if (modeW == MeasureSpec.AT_MOST) {
            width = MeasureSpec.getSize(widthMeasureSpec);
        }
        if (modeW == MeasureSpec.EXACTLY) {
            width = widthMeasureSpec;
        }
        if (modeW == MeasureSpec.UNSPECIFIED) {
            width = mWidth;
        }
        //获得高度MODE
        int modeH = MeasureSpec.getMode(height);
        //获得高度的值
        if (modeH == MeasureSpec.AT_MOST) {
            height = MeasureSpec.getSize(heightMeasureSpec);
        }
        if (modeH == MeasureSpec.EXACTLY) {
            height = heightMeasureSpec;
        }
        if (modeH == MeasureSpec.UNSPECIFIED) {
            //ScrollView和HorizontalScrollView
            height = mHeight;
        }
        //设置宽度和高度
        setMeasuredDimension(width, height);
    }
}
