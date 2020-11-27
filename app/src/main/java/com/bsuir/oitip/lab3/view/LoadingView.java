package com.bsuir.oitip.lab3.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bsuir.oitip.lab3.R;

public class LoadingView extends androidx.appcompat.widget.AppCompatImageView {

    Runnable mUpdateViewRunnable = null;
    float mRotateDegrees = 0;
    boolean mNeedToUpdateView = false;
    long mFrameTime = 0;

    public LoadingView(Context context) {
        super(context);
        init();
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setAnimationSpeed(float scale) {
        mFrameTime = (long)(1000 / 12 / scale);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mNeedToUpdateView = false;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mNeedToUpdateView = true;
        post(mUpdateViewRunnable);
    }

    public void init(){
        this.setImageResource(R.drawable.kprogresshud_spinner_light);
        mFrameTime = 1000 / 12;
        mUpdateViewRunnable = new Runnable() {
            @Override
            public void run() {
                mRotateDegrees += 30f;
                mRotateDegrees = mRotateDegrees < 360 ? mRotateDegrees : mRotateDegrees - 360;
                invalidate();
                if (mNeedToUpdateView) {
                    postDelayed(this, mFrameTime);
                }
            }
        };
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.rotate(mRotateDegrees, getWidth() / 2f, getHeight() / 2f);
        super.onDraw(canvas);
    }
}
